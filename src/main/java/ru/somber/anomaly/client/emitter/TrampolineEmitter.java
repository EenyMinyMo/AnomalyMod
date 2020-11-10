package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.trampoline.TrampolineDistortionParticle;
import ru.somber.anomaly.client.particle.trampoline.TrampolineFlashParticle;
import ru.somber.anomaly.client.particle.trampoline.TrampolineLeafParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class TrampolineEmitter extends AbstractAnomalyEmitter {

    private TrampolineFlashParticle flashParticle;
    private boolean particleInit;
    private int deltaTimeFlashParticle;

    public TrampolineEmitter(float x, float y, float z) {
        super(x, y, z);

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        deltaTimeFlashParticle = 20 + randomizer.nextInt(20);
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

        if (! particleInit) {
            particleInit = true;
            flashParticle = createFlashParticle(randomizer);
            addParticleContainer(flashParticle);
        }


        updateIdleParticle(randomizer);

        if (getTick() % 30 == 0) {
            createDistortionParticle(randomizer);
        }


        if (randomizer.nextFloat() > 0.97F) {
            createLeafParticle(randomizer);
        }

    }

    private void updateIdleParticle(Random randomizer) {
        if (getTick() % deltaTimeFlashParticle == 0) {
            float randomX = getPositionX() - 0.6F + randomizer.nextFloat() * 1.2F;
            float randomY = getPositionY() + 0.2F + randomizer.nextFloat() * 0.8F;
            float randomZ = getPositionZ() - 0.6F + randomizer.nextFloat() * 1.2F;
            flashParticle.setPosition(randomX, randomY, randomZ);
            flashParticle.setVisible();

            deltaTimeFlashParticle = 30 + randomizer.nextInt(20);
        }
    }

    private void createDistortionParticle(Random randomizer) {
        int maxLifeTime = 30;

        TrampolineDistortionParticle distortionParticle = new TrampolineDistortionParticle(getPositionX(), getPositionY(), getPositionZ(), maxLifeTime);

        addDistortionParticleContainer(distortionParticle);
    }

    private TrampolineFlashParticle createFlashParticle(Random randomizer) {
        TrampolineFlashParticle flashParticle = new TrampolineFlashParticle(getPositionX(), getPositionY(), getPositionZ(), Integer.MAX_VALUE);

        return flashParticle;
    }

    private void createLeafParticle(Random randomizer) {
        float randomX = getPositionX();
        float randomY = getPositionY() + randomizer.nextFloat() * 0.05F - 0.0F;
        float randomZ = getPositionZ();

        TrampolineLeafParticle leafParticle = new TrampolineLeafParticle(randomX, randomY, randomZ, 300);
        addParticleContainer(leafParticle);
    }

}
