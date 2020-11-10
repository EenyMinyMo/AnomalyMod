package ru.somber.anomaly.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.AbstractAnomalyEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.commonutil.SomberCommonUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAnomalyTileEntity extends TileEntity {
    protected static final List<EntityLivingBase> listForSearchEntities = new ArrayList<>();

    /** AABB аномалии, в пределах которой будут накладываться эффекты на сущности. */
    private final AxisAlignedBB aabbBody;
    /** Объект фозы аномалии, который описывает текущую фазу. */
    private AnomalyPhase currentPhase;
    /** Количество тиков, которое продолжается текущая фаза. */
    private int currentPhaseTick;

    /** Эмиттер частиц аномалии, соответствующий тайловой сущности. */
    @SideOnly(Side.CLIENT)
    private AbstractAnomalyEmitter emitter;


    public AbstractAnomalyTileEntity(float xMinAABB, float yMinAABB, float zMinAABB,
                                     float xMaxAABB, float yMaxAABB, float zMaxAABB) {
        super();

        this.aabbBody = AxisAlignedBB.getBoundingBox(xMinAABB, yMinAABB, zMinAABB,
                                                     xMaxAABB, yMaxAABB, zMaxAABB);
        this.currentPhaseTick = 0;
    }


    @Override
    public boolean canUpdate() {
        return true;
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

//            if (AnomalyMod.IS_SERVER) {
//                getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);
//            }
        } else {
            currentPhaseTick++;
        }
    }

    @Override
    public void validate() {
        super.validate();
        //валидация - фактическое размещение тайловой сущности в игре.
        //сюда прописывать код подготовки к работе тайловой сущности.

        aabbBody.minX += xCoord;
        aabbBody.minY += yCoord;
        aabbBody.minZ += zCoord;

        aabbBody.maxX += xCoord;
        aabbBody.maxY += yCoord;
        aabbBody.maxZ += zCoord;

        if (AnomalyMod.IS_SERVER) {
            serverValidate();
        } else {
            clientValidate();
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        //помечание сущности как недействительной.
        //сюда помещать код уничтожения внутренний данных сущностей и внешних зависимостей.

        if (AnomalyMod.IS_SERVER) {
            serverInvalidate();
        } else {
            clientInvalidate();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 256 * 256;
    }


    protected final void prepareCollideEntityList() {
        listForSearchEntities.clear();
        SomberCommonUtil.getEntitiesWithinAABB(getWorldObj(),
                                               EntityLivingBase.class,
                                               getAABBBody(),
                                               null,
                                               listForSearchEntities);
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

    @SideOnly(Side.CLIENT)
    protected final AbstractAnomalyEmitter getEmitter() {
        return emitter;
    }


    protected final void setPhase(AnomalyPhase phase) {
        this.currentPhase = phase;
    }

    @SideOnly(Side.CLIENT)
    protected final void setEmitter(AbstractAnomalyEmitter emitter) {
        this.emitter = emitter;
    }


    protected void serverValidate() {

    }

    protected void clientValidate() {
        emitter.setPosition(xCoord + 0.5F, yCoord, zCoord + 0.5F);
        emitter.create();
    }

    protected void serverInvalidate() {

    }

    protected void clientInvalidate() {
        emitter.setDie();
    }


    protected abstract boolean processDefaultPhase();

    protected abstract boolean processActivePhase();

    protected abstract boolean processSleepPhase();

}
