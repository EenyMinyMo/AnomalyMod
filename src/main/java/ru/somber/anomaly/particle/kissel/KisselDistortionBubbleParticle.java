package ru.somber.anomaly.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtils;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class KisselDistortionBubbleParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private float xForce, yForce, zForce;


    public KisselDistortionBubbleParticle(float x, float y, float z) {
        super(x, y, z, 3 * 20 + SomberCommonUtils.RANDOMIZER.nextInt(10), ParticleIcons.distortion17Icon);

        Random randomizer = SomberCommonUtils.RANDOMIZER;

        this.xForce = 0;
        this.yForce = 0;
        this.zForce = 0;

        this.maxHeight = 0.8F + randomizer.nextFloat() * 0.2F;

        setRotateAnglesZ((float) Math.toRadians(-180));
        setAlphaFactor(0.2F + randomizer.nextFloat() * 0.1F);

        float sizeRandom = 0.1F + randomizer.nextFloat() * 0.05F;
        setHalfSizes(sizeRandom, sizeRandom);
    }

    public KisselDistortionBubbleParticle(Vector3f newPosition) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ());
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtils.RANDOMIZER;
        this.xForce += ((randomizer.nextFloat() - 0.5F) * 0.002F);
        this.zForce += ((randomizer.nextFloat() - 0.5F) * 0.002F);

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setPositionX(getPositionX() + xForce);
        setPositionZ(getPositionZ() + zForce);

//        float log = (float) Math.log10((double) lifeTime / maxLifeTime);
//        float colorFactor =  1 - (log + 1F);
//        setColorFactor(1, 1, 1, 0.5F * colorFactor);
    }
}
