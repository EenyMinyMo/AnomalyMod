package ru.somber.anomaly.client.particle.steam;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class SteamParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private final float minSize, maxSize;
    private final float maxBlend;
    private float xForce, yForce, zForce;


    public SteamParticle(float x, float y, float z) {
        super(x, y, z, 25 + SomberCommonUtil.RANDOMIZER.nextInt(4), ParticleIcons.smoke1Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xForce = 0;
        this.yForce = 0;
        this.zForce = 0;

        this.maxHeight = 2.8F;
        this.minSize = 0.25F;
        this.maxSize = 0.8F + randomizer.nextFloat() * 0.8F;
        this.maxBlend = 0.25F;

        setRotateAnglesZ((float) (randomizer.nextFloat() * Math.PI * 2));
        setHalfSizes(minSize, minSize);
        setBlendFactor(maxBlend);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        this.xForce += ((randomizer.nextFloat() - 0.5F) * 0.006F);
        this.zForce += ((randomizer.nextFloat() - 0.5F) * 0.006F);

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()) * (1 + getLifeFactor() / 2));
        setPositionX(getPositionX() + xForce);
        setPositionZ(getPositionZ() + zForce);

        setAlphaFactor((1 - getLifeFactor()) * 0.6F);
        setBlendFactor((1 - getLifeFactor()) * maxBlend);

        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, getLifeFactor());
        setHalfSizes(size, size);
    }
}
