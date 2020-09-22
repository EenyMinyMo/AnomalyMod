package ru.somber.anomaly.emitter;

import ru.somber.anomaly.ClientProxy;
import ru.somber.particlesystem.emitter.AbstractEmitter;
import ru.somber.particlesystem.particle.IParticle;

public class AbstractAnomalyEmitter extends AbstractEmitter {

    public AbstractAnomalyEmitter(float x, float y, float z) {
        super(x, y, z);
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


    protected void addParticleContainer(IParticle particle) {
        addParticleInEmitter(particle);
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    protected void addDistortionParticleContainer(IParticle particle) {
        addParticleInEmitter(particle);
        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(particle);
    }

}
