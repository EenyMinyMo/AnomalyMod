package ru.somber.anomaly.common.phase;

public class AnomalyPhase {
    private final PhaseType currentPhase;
    private final int tickDuration;
    private AnomalyPhase nextPhase;

    public AnomalyPhase(PhaseType currentPhase, int tickDuration) {
        this.currentPhase = currentPhase;
        this.tickDuration = tickDuration;
    }

    public int getTickDuration() {
        return tickDuration;
    }

    public PhaseType getPhaseType() {
        return currentPhase;
    }

    public AnomalyPhase getNextPhase() {
        return nextPhase;
    }

    public void setNextPhase(AnomalyPhase nextPhase) {
        this.nextPhase = nextPhase;
    }

}
