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
        super(x, y, z, 15, ParticleIcons.fire2Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;


        this.maxHeight = 3.2F;
        this.minSize = 0.1F;
        this.maxSize = 1.3F;

        this.xForce = 0;
        this.yForce = maxHeight / getMaxLifeTime();
        this.zForce = 0;

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
        this.xForce += ((randomizer.nextFloat() - 0.5F) * 0.0175F);
        this.zForce += ((randomizer.nextFloat() - 0.5F) * 0.0175F);
        this.yForce *= 1.005F;

        setPositionX(getPositionX() + xForce);
        setPositionY(getPositionY() + yForce);
        setPositionZ(getPositionZ() + zForce);

        float lifeFactor = getLifeFactor();
        float alphaFactor = (float) (-Math.pow((lifeFactor - 0.5F), 2) + 0.25) * 1.5F;

        setBlendFactor((1 - lifeFactor) * 0.3F + 0.7F);
        setAlphaFactor(alphaFactor);

        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, (float) (-Math.pow((lifeFactor - 0.6F), 2) + 0.45) * 1.15F);
        setHalfSizes(size, size);
    }
}
