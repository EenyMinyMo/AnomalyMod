package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.ParticleIcons;
import ru.somber.anomaly.client.particle.acidmist.AcidMistFogParticle;
import ru.somber.particlesystem.particle.IParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class AcidMistEmitter extends AbstractAnomalyEmitter {

    public AcidMistEmitter(float x, float y, float z) {
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
        if (getTick() % 10 == 1) {
            createBigFogParticle();
        }

        if (getTick() % 9 == 1) {
            createSmallFogParticle();
        }

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

    private void createBigFogParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 2.2F - 1.1F;
        float y = getPositionY() + 0.2F;
        float z = getPositionZ() + randomizer.nextFloat() * 2.2F - 1.1F;

        IParticle particle;

        particle = new AcidMistFogParticle(x, y, z, 100, 1.2F, ParticleIcons.smoke0Icon);
        addParticleContainer(particle);

        particle = new AcidMistFogParticle(x + (randomizer.nextFloat() * 0.8F - 0.4F), y, z + (randomizer.nextFloat() * 0.8F - 0.4F), 100, 1.2F, ParticleIcons.smoke0Icon);
        addParticleContainer(particle);

        particle = new AcidMistFogParticle(x + (randomizer.nextFloat() * 0.8F - 0.4F), y, z + (randomizer.nextFloat() * 0.8F - 0.4F), 100, 1.2F, ParticleIcons.smoke0Icon);
        addParticleContainer(particle);
    }

    private void createSmallFogParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 2.2F - 1.1F;
        float y = getPositionY() + 0.2F;
        float z = getPositionZ() + randomizer.nextFloat() * 2.2F - 1.1F;

        IParticle particle = new AcidMistFogParticle(x, y, z, 90, 0.6F, ParticleIcons.smoke0Icon);
        addParticleContainer(particle);

        particle = new AcidMistFogParticle(x + (randomizer.nextFloat() * 0.8F - 0.4F), y, z + (randomizer.nextFloat() * 0.8F - 0.4F), 90, 0.6F, ParticleIcons.smoke0Icon);
        addParticleContainer(particle);

        particle = new AcidMistFogParticle(x + (randomizer.nextFloat() * 0.8F - 0.4F), y, z + (randomizer.nextFloat() * 0.8F - 0.4F), 90, 0.6F, ParticleIcons.smoke0Icon);
        addParticleContainer(particle);
    }

}

