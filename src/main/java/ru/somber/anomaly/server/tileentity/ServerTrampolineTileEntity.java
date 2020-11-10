package ru.somber.anomaly.server.tileentity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.List;

public class ServerTrampolineTileEntity extends AbstractServerTileEntity {
    private static final float xMinAABB = -0.25F;
    private static final float yMinAABB = 0F;
    private static final float zMinAABB = -0.25F;
    private static final float xMaxAABB = 1.25F;
    private static final float yMaxAABB = 1.5F;
    private static final float zMaxAABB = 1.25F;


    public ServerTrampolineTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
        AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 1);
        AnomalyPhase sleepPhase = new AnomalyPhase(PhaseType.Sleep, 40);

        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(sleepPhase);
        sleepPhase.setNextPhase(defaultPhase);

        setPhase(defaultPhase);
    }


    @Override
    protected boolean processDefaultPhase() {
        listFoSearchEntities.clear();
        SomberCommonUtil.getEntitiesWithinAABB(getWorldObj(),
                                               EntityLivingBase.class,
                                               getAABBBody(),
                                               null,
                                               listFoSearchEntities);

        return applyAnomalyEffectEntityList(listFoSearchEntities);
    }

    @Override
    protected boolean processActivePhase() {
        return getCurrentPhase().getTickDuration() <= getCurrentPhaseTick();
    }

    @Override
    protected boolean processSleepPhase() {
        return getCurrentPhase().getTickDuration() <= getCurrentPhaseTick();
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
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            //здесь можно прописать код эффекта только для игроков.

            //если игрок в креативе, то ничего не делаем.
            if (player.capabilities.isCreativeMode) {
                return false;
            }
        }

        //здесь применяем эффект аномалии для всех сущностей.
        entity.motionY = 1F;

        return true;
    }

}
