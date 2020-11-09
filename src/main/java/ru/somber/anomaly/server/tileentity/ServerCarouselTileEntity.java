package ru.somber.anomaly.server.tileentity;

import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

public class ServerCarouselTileEntity extends AbstractServerTileEntity {
    private static final float xMinAABB = -1F;
    private static final float yMinAABB = 0F;
    private static final float zMinAABB = -1F;
    private static final float xMaxAABB = 2F;
    private static final float yMaxAABB = 1F;
    private static final float zMaxAABB = 2F;


    public ServerCarouselTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
        AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, -1);
        AnomalyPhase sleepPhase = new AnomalyPhase(PhaseType.Active, 100);

        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(sleepPhase);
        sleepPhase.setNextPhase(defaultPhase);

        setPhase(defaultPhase);
    }


    @Override
    protected boolean processDefaultPhase() {
        return false;
    }

    @Override
    protected boolean processActivePhase() {
        return true;
    }

    @Override
    protected boolean processSleepPhase() {
        return true;
    }

}