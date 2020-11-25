package ru.somber.anomaly.common.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.CarouselEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

import java.util.List;

public class CarouselTileEntity extends AbstractAnomalyTileEntity {
    private static final float xMinAABB = -1F;
    private static final float yMinAABB = 0F;
    private static final float zMinAABB = -1F;
    private static final float xMaxAABB = 2F;
    private static final float yMaxAABB = 1F;
    private static final float zMaxAABB = 2F;

    private static final float xOffsetAnomalyCenter = 0.5F;
    private static final float yOffsetAnomalyCenter = 2.0F;
    private static final float zOffsetAnomalyCenter = 0.5F;
    private static final float suctionDistance = 7.5F;

    private static final AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
    private static final AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 200);
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
        prepareCollideEntityList(this);

        super.processDefaultPhase();

        return searchTargetEntity(listForSearchEntities);
    }

    @Override
    protected boolean processActivePhase() {
        if (targetEntity != null) {
            if (! targetEntity.isEntityAlive()) {
                targetEntity = null;
                return true;
            }

            if (suctionFactor < 0.5F) {
                suctionFactor *= 1.0128;
            }

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

            double distance = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
            if (distance > suctionDistance) {
                targetEntity = null;
                return true;
            }
        } else {
            return true;
        }


        if (AnomalyMod.IS_SERVER) {
            if (getCurrentPhaseTick() % 20 == 1) {
                targetEntity.setHealth(targetEntity.getHealth() - 0.5F);
            }
        }

        super.processActivePhase();

        return false;
    }

    @Override
    protected boolean processSleepPhase() {
        super.processSleepPhase();

        suctionFactor = 0.005F;

        return isPhaseTimeEnd();
    }

    /**
     * Пытается найти в переданном списке целевую сущность через canApplyAnomalyEffect().
     * Если целевая сущность найдена, она записывается в targetEntity и метод возврщает true.
     * Если целевая сущность не найдена, возвращается false.
     */
    private boolean searchTargetEntity(List<EntityLivingBase> entities) {
        for (EntityLivingBase entity : entities) {
            if (canApplyAnomalyEffect(entity)) {
                //здесь применяем эффект аномалии для всех сущностей.
                if (targetEntity == null) {
                    targetEntity = entity;
                }
                return true;
            }
        }
        return false;
    }

}
