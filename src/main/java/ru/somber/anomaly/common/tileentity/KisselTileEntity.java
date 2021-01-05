package ru.somber.anomaly.common.tileentity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.KisselEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

public class KisselTileEntity extends AbstractAnomalyTileEntity {
    private static final float xMinAABB = -0.5F;
    private static final float yMinAABB = 0F;
    private static final float zMinAABB = -0.5F;
    private static final float xMaxAABB = 1.5F;
    private static final float yMaxAABB = 1F;
    private static final float zMaxAABB = 1.5F;


    private static final AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
    private static final AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, -1);

    static {
        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(defaultPhase);
    }


    public KisselTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);

        if (!AnomalyMod.IS_SERVER) {
            KisselEmitter emitter = new KisselEmitter(0, 0, 0);
            setEmitter(emitter);
        }
    }

    @Override
    protected boolean processDefaultPhase() {
        super.processDefaultPhase();
        prepareCollideEntityList(this);
        prepareCollideBoltList(this);

        return (! listForSearchEntities.isEmpty()) || (! listForSearchBolts.isEmpty());
    }

    @Override
    protected boolean processActivePhase() {
        super.processActivePhase();
        prepareCollideEntityList(this);
        prepareCollideBoltList(this);

        if ((! listForSearchEntities.isEmpty()) || (! listForSearchBolts.isEmpty())) {
            for (EntityLivingBase entity : listForSearchEntities) {
                if (AnomalyMod.IS_SERVER && getCurrentPhaseTick() % 5 == 0) {
                    if (!(entity instanceof EntityPlayer) ||
                            !((EntityPlayer) entity).capabilities.isCreativeMode) {
                        entity.setHealth(entity.getHealth() - 1);
                    }
                }
            }

            return false;
        } else {
            return true;
        }
    }

}
