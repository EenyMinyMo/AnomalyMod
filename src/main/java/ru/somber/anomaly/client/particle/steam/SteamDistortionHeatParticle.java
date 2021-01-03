package ru.somber.anomaly.client.particle.steam;

import ru.somber.anomaly.client.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class SteamDistortionHeatParticle extends AbstractParticleSimpleData {

    private static final float maxHeight = 2.8F;
    private static final float maxAlpha = 0.75F;
    private static final float minSize = 0.6F;
    private static final float maxSize = 1.3F;

    private float xForce, zForce;


    public SteamDistortionHeatParticle(float x, float y, float z) {
        super(x, y, z, 80 + SomberCommonUtil.RANDOMIZER.nextInt(10), ParticleIcons.distortion0Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xForce = 0;
        this.zForce = 0;

        setRotateAnglesZ((float) Math.toRadians(360) * randomizer.nextFloat());
        setAlphaFactor(maxAlpha);

        setHalfSizes(minSize, minSize);
    }


    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xForce += ((randomizer.nextFloat() - 0.5F) * 0.0015F);
        this.zForce += ((randomizer.nextFloat() - 0.5F) * 0.0015F);

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setPositionX(getPositionX() + xForce);
        setPositionZ(getPositionZ() + zForce);

        setAlphaFactor((1 - getLifeFactor() * getLifeFactor() * getLifeFactor()) * maxAlpha);

        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, getLifeFactor());
        setHalfSizes(size, size);

        computeNormalVectorSphericalParticle();
    }
}
