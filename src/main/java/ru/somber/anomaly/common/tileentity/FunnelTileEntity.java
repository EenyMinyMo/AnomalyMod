package ru.somber.anomaly.common.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.CarouselEmitter;
import ru.somber.anomaly.client.emitter.FunnelEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

import java.util.List;

public class FunnelTileEntity extends AbstractAnomalyTileEntity {
    private static final float xMinAABB = -2F;
    private static final float yMinAABB = -0.5F;
    private static final float zMinAABB = -2F;
    private static final float xMaxAABB = 3F;
    private static final float yMaxAABB = 3.5F;
    private static final float zMaxAABB = 3F;

    private static final float xOffsetAnomalyCenter = 0.5F;
    private static final float yOffsetAnomalyCenter = 2.5F;
    private static final float zOffsetAnomalyCenter = 0.5F;

    private static final AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
    private static final AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 120);
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


    public FunnelTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);

        if (!AnomalyMod.IS_SERVER) {
            FunnelEmitter emitter = new FunnelEmitter(0, 0, 0);
            setEmitter(emitter);
        }

        suctionFactor = 0.01F;
    }

    @Override
    protected boolean processDefaultPhase() {
        super.processDefaultPhase();
        prepareCollideEntityList(this);

        return searchTargetEntity(listForSearchEntities);
    }

    @Override
    protected boolean processActivePhase() {
        if (targetEntity != null) {
            if (! targetEntity.isEntityAlive()) {
                return true;
            }

            if (suctionFactor < 0.5F) {
                suctionFactor *= 1.025F;
            }

            if (AnomalyMod.IS_SERVER) {
                //действия происходят на сервере.

                //если сущность не является сущностью игрока, то применяем эффект аномалии.
                if (!(targetEntity instanceof EntityPlayer)) {
                    applyAnomalyEffect();
                }

                //если время активной фазы вышло, убиваем сущность.
                if ((getCurrentPhaseTick()) >= getCurrentPhase().getTickDuration()) {
                    if (!(targetEntity instanceof EntityPlayer) ||
                            !((EntityPlayer) targetEntity).capabilities.isCreativeMode) {
                        targetEntity.setHealth(0);
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

        suctionFactor = 0.01F;
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

        if ((! AnomalyMod.IS_SERVER) && targetEntity == Minecraft.getMinecraft().renderViewEntity) {
            deltaY += 1.62;
        }

        targetEntity.motionX = 0;
        targetEntity.motionY = 0;
        targetEntity.motionZ = 0;

        targetEntity.motionX += deltaX * suctionFactor;
        targetEntity.motionY += deltaY * suctionFactor;
        targetEntity.motionZ += deltaZ * suctionFactor;
    }

}
