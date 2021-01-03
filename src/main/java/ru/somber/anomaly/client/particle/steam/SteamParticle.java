package ru.somber.anomaly.client.particle.steam;

import ru.somber.anomaly.client.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class SteamParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private final float minSize, maxSize;
    private final float maxBlend;
    private float xForce, yForce, zForce;


    public SteamParticle(float x, float y, float z) {
        super(x, y, z, 25 + SomberCommonUtil.RANDOMIZER.nextInt(4), ParticleIcons.smoke2Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xForce = 0;
        this.yForce = 0;
        this.zForce = 0;

        this.maxHeight = 4F;
        this.minSize = 0.25F;
        this.maxSize = 1F + randomizer.nextFloat() * 0.8F;
        this.maxBlend = 0.25F;

        setRotateAnglesZ((float) (randomizer.nextFloat() * Math.PI * 2));
        setHalfSizes(minSize, minSize);
        setBlendFactor(maxBlend);
    }


    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        this.xForce += ((randomizer.nextFloat() - 0.5F) * 0.009F);
        this.zForce += ((randomizer.nextFloat() - 0.5F) * 0.009F);

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()) * (1 + getLifeFactor() / 2));
        setPositionX(getPositionX() + xForce);
        setPositionZ(getPositionZ() + zForce);

        setAlphaFactor((1 - getLifeFactor()) * 0.5F);
        setBlendFactor((1 - getLifeFactor()) * maxBlend);

        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, getLifeFactor());
        setHalfSizes(size, size);

        computeNormalVectorSphericalParticle();
    }
}
