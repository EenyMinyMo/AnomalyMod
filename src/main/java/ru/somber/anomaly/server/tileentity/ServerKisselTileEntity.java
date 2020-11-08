package ru.somber.anomaly.server.tileentity;

import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

public class ServerKisselTileEntity extends AbstractServerTileEntity {
    private static final float xMinAABB = 0F;
    private static final float yMinAABB = 0F;
    private static final float zMinAABB = 0F;
    private static final float xMaxAABB = 1F;
    private static final float yMaxAABB = 1F;
    private static final float zMaxAABB = 1F;


    public ServerKisselTileEntity() {
        super(xMinAABB, yMinAABB, zMinAABB,
              xMaxAABB, yMaxAABB, zMaxAABB);

        AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
        AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 100);

        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(defaultPhase);

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
