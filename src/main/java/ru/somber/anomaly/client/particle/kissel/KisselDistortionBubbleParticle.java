package ru.somber.anomaly.client.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class KisselDistortionBubbleParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private float xForce, yForce, zForce;


    public KisselDistortionBubbleParticle(float x, float y, float z) {
        super(x, y, z, 3 * 20 + SomberCommonUtil.RANDOMIZER.nextInt(10), ParticleIcons.distortion17Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xForce = 0;
        this.yForce = 0;
        this.zForce = 0;

        this.maxHeight = 0.8F + randomizer.nextFloat() * 0.2F;

        setRotateAnglesZ((float) Math.toRadians(-180));
        setAlphaFactor(1);

        float sizeRandom = 0.1F + randomizer.nextFloat() * 0.05F;
        setHalfSizes(sizeRandom, sizeRandom);
    }


    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        this.xForce += ((randomizer.nextFloat() - 0.5F) * 0.002F);
        this.zForce += ((randomizer.nextFloat() - 0.5F) * 0.002F);

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setPositionX(getPositionX() + xForce);
        setPositionZ(getPositionZ() + zForce);

        computeNormalVectorSphericalParticle();
    }
}
