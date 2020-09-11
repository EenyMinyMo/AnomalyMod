package ru.somber.anomaly.particle.kisel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberUtils;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;
import ru.somber.particlesystem.particle.AbstractStaticParticle;
import ru.somber.particlesystem.texture.ParticleAtlasIcon;

public class KiselDistortionWaveParticle extends AbstractStaticParticle {

    private final float minSize;
    private final float maxSize;

    public KiselDistortionWaveParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.distortion7Icon);

        minSize = 0.2F;
        maxSize = 0.7F + (float) Math.random() * 0.2F;

//        setAlphaFactor(0.3F + (float) Math.random() * 0.3F);

        setRotateAnglesX((float) Math.toRadians(-90));
        setHalfSizes(minSize, minSize);

        setBlendFactor(0);
        setLightFactor(1);
    }

    public KiselDistortionWaveParticle(Vector3f newPosition, int maxLifeTime) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime);
    }


    @Override
    public void update() {
        super.update();

        float lifeFactor = (float) getLifeTime() / getMaxLifeTime();
        float size = SomberUtils.interpolateBetween(minSize, maxSize, lifeFactor);
        setHalfSizes(size, size);

        setAlphaFactor((1 - lifeFactor) * 0.6F);
    }
}
