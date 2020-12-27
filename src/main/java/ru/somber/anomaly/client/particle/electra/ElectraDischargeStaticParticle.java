package ru.somber.anomaly.client.particle.electra;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class ElectraDischargeStaticParticle  extends AbstractParticleSimpleData {

    private final float maxSize;


    public ElectraDischargeStaticParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.lightEnerg7Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.maxSize = randomizer.nextFloat() * 1.3F + 1.2F;


        setHalfSizes(0, 0);
        setRotateAnglesX((float) Math.toRadians(180 * randomizer.nextFloat()));
        setRotateAnglesY((float) Math.toRadians(360 * randomizer.nextFloat()));
        setRotateAnglesZ((float) Math.toRadians(360 * randomizer.nextFloat()));
        setBlendFactor(1);
        setAlphaFactor(1);
    }


    @Override
    public void update() {
        super.update();

        float lifeFactor = getLifeFactor();

        float size = maxSize * lifeFactor;
        setHalfSizes(size, size);

        setAlphaFactor(1 - (float) Math.pow(lifeFactor, 1.25F));

        computeNormalVectorStaticParticle();
    }
}
