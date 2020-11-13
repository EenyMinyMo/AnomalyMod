package ru.somber.anomaly.client.particle.fry;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class BurnParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private final float minSize, maxSize;
    private float xForce, yForce, zForce;


    public BurnParticle(float x, float y, float z) {
        super(x, y, z, 20 + SomberCommonUtil.RANDOMIZER.nextInt(4), ParticleIcons.anomaly4Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xForce = 0;
        this.yForce = 0;
        this.zForce = 0;

        this.maxHeight = 2.5F;
        this.minSize = 0.15F;
        this.maxSize = 0.6F + randomizer.nextFloat() * 0.3F;

        setRotateAnglesZ((float) (randomizer.nextFloat() * Math.PI * 2));
        setHalfSizes(minSize, minSize);
        setBlendFactor(1);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        this.xForce += ((randomizer.nextFloat() - 0.5F) * 0.0025F);
        this.zForce += ((randomizer.nextFloat() - 0.5F) * 0.0025F);

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()) * (1 + getLifeFactor() / 2));
        setPositionX(getPositionX() + xForce);
        setPositionZ(getPositionZ() + zForce);

        setAlphaFactor((1 - getLifeFactor()) * 0.75F);
        setBlendFactor((1 - getLifeFactor()) * 0.25F + 0.75F);

        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, getLifeFactor());
        setHalfSizes(size, size);
    }
}
