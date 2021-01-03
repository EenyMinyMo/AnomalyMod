package ru.somber.anomaly.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.client.emitter.AbstractAnomalyEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAnomalyTileEntity extends TileEntity {
    /** Список сущностей. Вынесен для поиска сущностей в мире через метод prepareCollidedEntities(). */
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


    /**
     * В конструктор передать относительные координаты AABB аномалии.
     * Для куба блока это будет (0, 0, 0, 1, 1, 1).
     */
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

        //произоводится обработка текущей фазы и проверка перехода на следующую фазу.
        boolean checkTransitionPhase = false;
        switch (currentPhase.getPhaseType()) {
            case Default : {    //если текущая фаза - дефолтная (фоновая)
                checkTransitionPhase = processDefaultPhase();
            } break;

            case Active : {     //если текущая фаза - активная
                checkTransitionPhase = processActivePhase();
            } break;

            case Sleep : {      //если текущая фаза - фаза сна
                checkTransitionPhase = processSleepPhase();
            } break;
        }

        //проверка на возможность перехода на следующую фазу.
        if (checkTransitionPhase) {

            //вызываем метод окончания фазы
            endCurrentPhase();

            //осуществляем сам переход.
            currentPhase = currentPhase.getNextPhase();
            currentPhaseTick = 0;

            //вызываем метод начала фазы
            startCurrentPhase();
        }

        currentPhaseTick++;
    }

    @Override
    public void validate() {
        super.validate();
        //валидация - фактическое размещение тайловой сущности в игре.
        //сюда прописывать код подготовки к работе тайловой сущности.

        //изменяем AABB аномалии, чтобы он соответствовал позиции сущности в мире.
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

        startCurrentPhase();
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
    public void onChunkUnload() {
        super.onChunkUnload();

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


    /**
     * Возвращает true, если текущее время пребывание в фазе аномалии превышает максимальную продолжительность фазы.
     */
    protected final boolean isPhaseTimeEnd() {
        return getCurrentPhase().getTickDuration() <= getCurrentPhaseTick();
    }

    /**
     * Возвращает AABB аномалии, в пределах которой аномалия может реагировать с сущностями.
     */
    protected final AxisAlignedBB getAABBBody() {
        return aabbBody;
    }

    /**
     * Возвращает текущий AABB аномалии.
     */
    protected final AnomalyPhase getCurrentPhase() {
        return currentPhase;
    }

    /**
     * Возвращает кол-во тиков с момента смены фазы аномалии.
     * Или по другому текущий тик фазы.
     */
    protected final int getCurrentPhaseTick() {
        return currentPhaseTick;
    }

    /**
     * Возвращает эмиттер частиц аномалии.
     */
    @SideOnly(Side.CLIENT)
    protected final AbstractAnomalyEmitter getEmitter() {
        return emitter;
    }

    /**
     * Устанавливает переданную фазу как текущую.
     */
    protected final void setPhase(AnomalyPhase phase) {
        this.currentPhase = phase;
    }

    /**
     * Возвращает эмиттер частиц аномалии.
     */
    @SideOnly(Side.CLIENT)
    protected final void setEmitter(AbstractAnomalyEmitter emitter) {
        this.emitter = emitter;
    }


    /**
     * Код в этом методе должен произоводить подготовку аномалии на серверной стороне.
     * Метод вызывается, когда тайловая сушность готова обрабатываться.
     */
    @SideOnly(Side.SERVER)
    protected void serverValidate() {}

    /**
     * Код в этом методе должен произоводить подготовку аномалии на клиентской стороне.
     * Метод вызывается, когда тайловая сушность готова обрабатываться.
     */
    @SideOnly(Side.CLIENT)
    protected void clientValidate() {
        emitter.setPosition(xCoord + 0.5F, yCoord, zCoord + 0.5F);
        emitter.create();
    }

    /**
     * Код в этом методе должен уничтожать внешние связи сущности на серверной стороне.
     * Метод вызывается перед моментом удаления тайловой сущности.
     */
    @SideOnly(Side.SERVER)
    protected void serverInvalidate() {}

    /**
     * Код в этом методе должен уничтожать внешние связи сущности на клиентской стороне.
     * Метод вызывается перед моментом удаления тайловой сущности.
     */
    @SideOnly(Side.CLIENT)
    protected void clientInvalidate() {
        emitter.setDie();
    }


    /**
     * Аномалия наследник должна переопределить этот метод для обработки дефолтной (фоновой) фазы.
     * Возвращает true, когда требуется перейти на следующую фазу.
     */
    protected boolean processDefaultPhase() {
        if (! AnomalyMod.IS_SERVER)  {
            getEmitter().updateDefaultPhase(getCurrentPhaseTick(), getCurrentPhase().getTickDuration());
        }

        return false;
    }

    /**
     * Аномалия наследник должна переопределить этот метод для обработки активной фазы.
     * Возвращает true, когда требуется перейти на следующую фазу.
     */
    protected boolean processActivePhase() {
        if (! AnomalyMod.IS_SERVER)  {
            getEmitter().updateActivePhase(getCurrentPhaseTick(), getCurrentPhase().getTickDuration());
        }

        return false;
    }

    /**
     * Аномалия наследник должна переопределить этот метод для обработки фазы сна (бездействия).
     * Возвращает true, когда требуется перейти на следующую фазу.
     */
    protected boolean processSleepPhase() {
        if (! AnomalyMod.IS_SERVER)  {
            getEmitter().updateSleepPhase(getCurrentPhaseTick(), getCurrentPhase().getTickDuration());
        }

        return false;
    }


    /** Вызывается, когда начинается дефолтная фаза. */
    protected void defaultPhaseStart() {
        if (! AnomalyMod.IS_SERVER)  {
            getEmitter().defaultPhaseStart();
        }
    }

    /** Вызывается, когда начинается активная фаза. */
    protected void activePhaseStart() {
        if (! AnomalyMod.IS_SERVER)  {
            getEmitter().activePhaseStart();
        }
    }

    /** Вызывается, когда начинается фаза сна. */
    protected void sleepPhaseStart() {
        if (! AnomalyMod.IS_SERVER)  {
            getEmitter().sleepPhaseStart();
        }
    }

    /** Вызывается, когда заканчивается дефолтная фаза. */
    protected void defaultPhaseEnd() {
        if (! AnomalyMod.IS_SERVER)  {
            getEmitter().defaultPhaseEnd();
        }
    }

    /** Вызывается, когда заканчивается активная фаза. */
    protected void activePhaseEnd() {
        if (! AnomalyMod.IS_SERVER)  {
            getEmitter().activePhaseEnd();
        }
    }

    /** Вызывается, когда заканчивается фаза сна. */
    protected void sleepPhaseEnd() {
        if (! AnomalyMod.IS_SERVER)  {
            getEmitter().sleepPhaseEnd();
        }
    }


    /**
     * Вызывает метод ***PhaseEnd() для фазы текущего типа.
     */
    private void endCurrentPhase() {
        switch (currentPhase.getPhaseType()) {
            case Default : {    //если сейчас закончиться дефолтная фаза
                defaultPhaseEnd();
            } break;

            case Active : {     //если сейчас закончиться активная фаза
                activePhaseEnd();
            } break;

            case Sleep : {      //если сейчас закончиться фаза сна
                sleepPhaseEnd();
            } break;
        }
    }

    /**
     * Вызывает метод ***PhaseStart() для фазы текущего типа.
     */
    private void startCurrentPhase() {
        switch (currentPhase.getPhaseType()) {
            case Default : {    //если сейчас начнется дефолтная фаза
                defaultPhaseStart();
            } break;

            case Active : {     //если сейчас начнется активная фаза
                activePhaseStart();
            } break;

            case Sleep : {      //если сейчас начнется фаза сна
                sleepPhaseStart();
            } break;
        }
    }


    /**
     * Произоводит подготовку списка сущностей в пределах аномалии.
     * Сущности ищутся через AABB аномалии в мире тайловой сушности.
     */
    protected static void prepareCollideEntityList(AbstractAnomalyTileEntity tile) {
        listForSearchEntities.clear();
        SomberCommonUtil.getEntitiesWithinAABB(tile.getWorldObj(),
                                               EntityLivingBase.class,
                                               tile.getAABBBody(),
                                               null,
                                               listForSearchEntities);
    }

}
