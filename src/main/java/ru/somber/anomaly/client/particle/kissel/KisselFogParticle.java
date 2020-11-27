package ru.somber.anomaly.client.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class KisselFogParticle extends AbstractParticleSimpleData {

    private final static float maxAlpha = 0.1F;

    private final float maxHeight;


    public KisselFogParticle(float x, float y, float z, int maxLifeTime, float halfSize, AtlasIcon icon) {
        super(x, y, z, maxLifeTime, icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.maxHeight = 1.2F + randomizer.nextFloat() * 1.3F;

        setColorFactor(0.3F, 1F, 0.3F, maxAlpha);
        setHalfSizes(halfSize, halfSize);
        setRotateAnglesZ(360 * randomizer.nextFloat());
        setBlendFactor(1F);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float lifeFactor = getLifeFactor() * 2 - 1;

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));
        setAlphaFactor(maxAlpha * (1 - lifeFactor * lifeFactor));
    }

}
