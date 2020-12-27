package ru.somber.anomaly.client.particle.electra;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class ElectraSphericalParticle extends AbstractParticleSimpleData {

    private final float maxSize;


    public ElectraSphericalParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.lightEnerg7Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.maxSize = randomizer.nextFloat() * 0.6F + 0.3F;


        setHalfSizes(0, 0);
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

        setAlphaFactor(1 - (float) Math.pow(lifeFactor, 4));

        computeNormalVectorSphericalParticle();
    }
}
