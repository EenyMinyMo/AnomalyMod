package ru.somber.anomaly.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.AmaZ1nG.sound.MutableSound;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.FryEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

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

    @SideOnly(Side.CLIENT)
    private MutableSound idleSound;
    @SideOnly(Side.CLIENT)
    private MutableSound activeSound;


    public FryTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);
    }

    @Override
    protected void clientValidate() {
        FryEmitter emitter = new FryEmitter(xCoord + 0.5F, yCoord, zCoord + 0.5F);
        setEmitter(emitter);

        super.clientValidate();

        idleSound = new MutableSound(new ResourceLocation(AnomalyMod.MOD_ID + ":fry_idle"));
        idleSound.setPosition(xCoord + 0.5, yCoord, zCoord + 0.5);
        idleSound.setRepeatable(true);

        activeSound = new MutableSound(new ResourceLocation(AnomalyMod.MOD_ID + ":fry_active"));
        activeSound.setPosition(xCoord + 0.5, yCoord, zCoord + 0.5);
        activeSound.setRepeatable(true);
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
        if (! AnomalyMod.IS_SERVER) {
            activeSound.stop();
        }
    }
}
