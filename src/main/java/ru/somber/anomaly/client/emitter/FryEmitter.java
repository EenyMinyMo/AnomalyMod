package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.fry.BurnParticle;
import ru.somber.anomaly.client.particle.fry.FryDistortionHeatParticle;
import ru.somber.particlesystem.particle.IParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class FryEmitter extends AbstractAnomalyEmitter {


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

        IParticle particle = new FryDistortionHeatParticle(x, y, z);
        addDistortionParticleContainer(particle);
    }


    private void createBurnParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX();
        float y = getPositionY() - 0.1F;
        float z = getPositionZ();

        IParticle particle;
        particle = new BurnParticle(x, y, z);
        addParticleContainer(particle);

        for (int i = 0; i < 3; i++) {
            particle = new BurnParticle(x + (randomizer.nextFloat() * 0.08F - 0.04F),
                                        y + (randomizer.nextFloat() * 0.4F - 0.2F),
                                        z + (randomizer.nextFloat() * 0.08F - 0.04F));
            addParticleContainer(particle);
        }
    }

}
