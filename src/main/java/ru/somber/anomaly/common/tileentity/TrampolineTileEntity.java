package ru.somber.anomaly.common.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.TrampolineEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

import java.util.List;

public class TrampolineTileEntity extends AbstractAnomalyTileEntity {
    private static final float xMinAABB = -0.25F;
    private static final float yMinAABB = 0F;
    private static final float zMinAABB = -0.25F;
    private static final float xMaxAABB = 1.25F;
    private static final float yMaxAABB = 1.5F;
    private static final float zMaxAABB = 1.25F;

    private static final AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
    private static final AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 1);
    private static final AnomalyPhase sleepPhase = new AnomalyPhase(PhaseType.Sleep, 60);

    static {
        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(sleepPhase);
        sleepPhase.setNextPhase(defaultPhase);
    }


    public TrampolineTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);

        if (!AnomalyMod.IS_SERVER) {
            TrampolineEmitter emitter = new TrampolineEmitter(0, 0, 0);
            setEmitter(emitter);
        }
    }

    @Override
    protected boolean processDefaultPhase() {
        super.processDefaultPhase();
        prepareCollideEntityList(this);

        if (! listForSearchEntities.isEmpty()) {
            for (EntityLivingBase entity : listForSearchEntities) {
                if (AnomalyMod.IS_SERVER) {
                    //действия происходят на сервере.

                    //если сущность не является сущностью игрока, то применяем эффект аномалии.
                    if (!(entity instanceof EntityPlayer)) {
                        applyAnomalyEffect(entity);
                    }

                    if (!(entity instanceof EntityPlayer) ||
                            !((EntityPlayer) entity).capabilities.isCreativeMode) {
                        entity.setHealth(entity.getHealth() - 3);
                    }
                } else {
                    //действия просяходят на клиенте.

                    //если сущность - главный игрок клиента и не находится в креативе, то применяем эффект аномалии.
                    if (entity instanceof EntityPlayer) {
                        if ((entity == Minecraft.getMinecraft().renderViewEntity) &&
                                (!((EntityPlayer) entity).capabilities.isCreativeMode)) {
                            applyAnomalyEffect(entity);
                        }
                    } else {
                        //если сущность не игрок, то применяем эффект аномалии.
                        applyAnomalyEffect(entity);
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean processActivePhase() {
        super.processActivePhase();

        return isPhaseTimeEnd();
    }

    @Override
    protected boolean processSleepPhase() {
        super.processSleepPhase();

        return isPhaseTimeEnd();
    }


    private void applyAnomalyEffect(EntityLivingBase entity) {
        entity.motionY = 0.85F;
    }

}
