package ru.somber.anomaly.client.particle.trampoline;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

public class TrampolineDistortionParticle extends AbstractParticleSimpleData {

    private static final float maxHeight = 1.3F;
    private static final float maxSize = 3F;

    public TrampolineDistortionParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.distortion3Icon);

        setHalfSizes(0, 0);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        float lifeFactor = getLifeFactor();
        float size = maxSize * lifeFactor;

        setPositionY(getPositionY() + maxHeight / getMaxLifeTime());
        setHalfSizes(size, size);

        float alphaFactor = 1 - lifeFactor;
        setAlphaFactor((float) Math.pow(alphaFactor, 0.5F));
    }

}
