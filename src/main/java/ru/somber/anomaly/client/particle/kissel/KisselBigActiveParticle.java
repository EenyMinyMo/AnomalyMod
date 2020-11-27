package ru.somber.anomaly.client.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class KisselBigActiveParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private final float maxAngle;
    private final float minSize, maxSize;
    private final float maxAlpha;


    public KisselBigActiveParticle(float x, float y, float z) {
        super(x, y, z, 20 + SomberCommonUtil.RANDOMIZER.nextInt(10), ParticleIcons.anomaly1Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.maxHeight = 0.7F + randomizer.nextFloat() * 0.2F;
        this.maxAngle = (float) (Math.PI * 0.5F * (0.5F - randomizer.nextFloat())) * 0.5F;
        this.minSize = 0.5F;
        this.maxSize = 1.4F;
        this.maxAlpha = 0.5F;

        setAlphaFactor(maxAlpha);
        setHalfSizes(minSize, minSize);
        setBlendFactor(0.8F);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        float lifeFactor = getLifeFactor();
        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, lifeFactor);

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setRotateAnglesZ(getAngleZ() + maxAngle * (1.0F / getMaxLifeTime()));
        setHalfSizes(size, size);
        setAlphaFactor((1 - lifeFactor) * maxAlpha);
    }

}
