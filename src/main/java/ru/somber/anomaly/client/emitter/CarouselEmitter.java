package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.carousel.CarouselDefaultDustParticle;
import ru.somber.anomaly.client.particle.carousel.CarouselDefaultLeafParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class CarouselEmitter extends AbstractAnomalyEmitter {

    private CarouselDefaultDustParticle[] defaultDustParticle;
    private CarouselDefaultLeafParticle[] defaultLeafParticle;


    public CarouselEmitter(float x, float y, float z) {
        super(x, y, z);


    }


    @Override
    public void create() {
        super.create();

        Random random = SomberCommonUtil.RANDOMIZER;

        defaultDustParticle = new CarouselDefaultDustParticle[35];
        defaultLeafParticle = new CarouselDefaultLeafParticle[15];

        for (int i = 0; i < defaultDustParticle.length; i++) {
            float x = getPositionX();
            float y = getPositionY() + 0.5F + random.nextFloat() * 0.05F - 0.025F;
            float z = getPositionZ();

            CarouselDefaultDustParticle particle = new CarouselDefaultDustParticle(x, y, z, Integer.MAX_VALUE);
            particle.setCurrentAngle(360F * i / defaultDustParticle.length);

            defaultDustParticle[i] = particle;
            addParticleContainer(particle);
        }

        for (int i = 0; i < defaultLeafParticle.length; i++) {
            float x = getPositionX();
            float y = getPositionY() + 0.5F + random.nextFloat() * 0.8F - 0.4F;
            float z = getPositionZ();

            CarouselDefaultLeafParticle particle = new CarouselDefaultLeafParticle(x, y, z, Integer.MAX_VALUE);
            particle.setCurrentAngle(360F * i / defaultLeafParticle.length);

            defaultLeafParticle[i] = particle;
            addParticleContainer(particle);
        }
    }

    @Override
    public void delete() {
        super.delete();

        for (int i = 0; i < defaultDustParticle.length; i++) {
            defaultDustParticle[i].setDie(true);
        }

        for (int i = 0; i < defaultLeafParticle.length; i++) {
            defaultLeafParticle[i].setDie(true);
        }

        defaultDustParticle = null;
        defaultLeafParticle = null;
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;

    }

    @Override
    public void updateDefaultPhase(int currentTick, int phaseTickDuration) {
        super.updateDefaultPhase(currentTick, phaseTickDuration);



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

        for (int i = 0; i < defaultDustParticle.length; i++) {
            defaultDustParticle[i].setVisible();
        }

        for (int i = 0; i < defaultLeafParticle.length; i++) {
            defaultLeafParticle[i].setVisible();
        }
    }

    @Override
    public void activePhaseStart() {
        super.activePhaseStart();
    }

    @Override
    public void sleepPhaseStart() {
        super.sleepPhaseStart();
    }

    @Override
    public void defaultPhaseEnd() {
        super.defaultPhaseEnd();

        for (int i = 0; i < defaultDustParticle.length; i++) {
            defaultDustParticle[i].setInvisible();
        }

        for (int i = 0; i < defaultLeafParticle.length; i++) {
            defaultLeafParticle[i].setInvisible();
        }
    }

    @Override
    public void activePhaseEnd() {
        super.activePhaseEnd();
    }

    @Override
    public void sleepPhaseEnd() {
        super.sleepPhaseEnd();
    }
}
