package ru.somber.anomaly.emitter;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.particle.kisel.KiselBubbleParticle;
import ru.somber.particlesystem.container.IParticleContainer;
import ru.somber.particlesystem.emitter.AbstractEmitter;
import ru.somber.particlesystem.particle.AbstractParticle;
import test.particles.TestSphericalParticle;

public class KiselEmitter extends AbstractEmitter {

    public KiselEmitter(float x, float y, float z) {
        super(x, y, z);
    }

    @Override
    public void create(IParticleContainer container) {
        super.create(container);
    }

    @Override
    public void update() {
        super.update();

        AbstractParticle particle = null;
        if (getTick() % 500 == 0) {
            particle = new KiselBubbleParticle(
                    new Vector3f(getPositionX() - 0.5F + ((float) Math.random()), getPositionY(), getPositionZ() - 0.5F + ((float) Math.random())));

        } else if (Math.random() > 0.995) {
            particle = new KiselBubbleParticle(
                    new Vector3f(getPositionX() - 0.5F + ((float) Math.random()), getPositionY(), getPositionZ() - 0.5F + ((float) Math.random())));
        }

        if (particle != null) {
            getEmitterParticleList().add(particle);
            getParticleContainer().addParticle(particle);
        }
    }

    @Override
    public void delete() {
        super.delete();
    }
    
}
