package ru.somber.anomaly.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtils;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class KisselDistortionWaveParticle extends AbstractParticleSimpleData {

    private final float minSize;
    private final float maxSize;
    private final float maxAlpha;


    public KisselDistortionWaveParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.distortion7Icon);

        Random randomizer = SomberCommonUtils.RANDOMIZER;

        this.minSize = 0.2F;
        this.maxSize = 0.7F + randomizer.nextFloat() * 0.2F;
        this.maxAlpha = 0.7F;

        setAlphaFactor(maxAlpha);
        setHalfSizes(minSize, minSize);
        setRotateAnglesX((float) Math.toRadians(-90));
    }

    public KisselDistortionWaveParticle(Vector3f newPosition, int maxLifeTime) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorStaticParticle(destination);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtils.RANDOMIZER;
        float lifeFactor = getLifeFactor();
        float size = SomberCommonUtils.interpolateBetween(minSize, maxSize, lifeFactor);

        setHalfSizes(size, size);
        setAlphaFactor((1 - lifeFactor) * maxAlpha);
    }
}
