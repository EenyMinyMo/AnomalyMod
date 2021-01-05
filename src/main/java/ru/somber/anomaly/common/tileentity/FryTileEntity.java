package ru.somber.anomaly.common.tileentity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.FryEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

import java.util.List;

public class FryTileEntity extends AbstractAnomalyTileEntity {
    private static final float xMinAABB = 0F;
    private static final float yMinAABB = 0F;
    private static final float zMinAABB = 0F;
    private static final float xMaxAABB = 1F;
    private static final float yMaxAABB = 3F;
    private static final float zMaxAABB = 1F;

    private static final AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
    private static final AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 100);

    static {
        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(defaultPhase);
    }


    private int tickOnActiveOffset;


    public FryTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);

        if (!AnomalyMod.IS_SERVER) {
            FryEmitter emitter = new FryEmitter(0, 0, 0);
            setEmitter(emitter);
        }
    }

    @Override
    protected boolean processDefaultPhase() {
        super.processDefaultPhase();
        prepareCollideEntityList(this);
        prepareCollideBoltList(this);

        tickOnActiveOffset = 0;

        return (! listForSearchEntities.isEmpty()) || (! listForSearchBolts.isEmpty());
    }

    @Override
    protected boolean processActivePhase() {
        super.processActivePhase();
        prepareCollideEntityList(this);
        prepareCollideBoltList(this);

        if ((! listForSearchEntities.isEmpty()) || (! listForSearchBolts.isEmpty())) {
            for (EntityLivingBase entity : listForSearchEntities) {
                if (AnomalyMod.IS_SERVER && getCurrentPhaseTick() % 4 == 0) {
                    if (!(entity instanceof EntityPlayer) ||
                            !((EntityPlayer) entity).capabilities.isCreativeMode) {
                        entity.setHealth(entity.getHealth() - 2);
                    }
                }
            }
            tickOnActiveOffset = getCurrentPhaseTick();
        }

        return ((getCurrentPhaseTick() - tickOnActiveOffset ) >= getCurrentPhase().getTickDuration());
    }

}
