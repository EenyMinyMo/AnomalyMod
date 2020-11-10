package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.fry.BurnParticle;
import ru.somber.anomaly.client.particle.fry.DistortionHeatParticle;
import ru.somber.util.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.IParticle;

import java.util.Random;

public class FryEmitter extends AbstractAnomalyEmitter {

    private boolean isActive;


    public FryEmitter(float x, float y, float z) {
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

    @Override
    public void updateDefaultPhase(int currentTick, int phaseTickDuration) {
        super.updateDefaultPhase(currentTick, phaseTickDuration);

        if (getTick() % 5 == 1) {
            createDistortionHeatParticle();
        }

    }

    @Override
    public void updateActivePhase(int currentTick, int phaseTickDuration) {
        super.updateActivePhase(currentTick, phaseTickDuration);

        if (getTick() % 5 == 1) {
            createDistortionHeatParticle();
        }
        createBurnParticle();
    }

    private void createDistortionHeatParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 0.2F - 0.1F;
        float y = getPositionY() - 0.2F;
        float z = getPositionZ() + randomizer.nextFloat() * 0.2F - 0.1F;

        IParticle particle = new DistortionHeatParticle(x, y, z);
        addDistortionParticleContainer(particle);
    }


    private void createBurnParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX();
        float y = getPositionY();
        float z = getPositionZ();

        IParticle particle = new BurnParticle(x, y, z);
        addParticleContainer(particle);
    }

}
