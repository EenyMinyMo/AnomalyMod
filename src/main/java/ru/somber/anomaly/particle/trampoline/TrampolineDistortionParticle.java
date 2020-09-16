package ru.somber.anomaly.particle.trampoline;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;
import ru.somber.particlesystem.texture.ParticleAtlasIcon;

public class TrampolineDistortionParticle extends AbstractSphericalParticle {

    private final float maxHeight;
    private final float maxSize;

    public TrampolineDistortionParticle(float x, float y, float z, int maxLifeTime, ParticleAtlasIcon icon) {
        super(x, y, z, maxLifeTime, icon);

        this.maxHeight = 0.9F;
        this.maxSize = 1.2F;

        setHalfSizes(0F, 0F);
        setAlphaFactor(0.75F);
    }

    public TrampolineDistortionParticle(Vector3f newPosition, int maxLifeTime, ParticleAtlasIcon icon) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime, icon);
    }

    @Override
    public void update() {
        super.update();

        float lifeFactor = (float) getLifeTime() / getMaxLifeTime();
        setPositionY(getPositionY() + maxHeight / getMaxLifeTime());
        setHalfSizes(getHalfWidth() + maxSize / getMaxLifeTime(), getHalfHeight() + maxSize / getMaxLifeTime());
    }
}
