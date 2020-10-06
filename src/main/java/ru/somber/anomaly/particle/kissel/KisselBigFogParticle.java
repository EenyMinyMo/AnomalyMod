package ru.somber.anomaly.particle.kissel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class KisselBigFogParticle extends AbstractParticleSimpleData {

    private final float minHeight;
    private final float maxHeight;
    private final float maxAlpha;


    public KisselBigFogParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.smoke0Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.minHeight = 0.5F;
        this.maxHeight = 2.5F;
        this.maxAlpha = 0.1F;

        setColorFactor(0.3F, 1F, 0.3F, maxAlpha);
        setHalfSizes(1.5F, 1.5F);
        setBlendFactor(1.0F);
    }

    public KisselBigFogParticle(Vector3f newPosition, int maxLifeTime) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime);
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

//        float size = SomberUtils.interpolateBetween(minSize, maxSize, lifeFactor);
//        setHalfSizes(size, size);
    }

}
