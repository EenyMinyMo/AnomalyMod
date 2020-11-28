package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.trampoline.TrampolineDistortionParticle;
import ru.somber.anomaly.client.particle.trampoline.TrampolineDustParticle;
import ru.somber.anomaly.client.particle.trampoline.TrampolineFlashParticle;
import ru.somber.anomaly.client.particle.trampoline.TrampolineLeafParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class TrampolineEmitter extends AbstractAnomalyEmitter {
    private static final int countDistortionParticleRecoveryTick = 70;
    private static final int countFlashParticleRecoveryTick = 16;

    private TrampolineFlashParticle flashParticle;
    private TrampolineDistortionParticle distortionParticle;

    private int distortionParticleRecoveryTick;
    private int flashParticleRecoveryTick;


    public TrampolineEmitter(float x, float y, float z) {
        super(x, y, z);
    }

    @Override
    public void create() {
        super.create();

        setTick(SomberCommonUtil.RANDOMIZER.nextInt(100));

        flashParticle = new TrampolineFlashParticle(getPositionX(),
                                                    getPositionY(),
                                                    getPositionZ());
        addParticleContainer(flashParticle);
    }

    @Override
    public void delete() {
        super.delete();
        flashParticle.setDie(true);
        distortionParticle.setDie(true);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void updateDefaultPhase(int currentTick, int phaseTickDuration) {
        super.updateDefaultPhase(currentTick, phaseTickDuration);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        if (distortionParticleRecoveryTick == 0) {
            distortionParticle = new TrampolineDistortionParticle(getPositionX(),
                                                                  getPositionY(),
                                                                  getPositionZ(),
                                                                  countDistortionParticleRecoveryTick - 20);
            addDistortionParticleContainer(distortionParticle);
            distortionParticleRecoveryTick = countDistortionParticleRecoveryTick;
        }

        if (flashParticleRecoveryTick == 0) {
            float randomX = getPositionX() + randomizer.nextFloat() * 1.2F - 0.6F;
            float randomY = getPositionY() + randomizer.nextFloat() * 1.0F + 0.2F;
            float randomZ = getPositionZ() + randomizer.nextFloat() * 1.2F - 0.6F;
            flashParticle.setPosition(randomX, randomY, randomZ);
            flashParticle.setVisible();

            flashParticleRecoveryTick = countFlashParticleRecoveryTick +
                    randomizer.nextInt(countFlashParticleRecoveryTick / 2);
        }

        distortionParticleRecoveryTick--;
        flashParticleRecoveryTick--;
    }

    @Override
    public void updateActivePhase(int currentTick, int phaseTickDuration) {
        super.updateActivePhase(currentTick, phaseTickDuration);

        distortionParticle.setDie(true);
        distortionParticleRecoveryTick = 0;
        flashParticle.setInvisible();

        createLeafParticles();
        createDustParticle();
    }

    @Override
    public void updateSleepPhase(int currentTick, int phaseTickDuration) {
        super.updateSleepPhase(currentTick, phaseTickDuration);
    }


    private void createLeafParticles() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float randomX = getPositionX();
        float randomY = getPositionY() + 0.05F;
        float randomZ = getPositionZ();

        int countLeafParticle = 20;
        for (int i = 0; i < countLeafParticle; i++) {
            TrampolineLeafParticle leafParticle =
                    new TrampolineLeafParticle(randomX, randomY, randomZ,
                                               360 * (i / (float) countLeafParticle), 30);

            addParticleContainer(leafParticle);
        }
    }

    private void createDustParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float randomX = getPositionX();
        float randomY = getPositionY();
        float randomZ = getPositionZ();

        int countDustParticle = 80;
        for (int i = 0; i < countDustParticle; i++) {
            TrampolineDustParticle leafParticle =
                    new TrampolineDustParticle(randomX, randomY + (randomizer.nextFloat() * 0.5F - 0.25F), randomZ,
                                               360 * (i / (float) countDustParticle), 30);

            addParticleContainer(leafParticle);
        }
    }

}
