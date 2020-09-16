package ru.somber.anomaly.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

public class KiselBigFogParticle extends AbstractSphericalParticle {

    private final float minHeight;
    private final float maxHeight;
    private final float maxAlpha;


    public KiselBigFogParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.smoke0Icon);

        this.minHeight = 0.5F;
        this.maxHeight = 2.5F;
        this.maxAlpha = 0.1F;

        setColorFactor(0.3F, 1F, 0.3F, maxAlpha);
        setHalfSizes(1.5F, 1.5F);
        setBlendFactor(1.0F);
    }

    public KiselBigFogParticle(Vector3f newPosition, int maxLifeTime) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime);
    }


    @Override
    public void update() {
        super.update();

        float lifeFactor = (float) getLifeTime() / getMaxLifeTime();

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));

//        float size = SomberUtils.interpolateBetween(minSize, maxSize, lifeFactor);
//        setHalfSizes(size, size);

        setAlphaFactor(maxAlpha * (1 - lifeFactor));
    }

}
