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

    @Override
    public void update() {
        super.update();
    }


    public void updateDefaultPhase(int currentTick, int phaseTickDuration) {
        this.update();
    }

    public void updateActivePhase(int currentTick, int phaseTickDuration) {
        this.update();
    }

    public void updateSleepPhase(int currentTick, int phaseTickDuration) {
        this.update();
    }


    protected void addParticleContainer(IParticle particle) {
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    protected void addDistortionParticleContainer(IParticle particle) {
        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(particle);
    }

}
