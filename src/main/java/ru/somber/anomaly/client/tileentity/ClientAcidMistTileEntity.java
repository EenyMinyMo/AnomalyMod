package ru.somber.anomaly.client.tileentity;

import ru.somber.anomaly.client.emitter.AcidMistEmitter;
import ru.somber.anomaly.common.phase.AnomalyPhase;
import ru.somber.anomaly.common.phase.PhaseType;

public class ClientAcidMistTileEntity extends AbstractClientTileEntity {

    public ClientAcidMistTileEntity() {
        super();

        AcidMistEmitter emitter = new AcidMistEmitter(0, 0, 0);
        setEmitter(emitter);

        AnomalyPhase defaultPhase = new AnomalyPhase(PhaseType.Default, -1);
        AnomalyPhase activePhase = new AnomalyPhase(PhaseType.Active, 100);

        defaultPhase.setNextPhase(activePhase);
        activePhase.setNextPhase(defaultPhase);

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
