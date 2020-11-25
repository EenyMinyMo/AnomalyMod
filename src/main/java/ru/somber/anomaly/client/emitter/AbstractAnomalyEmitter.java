package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.ClientProxy;
import ru.somber.particlesystem.emitter.AbstractEmitter;
import ru.somber.particlesystem.particle.IParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public abstract class AbstractAnomalyEmitter extends AbstractEmitter {

    public AbstractAnomalyEmitter(float x, float y, float z) {
        super(x, y, z);

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        setTick(randomizer.nextInt(1000));
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void delete() {
        super.delete();
    }

    /**
     * Обновляет эмиттер аномалии. Будет вызываться из методов обновления текущей фазы.
     * Т.е. сюда писать код, общий для всех фаз, т.к. он будет вызываться для всех фаз.
     */
    @Override
    public void update() {
        super.update();
    }


    /**
     * Вызывается, когда обновляется дефолтная фаза.
     */
    public void updateDefaultPhase(int currentTick, int phaseTickDuration) {
        this.update();
    }

    /**
     * Вызывается, когда обновляется активная фаза.
     */
    public void updateActivePhase(int currentTick, int phaseTickDuration) {
        this.update();
    }

    /**
     * Вызывается, когда обновляется фаза сна.
     */
    public void updateSleepPhase(int currentTick, int phaseTickDuration) {
        this.update();
    }


    /**
     * Вызывается, когда дефолтная фаза начинается.
     */
    public void defaultPhaseStart() {}

    /**
     * Вызывается, когда активная фаза начинается.
     */
    public void activePhaseStart() {}

    /**
     * Вызывается, когда фаза сна начинается.
     */
    public void sleepPhaseStart() {}

    /**
     * Вызывается, когда дефолтная фаза заканичивается (переход на другую фазу).
     */
    public void defaultPhaseEnd() {}

    /**
     * Вызывается, когда активная фаза заканичивается (переход на другую фазу).
     */
    public void activePhaseEnd() {}

    /**
     * Вызывается, когда фаза сна заканичивается (переход на другую фазу).
     */
    public void sleepPhaseEnd() {}


    protected void addParticleContainer(IParticle particle) {
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    protected void addDistortionParticleContainer(IParticle particle) {
        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(particle);
    }

}
