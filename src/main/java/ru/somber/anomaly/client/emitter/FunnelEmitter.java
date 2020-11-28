package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.funnel.*;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class FunnelEmitter extends AbstractAnomalyEmitter {
    private static final int countTickDistortionParticleRecovery = 60;
    private static final int countTickFlashParticleRecovery = 120;


    private FunnelDistortionParticle distortionParticle;
    private FunnelFlashParticle flashParticle;
    private FunnelLeafParticle[] leafParticles;

    private FunnelActiveDustParticle[] activeDustParticle;
    private FunnelActiveLeafParticle[] activeLeafParticle;

    private int distortionParticleRecoveryTick;
    private int flashParticleRecoveryTick;


    public FunnelEmitter(float x, float y, float z) {
        super(x, y, z);
    }

    @Override
    public void create() {
        super.create();

        setTick(SomberCommonUtil.RANDOMIZER.nextInt(100));

        flashParticle = new FunnelFlashParticle(getPositionX(),
                                                getPositionY(),
                                                getPositionZ());
        addParticleContainer(flashParticle);

        distortionParticle = new FunnelDistortionParticle(getPositionX(),
                                                          getPositionY(),
                                                          getPositionZ());
        addDistortionParticleContainer(distortionParticle);


        float factor = SomberCommonUtil.RANDOMIZER.nextFloat();
        distortionParticleRecoveryTick = (int) (countTickDistortionParticleRecovery * factor);

        factor += 0.5F;
        factor %= 1.0F;
        distortionParticleRecoveryTick = (int) (countTickFlashParticleRecovery * factor);

        leafParticles = new FunnelLeafParticle[8];
        for (int i = 0; i < leafParticles.length; i++) {
            FunnelLeafParticle leafParticle = new FunnelLeafParticle(getPositionX(), getPositionY(), getPositionZ());

            leafParticles[i] = leafParticle;
            addParticleContainer(leafParticle);
        }
    }

    @Override
    public void delete() {
        super.delete();

        flashParticle.setDie(true);
        distortionParticle.setDie(true);

        for (int i = 0; i < leafParticles.length; i++) {
            leafParticles[i].setDie(true);
        }
        leafParticles = null;

        if (activeDustParticle != null) {
            for (int i = 0; i < activeDustParticle.length; i++) {
                activeDustParticle[i].setDie(true);
            }
            activeDustParticle = null;
        }

        if (activeLeafParticle != null) {
            for (int i = 0; i < activeLeafParticle.length; i++) {
                activeLeafParticle[i].setDie(true);
            }
            activeLeafParticle = null;
        }
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
            distortionParticle.setVisible();

            distortionParticleRecoveryTick = countTickDistortionParticleRecovery;
        }

        if (flashParticleRecoveryTick == 0) {
            flashParticle.setVisible();

            flashParticleRecoveryTick = countTickFlashParticleRecovery;
        }

        distortionParticleRecoveryTick--;
        flashParticleRecoveryTick--;
    }

    @Override
    public void updateActivePhase(int currentTick, int phaseTickDuration) {
        super.updateActivePhase(currentTick, phaseTickDuration);
    }

    @Override
    public void updateSleepPhase(int currentTick, int phaseTickDuration) {
        super.updateSleepPhase(currentTick, phaseTickDuration);
    }


    @Override
    public void defaultPhaseStart() {
        super.defaultPhaseStart();

        distortionParticleRecoveryTick = 0;
        flashParticleRecoveryTick = countTickFlashParticleRecovery / 2;

        for (int i = 0; i < leafParticles.length; i++) {
            leafParticles[i].setVisible();
        }
    }

    @Override
    public void activePhaseStart() {
        super.activePhaseStart();

        Random random = SomberCommonUtil.RANDOMIZER;

        activeDustParticle = new FunnelActiveDustParticle[100];
        for (int i = 0; i < activeDustParticle.length; i++) {
            float x = getPositionX();
            float y = getPositionY() + 0.5F + random.nextFloat() * 0.05F - 0.025F;
            float z = getPositionZ();

            FunnelActiveDustParticle particle = new FunnelActiveDustParticle(x, y, z, Integer.MAX_VALUE);
            particle.setCurrentAngle(360F * i / activeDustParticle.length);
            particle.setMode(true);

            activeDustParticle[i] = particle;
            addParticleContainer(particle);
        }

        activeLeafParticle = new FunnelActiveLeafParticle[30];
        for (int i = 0; i < activeLeafParticle.length; i++) {
            float x = getPositionX();
            float y = getPositionY() + 0.5F + random.nextFloat() * 0.05F - 0.025F;
            float z = getPositionZ();

            FunnelActiveLeafParticle particle = new FunnelActiveLeafParticle(x, y, z, Integer.MAX_VALUE);
            particle.setCurrentAngle(360F * i / activeLeafParticle.length);
            particle.setMode(true);

            activeLeafParticle[i] = particle;
            addParticleContainer(particle);
        }
    }

    @Override
    public void sleepPhaseStart() {
        super.sleepPhaseStart();
    }

    @Override
    public void defaultPhaseEnd() {
        super.defaultPhaseEnd();

        distortionParticle.setInvisible();
        flashParticle.setInvisible();

        for (int i = 0; i < leafParticles.length; i++) {
            leafParticles[i].setInvisible();
        }
    }

    @Override
    public void activePhaseEnd() {
        super.activePhaseEnd();

        for (int i = 0; i < activeDustParticle.length; i++) {
            activeDustParticle[i].setMode(false);
        }

        for (int i = 0; i < activeLeafParticle.length; i++) {
            activeLeafParticle[i].setMode(false);
        }
    }

    @Override
    public void sleepPhaseEnd() {
        super.sleepPhaseEnd();

        for (int i = 0; i < activeDustParticle.length; i++) {
            activeDustParticle[i].setDie(true);
        }
        activeDustParticle = null;

        for (int i = 0; i < activeLeafParticle.length; i++) {
            activeLeafParticle[i].setDie(true);
        }
        activeLeafParticle = null;
    }

}