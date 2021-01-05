package ru.somber.anomaly.client.particle;

import ru.somber.anomaly.client.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

public class SplashGravityParticle extends AbstractParticleSimpleData {

    private static final float minSize = 0.1F;
    private static final float maxSize = 1.1F;


    public SplashGravityParticle(float x, float y, float z, float xNormal, float yNormal, float zNormal) {
        super(x, y, z, 14, ParticleIcons.anomaly15Icon);

        float normalLength = xNormal * xNormal + yNormal * yNormal + zNormal * zNormal;
        xNormal /= normalLength;
        yNormal /= normalLength;
        zNormal /= normalLength;

        setNormalVector(xNormal, yNormal, zNormal);
        setHalfSizes(minSize, minSize);
        setRotateAnglesZ((float) (Math.PI * 2 * Math.random()));
    }


    @Override
    public void update() {
        super.update();

        float sqrLifeFactor = getLifeFactor() * getLifeFactor();
        float size = minSize + (maxSize - minSize) * sqrLifeFactor;
        setHalfSizes(size, size);
        setAlphaFactor(1 - getLifeFactor());
    }

}
