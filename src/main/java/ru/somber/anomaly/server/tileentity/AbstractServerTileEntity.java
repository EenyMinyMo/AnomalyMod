package ru.somber.anomaly.server.tileentity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import ru.somber.anomaly.common.phase.AnomalyPhase;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractServerTileEntity extends TileEntity {
    protected static final List<EntityLivingBase> listFoSearchEntities = new ArrayList<>();

    /** AABB аномалии, в пределах которой будут накладываться эффекты на сущности. */
    private final AxisAlignedBB aabbBody;
    /** Объект фозы аномалии, который описывает текущую фазу. */
    private AnomalyPhase currentPhase;
    /** Количество тиков, которое продолжается фаза. */
    private int currentPhaseTick;


    /**
     * В конструктор серверной тайловой сущности передавать локальные координаты тела AABB аномалии.
     * Допустим если аномалия ограниченина одним блоком, то передать (0F, 0F, 0F, 1F, 1F, 1F).
     * По переданным координатам строится AABB аномалии.
     */
    public AbstractServerTileEntity(float xMinAABB, float yMinAABB, float zMinAABB,
                                    float xMaxAABB, float yMaxAABB, float zMaxAABB) {
        super();

        this.aabbBody = AxisAlignedBB.getBoundingBox(xMinAABB, yMinAABB, zMinAABB,
                                                xMaxAABB, yMaxAABB, zMaxAABB);
        this.currentPhaseTick = 0;
    }


    @Override
    public void validate() {
        super.validate();
        //валидация - фактическое размещение тайловой сущности в игре.
        //сюда прописывать код подготовки к работе тайловой сущности.

        //здесь устаналиваем координаты AABB аномалии в системе координат мира,
        //т.к. до этого момента координаты сущности не были установлены.
        aabbBody.minX += xCoord;
        aabbBody.minY += yCoord;
        aabbBody.minZ += zCoord;

        aabbBody.maxX += xCoord;
        aabbBody.maxY += yCoord;
        aabbBody.maxZ += zCoord;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        //помечание сущности как недействительной.
        //сюда помещать код уничтожения внутренний данных сущностей и внешних зависимостей.
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        boolean checkTransitionPhase = false;
        switch (currentPhase.getPhaseType()) {
            case Default : {
                checkTransitionPhase = processDefaultPhase();
            } break;

            case Active : {
                checkTransitionPhase = processActivePhase();
            } break;

            case Sleep : {
                checkTransitionPhase = processSleepPhase();
            } break;
        }
        if (checkTransitionPhase) {
            currentPhase = currentPhase.getNextPhase();
            currentPhaseTick = 0;

            getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);
        }

        currentPhaseTick++;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setInteger("phase",currentPhase.getPhaseType().ordinal());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 10, nbt);
    }


    protected final void setPhase(AnomalyPhase phase) {
        this.currentPhase = phase;
    }

    protected final AxisAlignedBB getAABBBody() {
        return aabbBody;
    }

    protected final AnomalyPhase getCurrentPhase() {
        return currentPhase;
    }

    protected final int getCurrentPhaseTick() {
        return currentPhaseTick;
    }


    protected abstract boolean processDefaultPhase();

    protected abstract boolean processActivePhase();

    protected abstract boolean processSleepPhase();

}
