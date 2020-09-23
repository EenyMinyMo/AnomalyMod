package ru.somber.anomaly.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtils;
import ru.somber.particlesystem.particle.AbstractStaticParticle;

import java.util.Random;

public class KiselDistortionWaveParticle extends AbstractStaticParticle {

    private final float minSize;
    private final float maxSize;
    private final float maxAlpha;


    public KiselDistortionWaveParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.distortion7Icon);

        Random randomizer = SomberCommonUtils.RANDOMIZER;

        this.minSize = 0.2F;
        this.maxSize = 0.7F + randomizer.nextFloat() * 0.2F;
        this.maxAlpha = 0.7F;

        setAlphaFactor(maxAlpha);
        setHalfSizes(minSize, minSize);
        setRotateAnglesX((float) Math.toRadians(-90));
    }

    public KiselDistortionWaveParticle(Vector3f newPosition, int maxLifeTime) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime);
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
