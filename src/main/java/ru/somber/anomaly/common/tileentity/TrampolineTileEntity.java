package ru.somber.anomaly.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.AmaZ1nG.sound.MutableSound;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.KisselEmitter;
import ru.somber.anomaly.client.emitter.TrampolineEmitter;
import ru.somber.anomaly.common.entity.EntityBolt;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;
import ru.somber.util.commonutil.SomberCommonUtil;

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


    @SideOnly(Side.CLIENT)
    private MutableSound idleSound;
    @SideOnly(Side.CLIENT)
    private MutableSound activeSound;


    public TrampolineTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        setPhase(defaultPhase);
    }

    @Override
    protected void clientValidate() {
        TrampolineEmitter emitter = new TrampolineEmitter(xCoord + 0.5F, yCoord, zCoord + 0.5F);
        setEmitter(emitter);

        super.clientValidate();

        idleSound = new MutableSound(new ResourceLocation(AnomalyMod.MOD_ID + ":trampoline_idle"));
        idleSound.setPosition(xCoord + 0.5, yCoord, zCoord + 0.5);
        idleSound.setRepeatable(true);
        idleSound.setRepeatDelay(25 + SomberCommonUtil.RANDOMIZER.nextInt(15));

        activeSound = new MutableSound(new ResourceLocation(AnomalyMod.MOD_ID + ":trampoline_active"));
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

        if ((! listForSearchEntities.isEmpty()) || (! listForSearchBolts.isEmpty())) {
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

            for (EntityBolt entity : listForSearchBolts) {
                applyAnomalyEffect(entity);
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

    @Override
    protected void sleepPhaseStart() {
        super.sleepPhaseStart();
    }

    @Override
    protected void sleepPhaseEnd() {
        super.sleepPhaseEnd();
    }

    private void applyAnomalyEffect(Entity entity) {
        entity.addVelocity(0, 0.85, 0);
    }

}
