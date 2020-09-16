package ru.somber.anomaly.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

public class KiselFogParticle extends AbstractSphericalParticle {

    private final float maxHeight;
    private final float maxAlpha;
    private final float xStart, yStart, zStart;

    public KiselFogParticle(float x, float y, float z) {
        super(x, y, z, 60 + ((int) (Math.random() * 5)), ParticleIcons.smoke0Icon);

        this.xStart = x;
        this.yStart = y;
        this.zStart = z;

        this.maxHeight = 0.4F + (float) Math.random() * 1.4F;
        this.maxAlpha = 0.05F;

        setColorFactor(0.3F, 1F, 0.3F, maxAlpha);
        setHalfSizes(0.8F, 0.8F);
        setBlendFactor(1.0F);
    }

    public KiselFogParticle(Vector3f newPosition) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ());
    }

    @Override
    public void update() {
        super.update();

        float lifeFactor = ((float) getLifeTime()) / getMaxLifeTime();
        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));

        setAlphaFactor(maxAlpha * (1 - lifeFactor));
    }

}
