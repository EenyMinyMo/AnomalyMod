package ru.somber.anomaly.emitter;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ClientProxy;
import ru.somber.anomaly.particle.kisel.KiselBubbleParticle;
import ru.somber.anomaly.particle.kisel.KiselDistortionBubbleParticle;
import ru.somber.particlesystem.emitter.AbstractEmitter;
import ru.somber.particlesystem.particle.IParticle;

public class KiselEmitter extends AbstractEmitter {

    public KiselEmitter(float x, float y, float z) {
        super(x, y, z);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void update() {
        super.update();

        if (Math.random() > 0.9) {
            createColorBubbleParticle();
        }

        if (Math.random() > 0.9) {
            createDistortionBubbleParticle();
        }
    }

    @Override
    public void delete() {
        super.delete();
    }


    private void createColorBubbleParticle() {
        IParticle particle = new KiselBubbleParticle(getPositionX() - 0.5F + ((float) Math.random()), getPositionY(), getPositionZ() - 0.5F + ((float) Math.random()));

        addParticleInEmitter(particle);
        ClientProxy.getParticleManager().getParticleContainer().addParticle(particle);
    }

    private void createDistortionBubbleParticle() {
        IParticle particle = new KiselDistortionBubbleParticle(getPositionX() - 0.5F + ((float) Math.random()), getPositionY(), getPositionZ() - 0.5F + ((float) Math.random()));

        addParticleInEmitter(particle);
        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(particle);
    }

}
