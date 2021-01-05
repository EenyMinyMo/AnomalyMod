package ru.somber.anomaly.client.particle.kissel;

import ru.somber.anomaly.client.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class KisselDistortionWaveParticle extends AbstractParticleSimpleData {

    private final float minSize;
    private final float maxSize;


    public KisselDistortionWaveParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.distortion4Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.minSize = 0.2F;
        this.maxSize = 0.7F + randomizer.nextFloat() * 0.2F;

        setAlphaFactor(1);
        setHalfSizes(minSize, minSize);
        setRotateAnglesX((float) Math.toRadians(-90));
    }


    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        float lifeFactor = getLifeFactor();
        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, lifeFactor);

        setHalfSizes(size, size);
        setAlphaFactor((1 - lifeFactor));

        computeNormalVectorStaticParticle();
    }
}
