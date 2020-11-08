package ru.somber.anomaly.client.particle.fry;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class DistortionHeatParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private final float minSize, maxSize;
    private float xForce, yForce, zForce;


    public DistortionHeatParticle(float x, float y, float z) {
        super(x, y, z, 90 + SomberCommonUtil.RANDOMIZER.nextInt(10), ParticleIcons.distortion0Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xForce = 0;
        this.yForce = 0;
        this.zForce = 0;

        this.maxHeight = 3F + randomizer.nextFloat() * 0.3F;
        this.minSize = 0.5F;
        this.maxSize = 0.8F;

        setRotateAnglesZ((float) Math.toRadians(-180));
//        setAlphaFactor(0.2F + randomizer.nextFloat() * 0.1F);

//        float sizeRandom = 0.1F + randomizer.nextFloat() * 0.05F;
        setHalfSizes(minSize, minSize);
    }

    public DistortionHeatParticle(Vector3f newPosition) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ());
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

        setAlphaFactor(1 - (float) Math.pow(getLifeFactor(), 3));

        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, getLifeFactor());
        setHalfSizes(size, size);
    }
}
