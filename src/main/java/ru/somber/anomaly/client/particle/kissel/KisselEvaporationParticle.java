package ru.somber.anomaly.client.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class KisselEvaporationParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private final float maxAngle;
    private final float minSize, maxSize;
    private final float maxAlpha;


    public KisselEvaporationParticle(float x, float y, float z) {
        super(x, y, z, 20 + SomberCommonUtil.RANDOMIZER.nextInt(5), ParticleIcons.anomaly1Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.maxHeight = 0.4F + randomizer.nextFloat() * 0.15F;
        this.maxAngle = (float) (Math.PI * 0.5F * 0.5F - randomizer.nextFloat()) * 0.5F;
        this.minSize = 0.1F;
        this.maxSize = 0.5F;
        this.maxAlpha = 0.3F;

        setAlphaFactor(maxAlpha);
        setHalfSizes(minSize, minSize);
        setBlendFactor(1F);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
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

//        setHalfSizes(1F * MathHelper.sin(getLifeTime() / ((float)Math.PI * 2)), 1F * MathHelper.sin(getLifeTime() / ((float)Math.PI * 2)));
//        setRotateAnglesZ(((float)Math.PI * lifeFactor));
    }

}
