package ru.somber.anomaly.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.AmaZ1nG.sound.MutableSound;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.ElectraEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

public class ElectraTileEntity extends AbstractAnomalyTileEntity {
    private static final float xMinAABB = -0.9F;
    private static final float yMinAABB = 0F;
    private static final float zMinAABB = -0.9F;
    private static final float xMaxAABB = 1.9F;
    private static final float yMaxAABB = 0.9F;
    private static final float zMaxAABB = 1.9F;

    private static final AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
    private static final AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 1);
    private static final AnomalyPhase sleepPhase = new AnomalyPhase(PhaseType.Sleep, 60);

    static {
        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(sleepPhase);
        sleepPhase.setNextPhase(defaultPhase);
    }


    @SideOnly(Side.CLIENT)
    private MutableSound idleSound;
    @SideOnly(Side.CLIENT)
    private MutableSound activeSound;


    public ElectraTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);
    }

    @Override
    protected void clientValidate() {
        ElectraEmitter emitter = new ElectraEmitter(xCoord + 0.5F, yCoord, zCoord + 0.5F);
        setEmitter(emitter);

        super.clientValidate();

        idleSound = new MutableSound(new ResourceLocation(AnomalyMod.MOD_ID + ":electra_idle"));
        idleSound.setPosition(xCoord + 0.5, yCoord, zCoord + 0.5);
        idleSound.setRepeatable(true);

        activeSound = new MutableSound(new ResourceLocation(AnomalyMod.MOD_ID + ":electra_active_2"));
        activeSound.setPosition(xCoord + 0.5, yCoord, zCoord + 0.5);
        activeSound.setRepeatable(false);
    }

    @Override
    protected void clientInvalidate() {
        super.clientInvalidate();

        idleSound.stop();
        activeSound.stop();
    }

    @Override
    protected boolean processDefaultPhase() {
        super.processDefaultPhase();
        prepareCollideEntityList(this);
        prepareCollideBoltList(this);

        if ((!listForSearchEntities.isEmpty()) || (!listForSearchBolts.isEmpty())) {
            for (EntityLivingBase entity : listForSearchEntities) {
                if (AnomalyMod.IS_SERVER) {
                    if (!(entity instanceof EntityPlayer) ||
                            !((EntityPlayer) entity).capabilities.isCreativeMode) {
                        entity.setHealth(entity.getHealth() - 10F);
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
    }

}
