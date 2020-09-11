package ru.somber.anomaly.particle.kisel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberUtils;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

public class KiselEvaporationParticle extends AbstractSphericalParticle {

    private final float maxHeight;
    private final float maxAngle;
    private final float minSize, maxSize;
    private final float maxAlpha;
    private final float xStart, yStart, zStart;

    public KiselEvaporationParticle(float x, float y, float z) {
        super(x, y, z, 20 + ((int) (Math.random() * 5)), ParticleIcons.anomaly1Icon);

        this.xStart = x;
        this.yStart = y;
        this.zStart = z;

        maxHeight = 0.4F + (float) Math.random() * 0.15F;
        maxAngle = (float) (Math.PI * 0.5F * (0.5 - Math.random())) * 0.5F;
        minSize = 0.1F;
        maxSize = 0.5F;
        maxAlpha = 0.3F;

        setAlphaFactor(maxAlpha);
        setHalfSizes(minSize, minSize);

        setBlendFactor(1F);
        setLightFactor(1.0F);
    }

    public KiselEvaporationParticle(Vector3f newPosition) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ());
    }

    @Override
    public void update() {
        super.update();

        float lifeFactor = ((float) getLifeTime()) / getMaxLifeTime();

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setRotateAnglesZ(getAngleZ() + maxAngle * (1.0F / getMaxLifeTime()));

        setAlphaFactor((1 - lifeFactor) * maxAlpha);

        float size = SomberUtils.interpolateBetween(minSize, maxSize, lifeFactor);
        setHalfSizes(size, size);

//        setHalfSizes(1F * MathHelper.sin(getLifeTime() / ((float)Math.PI * 2)), 1F * MathHelper.sin(getLifeTime() / ((float)Math.PI * 2)));
//        setRotateAnglesZ(((float)Math.PI * lifeFactor));
    }

}
