package ru.somber.anomaly.client.particle.acidmist;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class AcidMistFogParticle extends AbstractParticleSimpleData {

    private final static float maxAlpha = 0.04F;

    private final float maxHeight;


    public AcidMistFogParticle(float x, float y, float z, int maxLifeTime, float halfSize, AtlasIcon icon) {
        super(x, y, z, maxLifeTime, icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.maxHeight = 1.4F + randomizer.nextFloat() * 1.4F;

        setColorFactor(0.3F, 0.6F, 0.3F, maxAlpha);
        setHalfSizes(halfSize, halfSize);
        setRotateAnglesZ(360 * randomizer.nextFloat());
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

        float lifeFactor = getLifeFactor() * 2 - 1;

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setAlphaFactor(maxAlpha * (float) (1 - Math.pow(lifeFactor, 2)));
    }

}
