package ru.somber.anomaly.client.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class KisselFogParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private final float maxAlpha;


    public KisselFogParticle(float x, float y, float z) {
        super(x, y, z, 60 + SomberCommonUtil.RANDOMIZER.nextInt(5), ParticleIcons.smoke0Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.maxHeight = 0.4F + randomizer.nextFloat() * 1.4F;
        this.maxAlpha = 0.05F;

        setColorFactor(0.3F, 1F, 0.3F, maxAlpha);
        setHalfSizes(0.8F, 0.8F);
        setBlendFactor(1.0F);
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

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setAlphaFactor(maxAlpha * (1 - lifeFactor));
    }

}
