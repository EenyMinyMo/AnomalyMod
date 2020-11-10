package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.trampoline.TrampolineDistortionParticle;
import ru.somber.anomaly.client.particle.trampoline.TrampolineFlashParticle;
import ru.somber.anomaly.client.particle.trampoline.TrampolineLeafParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class TrampolineEmitter extends AbstractAnomalyEmitter {
    private static final int countDistortionParticleRecoveryTick = 45;
    private static final int countFlashParticleRecoveryTick = 20;

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
                                                    getPositionZ(),
                                                    Integer.MAX_VALUE);
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
                                                                  countDistortionParticleRecoveryTick);
            addDistortionParticleContainer(distortionParticle);
            distortionParticleRecoveryTick = countDistortionParticleRecoveryTick;
        }

        if (flashParticleRecoveryTick == 0) {
            float randomX = getPositionX() - 0.6F + randomizer.nextFloat() * 1.2F;
            float randomY = getPositionY() + 0.2F + randomizer.nextFloat() * 0.8F;
            float randomZ = getPositionZ() - 0.6F + randomizer.nextFloat() * 1.2F;
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



    }

    @Override
    public void updateSleepPhase(int currentTick, int phaseTickDuration) {
        super.updateSleepPhase(currentTick, phaseTickDuration);
    }

    private void createLeafParticle(Random randomizer) {
        float randomX = getPositionX();
        float randomY = getPositionY() + randomizer.nextFloat() * 0.05F;
        float randomZ = getPositionZ();

        TrampolineLeafParticle leafParticle = new TrampolineLeafParticle(randomX, randomY, randomZ, 300);
        addParticleContainer(leafParticle);
    }

}
