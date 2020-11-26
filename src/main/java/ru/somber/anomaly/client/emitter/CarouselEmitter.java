package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.carousel.CarouselActiveDustParticle;
import ru.somber.anomaly.client.particle.carousel.CarouselActiveLeafParticle;
import ru.somber.anomaly.client.particle.carousel.CarouselDefaultDustParticle;
import ru.somber.anomaly.client.particle.carousel.CarouselDefaultLeafParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class CarouselEmitter extends AbstractAnomalyEmitter {

    private CarouselDefaultDustParticle[] defaultDustParticle;
    private CarouselDefaultLeafParticle[] defaultLeafParticle;

    private CarouselActiveDustParticle[] activeDustParticle;
    private CarouselActiveLeafParticle[] activeLeafParticle;


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

        Random random = SomberCommonUtil.RANDOMIZER;

        activeDustParticle = new CarouselActiveDustParticle[100];
        for (int i = 0; i < activeDustParticle.length; i++) {
            float x = getPositionX();
            float y = getPositionY() + 0.5F + random.nextFloat() * 0.05F - 0.025F;
            float z = getPositionZ();

            CarouselActiveDustParticle particle = new CarouselActiveDustParticle(x, y, z, Integer.MAX_VALUE);
            particle.setCurrentAngle(360F * i / activeDustParticle.length);
            particle.setMode(true);

            activeDustParticle[i] = particle;
            addParticleContainer(particle);
        }

        activeLeafParticle = new CarouselActiveLeafParticle[30];
        for (int i = 0; i < activeLeafParticle.length; i++) {
            float x = getPositionX();
            float y = getPositionY() + 0.5F + random.nextFloat() * 0.05F - 0.025F;
            float z = getPositionZ();

            CarouselActiveLeafParticle particle = new CarouselActiveLeafParticle(x, y, z, Integer.MAX_VALUE);
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
