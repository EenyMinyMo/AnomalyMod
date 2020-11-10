package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.ClientProxy;
import ru.somber.anomaly.client.particle.kissel.*;
import ru.somber.util.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.IParticle;

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

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        updateIdleMode(randomizer);
//        updateActiveMode(randomizer);
    }


    private void updateIdleMode(Random randomizer) {
        if (randomizer.nextFloat() > 0.95F) {
            createColorBubbleParticle(randomizer);
        }

        if (randomizer.nextFloat() > 0.95F) {
            createDistortionBubbleParticle(randomizer);
        }

        if (randomizer.nextFloat() > 0.965F) {
            createEvaporationParticle(randomizer);
        }

        if (randomizer.nextFloat() > 0.97F) {
            createFogParticle(randomizer);
        }

        if (randomizer.nextFloat() > 0.97F) {
            createBigFogParticle(randomizer);
        }
    }

    private void updateActiveMode(Random randomizer) {
        if (randomizer.nextFloat() > 0.75F) {
            createBigActiveParticle(randomizer);
            createColorBubbleParticle(randomizer);
        }

        if (randomizer.nextFloat() > 0.75F) {
            createColorBubbleParticle(randomizer);
        }
    }

    private void createColorBubbleParticle(Random randomizer) {
        float x = getPositionX() - 0.5F + randomizer.nextFloat();
        float y = getPositionY();
        float z = getPositionZ() - 0.5F + randomizer.nextFloat();

        IParticle particle = new KisselBubbleParticle(x, y, z);

        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);

        createDistortionWaveParticle(x, y + 0.002F, z, 20 + randomizer.nextInt(5));
//        createFoamAnimParticle(x, y + 0.005F, z, 20 + ((int) (Math.random() * 5)));
    }

    private void createEvaporationParticle(Random randomizer) {
        float x = getPositionX() - 0.5F + randomizer.nextFloat();
        float y = getPositionY();
        float z = getPositionZ() - 0.5F + randomizer.nextFloat();

        IParticle particle = new KisselEvaporationParticle(x, y, z);

        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createFogParticle(Random randomizer) {
        float x = getPositionX() - 0.7F + randomizer.nextFloat() * 1.4F;
        float y = getPositionY();
        float z = getPositionZ() - 0.7F + randomizer.nextFloat() * 1.4F;

        IParticle particle = new KisselFogParticle(x, y, z);

        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createBigFogParticle(Random randomizer) {
        float x = getPositionX() - 0.5F + ((float) Math.random());
        float y = getPositionY();
        float z = getPositionZ() - 0.5F + ((float) Math.random());

        IParticle particle = new KisselBigFogParticle(x, y + 0.002F, z, 100 + randomizer.nextInt(5));

        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createBigActiveParticle(Random randomizer) {
        float x = getPositionX() - 0.5F + randomizer.nextFloat();
        float y = getPositionY();
        float z = getPositionZ() - 0.5F + randomizer.nextFloat();

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
