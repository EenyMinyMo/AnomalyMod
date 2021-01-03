package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.ClientProxy;
import ru.somber.anomaly.client.ParticleIcons;
import ru.somber.anomaly.client.particle.kissel.*;
import ru.somber.particlesystem.particle.IParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class KisselEmitter extends AbstractAnomalyEmitter {

    public KisselEmitter(float x, float y, float z) {
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
        updateIdleMode();
    }

    @Override
    public void updateActivePhase(int currentTick, int phaseTickDuration) {
        updateIdleMode();
        updateActiveMode();
    }

    @Override
    public void updateSleepPhase(int currentTick, int phaseTickDuration) {}


    private void updateIdleMode() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        if (randomizer.nextFloat() > 0.94F) {
            createAcidSprayParticle(randomizer);
        }

        if (randomizer.nextFloat() > 0.94F) {
            createDistortionBubbleParticle(randomizer);
        }

        if (randomizer.nextFloat() > 0.95F) {
            createEvaporationParticle(randomizer);
        }

        if (randomizer.nextFloat() > 0.93F) {
            createFogParticle(randomizer);
        }

        if (randomizer.nextFloat() > 0.93F) {
            createBigFogParticle(randomizer);
        }
    }

    private void updateActiveMode() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        if (randomizer.nextFloat() > 0.7F) {
            createBigActiveParticle(randomizer);
            createAcidSprayParticle(randomizer);
        }

        if (randomizer.nextFloat() > 0.7F) {
            createAcidSprayParticle(randomizer);
        }
    }

    private void createAcidSprayParticle(Random randomizer) {
        float x = getPositionX() + randomizer.nextFloat() * 2 - 1;
        float y = getPositionY() + 0.02F;
        float z = getPositionZ() + randomizer.nextFloat() * 2 - 1;

        IParticle particle = new KisselBubbleParticle(x, y, z);
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);

        //волна размытия под брызгами
        particle = new KisselDistortionWaveParticle(x, y, z, 20 + randomizer.nextInt(5));
        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createEvaporationParticle(Random randomizer) {
        float x = getPositionX() + randomizer.nextFloat() * 1.8F - 0.9F;
        float y = getPositionY();
        float z = getPositionZ() + randomizer.nextFloat() * 1.8F - 0.9F;

        IParticle particle = new KisselEvaporationParticle(x, y, z);

        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createFogParticle(Random randomizer) {
        float x = getPositionX() + randomizer.nextFloat() * 1.7F - 0.85F;
        float y = getPositionY();
        float z = getPositionZ() + randomizer.nextFloat() * 1.7F - 0.85F;

        IParticle particle =
                new KisselFogParticle(x, y, z, 60 + randomizer.nextInt(5), 0.8F, ParticleIcons.smoke0Icon);

        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createBigFogParticle(Random randomizer) {
        float x = getPositionX() + randomizer.nextFloat() * 1.6F - 0.8F;
        float y = getPositionY() + 0.002F;
        float z = getPositionZ() + randomizer.nextFloat() * 1.6F - 0.8F;

        IParticle particle =
                new KisselFogParticle(x, y, z, 100 + randomizer.nextInt(5), 1.5F, ParticleIcons.smoke0Icon);

        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createBigActiveParticle(Random randomizer) {
        float x = getPositionX() + randomizer.nextFloat() * 2 - 1;
        float y = getPositionY();
        float z = getPositionZ() + randomizer.nextFloat() * 2 - 1;

        IParticle particle = new KisselBigActiveParticle(x, y, z);

        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createDistortionBubbleParticle(Random randomizer) {
        float x = getPositionX() - 0.5F + randomizer.nextFloat();
        float y = getPositionY();
        float z = getPositionZ() - 0.5F + randomizer.nextFloat();

        IParticle particle = new KisselDistortionBubbleParticle(x, y, z);

        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createDistortionWaveParticle(float x, float y, float z, int maxLifeTime) {
        IParticle particle = new KisselDistortionWaveParticle(x, y, z, maxLifeTime);

        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(particle);
    }

}
