package ru.somber.anomaly.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.AmaZ1nG.sound.MutableSound;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.SteamEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

public class SteamTileEntity extends AbstractAnomalyTileEntity {
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

    @SideOnly(Side.CLIENT)
    private MutableSound defaultSound;

    public SteamTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);

        if (!AnomalyMod.IS_SERVER) {
            SteamEmitter emitter = new SteamEmitter(0, 0, 0);
            setEmitter(emitter);
        }
    }

    @Override
    protected void clientValidate() {
        super.clientValidate();

        defaultSound = new MutableSound(new ResourceLocation(AnomalyMod.MOD_ID + ":steam"));
        defaultSound.setPosition(xCoord + 0.5, yCoord, zCoord + 0.5);
        defaultSound.setRepeatable(true);
        defaultSound.play();
    }

    @Override
    protected void clientInvalidate() {
        super.clientInvalidate();

        defaultSound.stop();
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
                if (AnomalyMod.IS_SERVER && getCurrentPhaseTick() % 3 == 0) {
                    if (!(entity instanceof EntityPlayer) ||
                            !((EntityPlayer) entity).capabilities.isCreativeMode) {
                        entity.setHealth(entity.getHealth() - 2);
                    }
                }
            }
            return false;
        }
        return true;
    }
}
