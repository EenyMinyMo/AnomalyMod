package ru.somber.anomaly.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.AmaZ1nG.sound.MutableSound;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.ClientProxy;
import ru.somber.anomaly.client.emitter.CarouselEmitter;
import ru.somber.anomaly.client.particle.SplashGravityParticle;
import ru.somber.anomaly.common.entity.EntityBolt;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

import java.util.List;

public class CarouselTileEntity extends AbstractAnomalyTileEntity {
    private static final float xMinAABB = -0.9F;
    private static final float yMinAABB = -0.1F;
    private static final float zMinAABB = -0.9F;
    private static final float xMaxAABB = 1.9F;
    private static final float yMaxAABB = 1.1F;
    private static final float zMaxAABB = 1.9F;

    private static final float xOffsetAnomalyCenter = 0.5F;
    private static final float yOffsetAnomalyCenter = 2.0F;
    private static final float zOffsetAnomalyCenter = 0.5F;
    private static final float suctionDistance = 6F;

    private static final AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
    private static final AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 123);
    private static final AnomalyPhase sleepPhase = new AnomalyPhase(PhaseType.Sleep, 60);

    static {
        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(sleepPhase);
        sleepPhase.setNextPhase(defaultPhase);
    }


    /** Целевая сущность работы аномалии. */
    private EntityLivingBase targetEntity;
    /** Коэффициент засасывания аномалии. */
    private float suctionFactor;

    @SideOnly(Side.CLIENT)
    private MutableSound idleSound;
    @SideOnly(Side.CLIENT)
    private MutableSound activeSound;
    @SideOnly(Side.CLIENT)
    private MutableSound boltReactionSound;


    public CarouselTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);

        suctionFactor = 0.005F;
    }

    @Override
    protected void clientValidate() {
        CarouselEmitter emitter = new CarouselEmitter(xCoord + 0.5F, yCoord, zCoord + 0.5F);
        setEmitter(emitter);

        super.clientValidate();

        idleSound = new MutableSound(new ResourceLocation(AnomalyMod.MOD_ID + ":carousel_idle"));
        idleSound.setPosition(xCoord + 0.5, yCoord, zCoord + 0.5);
        idleSound.setRepeatable(true);

        activeSound = new MutableSound(new ResourceLocation(AnomalyMod.MOD_ID + ":carousel_active"));
        activeSound.setPosition(xCoord + 0.5, yCoord, zCoord + 0.5);
        activeSound.setRepeatable(false);

        boltReactionSound = new MutableSound(new ResourceLocation(AnomalyMod.MOD_ID + ":carousel_bolt_reaction"));
        boltReactionSound.setPosition(xCoord + 0.5, yCoord, zCoord + 0.5);
        boltReactionSound.setRepeatable(false);
    }

    @Override
    protected void clientInvalidate() {
        super.clientInvalidate();

        idleSound.stop();
        activeSound.stop();
        boltReactionSound.stop();
    }

    @Override
    protected boolean processDefaultPhase() {
        super.processDefaultPhase();
        prepareCollideEntityList(this);
        prepareCollideBoltList(this);

        for (EntityBolt entityBolt : listForSearchBolts) {
            if (! AnomalyMod.IS_SERVER) {
                float xParticlePos = (float) entityBolt.posX;
                float yParticlePos = (float) entityBolt.posY;
                float zParticlePos = (float) entityBolt.posZ;
                float xNormal = (float) entityBolt.motionX;
                float yNormal = (float) entityBolt.motionY;
                float zNormal = (float) entityBolt.motionZ;

                SplashGravityParticle particle = new SplashGravityParticle(xParticlePos, yParticlePos, zParticlePos, xNormal, yNormal, zNormal);
                ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
                boltReactionSound.play();
            }

            entityBolt.motionX = -entityBolt.motionX;
            entityBolt.motionY = -entityBolt.motionY;
            entityBolt.motionZ = -entityBolt.motionZ;
        }

        return searchTargetEntity(listForSearchEntities);
    }

    @Override
    protected boolean processActivePhase() {
        if (targetEntity != null) {
            if (! targetEntity.isEntityAlive()) {
                if (! AnomalyMod.IS_SERVER) {
                    activeSound.stop();
                }
                return true;
            }

            if (suctionFactor < 0.5F) {
                suctionFactor *= 1.02;
            }

            if (AnomalyMod.IS_SERVER) {
                //действия происходят на сервере.

                //если сущность не является сущностью игрока, то применяем эффект аномалии.
                if (!(targetEntity instanceof EntityPlayer)) {
                    applyAnomalyEffect();
                }

                //раз в секунду наносим урон.
                if (getCurrentPhaseTick() % 20 == 1) {
                    if (!(targetEntity instanceof EntityPlayer) ||
                            !((EntityPlayer) targetEntity).capabilities.isCreativeMode) {
                        targetEntity.setHealth(targetEntity.getHealth() - 2F);
                    }
                }
            } else {
                //действия просяходят на клиенте.

                //если сущность - главный игрок клиента и не находится в креативе, то применяем эффект аномалии.
                if (targetEntity instanceof EntityPlayer) {
                    if ((targetEntity == Minecraft.getMinecraft().renderViewEntity) &&
                            (!((EntityPlayer) targetEntity).capabilities.isCreativeMode)) {
                        applyAnomalyEffect();
                    }
                } else {
                    //если сущность не игрок, то применяем эффект аномалии.
                    applyAnomalyEffect();
                }
            }

            double deltaX = (xOffsetAnomalyCenter + xCoord) - targetEntity.posX;
            double deltaZ = (zOffsetAnomalyCenter + zCoord) - targetEntity.posZ;
            double distance = deltaX * deltaX + deltaZ * deltaZ;
            if (distance > suctionDistance) {
                if (! AnomalyMod.IS_SERVER) {
                    activeSound.stop();
                }
                return true;
            }
        } else {
            if (! AnomalyMod.IS_SERVER) {
                activeSound.stop();
            }
            return true;
        }

        super.processActivePhase();

        return isPhaseTimeEnd();
    }

    @Override
    protected boolean processSleepPhase() {
        super.processSleepPhase();

        return isPhaseTimeEnd();
    }

    @Override
    protected void defaultPhaseStart() {
        super.defaultPhaseStart();
        if (! AnomalyMod.IS_SERVER) {
            idleSound.play();
        }
    }

    @Override
    protected void defaultPhaseEnd() {
        super.defaultPhaseEnd();
        if (! AnomalyMod.IS_SERVER) {
            idleSound.stop();
        }
    }

    @Override
    protected void activePhaseStart() {
        super.activePhaseStart();
        if (! AnomalyMod.IS_SERVER) {
            activeSound.play();
        }
    }

    @Override
    protected void activePhaseEnd() {
        super.activePhaseEnd();

        suctionFactor = 0.005F;
        targetEntity = null;
    }

    @Override
    protected void sleepPhaseEnd() {
        super.sleepPhaseEnd();
    }

    @Override
    protected void sleepPhaseStart() {
        super.sleepPhaseStart();
    }


    private boolean searchTargetEntity(List<EntityLivingBase> entities) {
        if (! entities.isEmpty()) {
            if (targetEntity == null) {
                targetEntity = entities.get(0);
            }
            return true;
        }
        return false;
    }

    private void applyAnomalyEffect() {
        double deltaX = (xOffsetAnomalyCenter + xCoord) - targetEntity.posX;
        double deltaY = (yOffsetAnomalyCenter + yCoord) - targetEntity.posY;
        double deltaZ = (zOffsetAnomalyCenter + zCoord) - targetEntity.posZ;

        if (! AnomalyMod.IS_SERVER && targetEntity == Minecraft.getMinecraft().renderViewEntity) {
            deltaY += 1.62;
        }

        targetEntity.motionY = 0;

        targetEntity.motionX += deltaX * suctionFactor;
        targetEntity.motionY += deltaY * suctionFactor;
        targetEntity.motionZ += deltaZ * suctionFactor;

        targetEntity.rotationYaw += 5;
    }

}
