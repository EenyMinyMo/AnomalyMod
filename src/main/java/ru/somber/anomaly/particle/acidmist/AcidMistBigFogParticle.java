package ru.somber.anomaly.particle.acidmist;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class AcidMistBigFogParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private final float maxAlpha;


    public AcidMistBigFogParticle(float x, float y, float z) {
        super(x, y, z, 130 + SomberCommonUtil.RANDOMIZER.nextInt(5), ParticleIcons.smoke4Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.maxHeight = 0.7F + randomizer.nextFloat() * 1.4F;
        this.maxAlpha = 0.05F;

        setColorFactor(0.3F, 0.6F, 0.3F, maxAlpha);
        setHalfSizes(1.2F, 1.2F);
        setRotateAnglesZ(360 * randomizer.nextFloat());
        setBlendFactor(1F);
    }

    public AcidMistBigFogParticle(Vector3f newPosition) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ());
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        float lifeFactor = getLifeFactor() * 2 - 1;

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setAlphaFactor(maxAlpha * (float) (1 - Math.pow(lifeFactor, 2)));
    }

}
