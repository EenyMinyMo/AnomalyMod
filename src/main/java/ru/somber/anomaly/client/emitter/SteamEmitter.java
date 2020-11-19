package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.steam.SteamDistortionHeatParticle;
import ru.somber.anomaly.client.particle.steam.SteamParticle;
import ru.somber.particlesystem.particle.IParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class SteamEmitter extends AbstractAnomalyEmitter {


    public SteamEmitter(float x, float y, float z) {
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
        createSteamParticle();
    }

    @Override
    public void updateActivePhase(int currentTick, int phaseTickDuration) {
        super.updateActivePhase(currentTick, phaseTickDuration);

        if (getTick() % 5 == 1) {
            createDistortionHeatParticle();
        }
        createSteamParticle();
    }

    private void createDistortionHeatParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 0.2F - 0.1F;
        float y = getPositionY() - 0.2F;
        float z = getPositionZ() + randomizer.nextFloat() * 0.2F - 0.1F;

        IParticle particle = new SteamDistortionHeatParticle(x, y, z);
        addDistortionParticleContainer(particle);
    }


    private void createSteamParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX();
        float y = getPositionY();
        float z = getPositionZ();

        IParticle particle;
        particle = new SteamParticle(x, y, z);
        addParticleContainer(particle);

        particle = new SteamParticle(x + (randomizer.nextFloat() * 0.1F - 0.05F),
                                     y + (randomizer.nextFloat() * 0.1F - 0.05F),
                                     z + (randomizer.nextFloat() * 0.1F - 0.05F));
        addParticleContainer(particle);

        particle = new SteamParticle(x + (randomizer.nextFloat() * 0.1F - 0.05F),
                                    y + (randomizer.nextFloat() * 0.1F - 0.05F),
                                    z + (randomizer.nextFloat() * 0.1F - 0.05F));
        addParticleContainer(particle);
    }

}
