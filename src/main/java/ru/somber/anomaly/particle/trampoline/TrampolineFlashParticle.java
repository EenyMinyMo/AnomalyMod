package ru.somber.anomaly.particle.trampoline;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;
import ru.somber.particlesystem.texture.ParticleAtlasIcon;

public class TrampolineFlashParticle extends AbstractSphericalParticle {

    public TrampolineFlashParticle(float x, float y, float z, int maxLifeTime, ParticleAtlasIcon icon) {
        super(x, y, z, maxLifeTime, icon);

        setHalfSizes(0F, 0F);
        setAlphaFactor(0.75F);
        setBlendFactor(1);
    }

    public TrampolineFlashParticle(Vector3f newPosition, int maxLifeTime, ParticleAtlasIcon icon) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime, icon);
    }

    @Override
    public void update() {
        setOldPosition(getPositionX(), getPositionY(), getPositionZ());
        setOldHalfSizes(getHalfWidth(), getHalfHeight());
        setOldRotateAngles(getAngleX(), getAngleY(), getAngleZ());

        setLifeTime(getLifeTime() + 1);
    }

}
