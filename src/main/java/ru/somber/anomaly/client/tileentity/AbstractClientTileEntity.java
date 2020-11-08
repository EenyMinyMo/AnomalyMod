package ru.somber.anomaly.client.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import ru.somber.anomaly.client.emitter.AbstractAnomalyEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

public abstract class AbstractClientTileEntity extends TileEntity {
    /** Эмиттер частиц аномалии, соответствующий тайловой сущности. */
    private AbstractAnomalyEmitter emitter;

    private AnomalyPhase defaultPhase;
    private AnomalyPhase activePhase;
    private AnomalyPhase sleepPhase;
    /** Текущий тип фазы аномалии. */
    private PhaseType currentPhase;
    /** Количество тиков, которое продолжается фаза. */
    private int currentPhaseTick;


    public AbstractClientTileEntity() {
        super();

        this.currentPhase = PhaseType.Default;
        this.currentPhaseTick = 0;
    }


    @Override
    public void validate() {
        super.validate();
        //валидация - фактическое размещение тайловой сущности в игре.
        //сюда прописывать код подготовки к работе тайловой сущности.

        //инициализируем эмиттер данными тайловой сущности.
        emitter.setPosition(xCoord + 0.5F, yCoord, zCoord + 0.5F);
        emitter.create();
//        ClientProxy.getEmitterContainer().addEmitter(emitter);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        //помечание сущности как недействительной.
        //сюда помещать код уничтожения внутренний данных сущностей и внешних зависимостей.

        emitter.setDie();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        switch (currentPhase) {
            case Default : {
                processDefaultPhase();
            } break;

            case Active : {
                processActivePhase();
            } break;

            case Sleep : {
                processSleepPhase();
            } break;
        }

        currentPhaseTick++;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        int phaseOrdinal = nbt.getInteger("phase");
        PhaseType[] phases = PhaseType.values();
        currentPhase = phases[phaseOrdinal];

        currentPhaseTick = 0;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 256 * 256;
    }


    protected final AbstractAnomalyEmitter getEmitter() {
        return emitter;
    }

    protected final PhaseType getCurrentPhaseType() {
        return currentPhase;
    }

    protected final int getCurrentPhaseTick() {
        return currentPhaseTick;
    }

    protected final void setEmitter(AbstractAnomalyEmitter emitter) {
        this.emitter = emitter;
    }

    protected final void setDefaultPhase(AnomalyPhase defaultPhase) {
        this.defaultPhase = defaultPhase;
    }

    protected final void setActivePhase(AnomalyPhase activePhase) {
        this.activePhase = activePhase;
    }

    protected final void setSleepPhase(AnomalyPhase sleepPhase) {
        this.sleepPhase = sleepPhase;
    }


    protected void processDefaultPhase() {
        getEmitter().updateDefaultPhase(getCurrentPhaseTick(), defaultPhase.getTickDuration());
    }

    protected void processActivePhase() {
        getEmitter().updateActivePhase(getCurrentPhaseTick(), activePhase.getTickDuration());
    }

    protected void processSleepPhase() {
        getEmitter().updateSleepPhase(getCurrentPhaseTick(), sleepPhase.getTickDuration());
    }

}
