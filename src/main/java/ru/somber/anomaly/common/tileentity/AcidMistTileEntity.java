package ru.somber.anomaly.common.tileentity;

import net.minecraft.entity.EntityLivingBase;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.AcidMistEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

import java.util.List;

public class AcidMistTileEntity extends AbstractAnomalyTileEntity {
    private static final float xMinAABB = -1F;
    private static final float yMinAABB = 0F;
    private static final float zMinAABB = -1F;
    private static final float xMaxAABB = 2F;
    private static final float yMaxAABB = 2F;
    private static final float zMaxAABB = 2F;

    private static final AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
    private static final AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 100);

    static {
        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(defaultPhase);
    }


    public AcidMistTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);

        if (!AnomalyMod.IS_SERVER) {
            AcidMistEmitter emitter = new AcidMistEmitter(0, 0, 0);
            setEmitter(emitter);
        }
    }

    @Override
    protected boolean processDefaultPhase() {
        prepareCollideEntityList(this);

        if (AnomalyMod.IS_SERVER) {

        } else {
            getEmitter().updateDefaultPhase(getCurrentPhaseTick(), getCurrentPhase().getTickDuration());
        }

        return applyAnomalyEffectEntityList(listForSearchEntities);
    }

    @Override
    protected boolean processActivePhase() {
        prepareCollideEntityList(this);

        if (AnomalyMod.IS_SERVER) {

        } else {
            getEmitter().updateActivePhase(getCurrentPhaseTick(), getCurrentPhase().getTickDuration());
        }

        return ! applyAnomalyEffectEntityList(listForSearchEntities);
    }

    @Override
    protected boolean processSleepPhase() {
        return true;
    }

    /**
     * Пытается применить эффект аномалии на переданный список сущностей.
     * Т.е. для каждой сущности вызывается applyAnomalyEffect(entity).
     *
     * @return true - если хотя бы на одну сущность был применен эффект аномалии, иначе false.
     */
    protected boolean applyAnomalyEffectEntityList(List<EntityLivingBase> entities) {
        boolean flag = false;
        for (EntityLivingBase entity : entities) {
            flag = flag || applyAnomalyEffect(entity);
        }
        return flag;
    }

    /**
     * Пытается применить эффект аномалии на переданную сущность.
     * Если переднная сущность - игрок в креативе, эффект аномалии не применяется.
     *
     * @return true - если на переданную сущность был применен эффект аномалии, иначе false.
     */
    protected boolean applyAnomalyEffect(EntityLivingBase entity) {
        //попытка каста к типу игрока
        if (canApplyAnomalyEffect(entity)) {
            //здесь применяем эффект аномалии для всех сущностей.
            if (AnomalyMod.IS_SERVER) {
                entity.setHealth(entity.getHealth() - 0.05F);
            }
            return true;
        }

        return false;
    }

}
