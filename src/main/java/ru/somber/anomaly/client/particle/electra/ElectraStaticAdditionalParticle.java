package ru.somber.anomaly.client.particle.electra;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class ElectraStaticAdditionalParticle extends AbstractParticleSimpleData {


    public ElectraStaticAdditionalParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.lightEnerg2Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float maxSize = randomizer.nextFloat() * 0.6F + 0.4F;

        setHalfSizes(maxSize, maxSize);
        setRotateAnglesX((float) Math.toRadians(90));
        setRotateAnglesZ((float) Math.toRadians(360 * randomizer.nextFloat()));
        setBlendFactor(1);
        setAlphaFactor(1);
    }


    @Override
    public void update() {
        super.update();

        float lifeFactor = getLifeFactor();
        setAlphaFactor((1 - (float) Math.pow(lifeFactor, 2)) * 0.75F);

        computeNormalVectorStaticParticle();
    }
}
