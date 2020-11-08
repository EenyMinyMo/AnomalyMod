package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.debug.DebugParticle;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.IParticle;

import java.util.Random;

public class DebugEmitter extends AbstractAnomalyEmitter {

    public DebugEmitter(float x, float y, float z) {
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

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        if (getTick() %  10000 == 1) {
            IParticle debugParticle = new DebugParticle(getPositionX(), getPositionY() + 0.5F, getPositionZ(), 10000);
            addParticleContainer(debugParticle);
            addParticleInEmitter(debugParticle);
        }

    }



}