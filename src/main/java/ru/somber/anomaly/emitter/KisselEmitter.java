package ru.somber.anomaly.emitter;

import ru.somber.anomaly.ClientProxy;
import ru.somber.anomaly.particle.kissel.*;
import ru.somber.particlesystem.emitter.AbstractEmitter;
import ru.somber.particlesystem.particle.IParticle;

public class KisselEmitter extends AbstractEmitter {

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

        updateIdleMode();
//        updateActiveMode();
    }


    private void updateIdleMode() {
        if (Math.random() > 0.95) {
            createColorBubbleParticle();
        }

        if (Math.random() > 0.95) {
            createDistortionBubbleParticle();
        }

        if (Math.random() > 0.965) {
            createEvaporationParticle();
        }

        if (Math.random() > 0.97) {
            createFogParticle();
        }

        if (Math.random() > 0.97) {
            createBigFogParticle();
        }
    }

    private void updateActiveMode() {
        if (Math.random() > 0.75) {
            createBigActiveParticle();
            createColorBubbleParticle();
        }

        if (Math.random() > 0.75) {
            createColorBubbleParticle();
        }
    }

    private void createColorBubbleParticle() {
        float x = getPositionX() - 0.5F + ((float) Math.random());
        float y = getPositionY();
        float z = getPositionZ() - 0.5F + ((float) Math.random());

        IParticle particle = new KiselBubbleParticle(x, y, z);

        addParticleInEmitter(particle);
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);

        createDistortionWaveParticle(x, y + 0.002F, z, 20 + ((int) (Math.random() * 5)));
//        createFoamAnimParticle(x, y + 0.005F, z, 20 + ((int) (Math.random() * 5)));
    }

    private void createEvaporationParticle() {
        float x = getPositionX() - 0.5F + ((float) Math.random());
        float y = getPositionY();
        float z = getPositionZ() - 0.5F + ((float) Math.random());

        IParticle particle = new KiselEvaporationParticle(x, y, z);

        addParticleInEmitter(particle);
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createFogParticle() {
        float x = getPositionX() - 0.7F + ((float) Math.random()) * 1.4F;
        float y = getPositionY();
        float z = getPositionZ() - 0.7F + ((float) Math.random()) * 1.4F;

        IParticle particle = new KiselFogParticle(x, y, z);

        addParticleInEmitter(particle);
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createBigFogParticle() {
        float x = getPositionX() - 0.5F + ((float) Math.random());
        float y = getPositionY();
        float z = getPositionZ() - 0.5F + ((float) Math.random());

        IParticle particle = new KiselBigFogParticle(x, y + 0.002F, z, 100 + ((int) (Math.random() * 5)));

        addParticleInEmitter(particle);
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createBigActiveParticle() {
        float x = getPositionX() - 0.5F + ((float) Math.random());
        float y = getPositionY();
        float z = getPositionZ() - 0.5F + ((float) Math.random());

        IParticle particle = new KiselBigActiveParticle(x, y, z);

        addParticleInEmitter(particle);
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }


    private void createDistortionBubbleParticle() {
        float x = getPositionX() - 0.5F + ((float) Math.random());
        float y = getPositionY();
        float z = getPositionZ() - 0.5F + ((float) Math.random());

        IParticle particle = new KiselDistortionBubbleParticle(x, y, z);

        addParticleInEmitter(particle);
        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createDistortionWaveParticle(float x, float y, float z, int maxLifeTime) {
        IParticle particle = new KiselDistortionWaveParticle(x, y, z, maxLifeTime);

        addParticleInEmitter(particle);
        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(particle);
    }

}
