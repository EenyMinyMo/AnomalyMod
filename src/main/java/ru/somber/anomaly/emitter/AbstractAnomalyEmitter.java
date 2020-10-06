package ru.somber.anomaly.emitter;

import ru.somber.anomaly.ClientProxy;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.emitter.AbstractEmitter;
import ru.somber.particlesystem.particle.IParticle;

import java.util.Random;

public class AbstractAnomalyEmitter extends AbstractEmitter {

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


    protected void addParticleContainer(IParticle particle) {
        addParticleInEmitter(particle);
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    protected void addDistortionParticleContainer(IParticle particle) {
        addParticleInEmitter(particle);
        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(particle);
    }

}
