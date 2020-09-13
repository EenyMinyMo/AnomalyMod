package ru.somber.anomaly.particle.kisel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtils;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

public class KiselBigActiveParticle extends AbstractSphericalParticle {

    private final float maxHeight;
    private final float maxAngle;
    private final float minSize, maxSize;
    private final float maxAlpha;
    private final float xStart, yStart, zStart;

    public KiselBigActiveParticle(float x, float y, float z) {
        super(x, y, z, 20 + ((int) (Math.random() * 5)), ParticleIcons.anomaly1Icon);

        this.xStart = x;
        this.yStart = y;
        this.zStart = z;

        maxHeight = 0.7F + (float) Math.random() * 0.2F;
        maxAngle = (float) (Math.PI * 0.5F * (0.5 - Math.random())) * 0.5F;
        minSize = 0.5F;
        maxSize = 1.4F;
        maxAlpha = 0.5F;

        setAlphaFactor(maxAlpha);
        setHalfSizes(minSize, minSize);

        setBlendFactor(0.8F);
        setLightFactor(1.0F);
    }

    public KiselBigActiveParticle(Vector3f newPosition) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ());
    }

    @Override
    public void update() {
        super.update();

        float lifeFactor = ((float) getLifeTime()) / getMaxLifeTime();

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setRotateAnglesZ(getAngleZ() + maxAngle * (1.0F / getMaxLifeTime()));

        setAlphaFactor((1 - lifeFactor) * maxAlpha);

        float size = SomberCommonUtils.interpolateBetween(minSize, maxSize, lifeFactor);
        setHalfSizes(size, size);
    }

}
