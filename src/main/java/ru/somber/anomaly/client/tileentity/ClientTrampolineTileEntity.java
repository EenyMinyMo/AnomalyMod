package ru.somber.anomaly.client.tileentity;

import ru.somber.anomaly.client.emitter.TrampolineEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

public class ClientTrampolineTileEntity extends AbstractClientTileEntity {

    private static final AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
    private static final AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 100);

    static {
        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(defaultPhase);
    }


    public ClientTrampolineTileEntity() {
        super();

        TrampolineEmitter emitter = new TrampolineEmitter(0, 0, 0);
        setEmitter(emitter);

        setDefaultPhase(defaultPhase);
        setActivePhase(defaultPhase);
    }

    @Override
    protected void processDefaultPhase() {
        super.processDefaultPhase();
    }

    @Override
    protected void processActivePhase() {
        super.processActivePhase();
    }

    @Override
    protected void processSleepPhase() {}

}