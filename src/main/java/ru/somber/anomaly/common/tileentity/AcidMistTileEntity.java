package ru.somber.anomaly.common.tileentity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.AcidMistEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

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
        super.processDefaultPhase();
        prepareCollideEntityList(this);
        prepareCollideBoltList(this);

        return (! listForSearchEntities.isEmpty()) || (! listForSearchBolts.isEmpty());
    }

    @Override
    protected boolean processActivePhase() {
        super.processActivePhase();
        prepareCollideEntityList(this);

        if ((! listForSearchEntities.isEmpty()) || (! listForSearchBolts.isEmpty()))  {
            for (EntityLivingBase entity : listForSearchEntities) {
                if (AnomalyMod.IS_SERVER && getCurrentPhaseTick() % 10 == 0) {
                    if (!(entity instanceof EntityPlayer) ||
                            !((EntityPlayer) entity).capabilities.isCreativeMode) {
                        entity.setHealth(entity.getHealth() - 2F);
                    }
                }
            }

            return false;
        } else {
            return true;
        }
    }


}
