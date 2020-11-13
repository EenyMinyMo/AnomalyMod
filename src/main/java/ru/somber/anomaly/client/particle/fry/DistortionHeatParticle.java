package ru.somber.anomaly.client.particle.fry;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class DistortionHeatParticle extends AbstractParticleSimpleData {

    private static final float maxHeight = 3.5F;
    private static final float maxAlpha = 0.5F;
    private static final float minSize = 0.4F;
    private static final float maxSize = 1.1F;

    private float xForce, zForce;


    public DistortionHeatParticle(float x, float y, float z) {
        super(x, y, z, 90 + SomberCommonUtil.RANDOMIZER.nextInt(10), ParticleIcons.distortion0Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xForce = 0;
        this.zForce = 0;

        setRotateAnglesZ((float) Math.toRadians(360) * randomizer.nextFloat());
        setAlphaFactor(maxAlpha);

        setHalfSizes(minSize, minSize);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xForce += ((randomizer.nextFloat() - 0.5F) * 0.001F);
        this.zForce += ((randomizer.nextFloat() - 0.5F) * 0.001F);

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setPositionX(getPositionX() + xForce);
        setPositionZ(getPositionZ() + zForce);

        setAlphaFactor((1 - (float) Math.pow(getLifeFactor(), 3)) * maxAlpha);

        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, getLifeFactor());
        setHalfSizes(size, size);
    }
}
