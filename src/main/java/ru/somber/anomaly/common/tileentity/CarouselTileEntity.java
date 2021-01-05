package ru.somber.anomaly.common.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
    private static final AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 450);
    private static final AnomalyPhase sleepPhase = new AnomalyPhase(PhaseType.Sleep, 100);

    static {
        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(sleepPhase);
        sleepPhase.setNextPhase(defaultPhase);
    }


    /** Целевая сущность работы аномалии. */
    private EntityLivingBase targetEntity;
    /** Коэффициент засасывания аномалии. */
    private float suctionFactor;


    public CarouselTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);

        if (!AnomalyMod.IS_SERVER) {
            CarouselEmitter emitter = new CarouselEmitter(0, 0, 0);
            setEmitter(emitter);
        }

        suctionFactor = 0.005F;
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
                return true;
            }
        } else {
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
    protected void activePhaseEnd() {
        super.activePhaseEnd();

        suctionFactor = 0.005F;
        targetEntity = null;
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
