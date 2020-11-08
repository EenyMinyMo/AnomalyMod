package ru.somber.anomaly.client.particle.trampoline;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class TrampolineDistortionParticle extends AbstractParticleSimpleData {

    private static final float sizes = 0.15F;

    private final float maxHeight;
    private final float maxSize;


    public TrampolineDistortionParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.distortion3Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.maxHeight = 1.5F;
        this.maxSize = 3F;

        setHalfSizes(0F, 0F);
    }

    public TrampolineDistortionParticle(Vector3f newPosition, int maxLifeTime) {
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

        setPositionY(getPositionY() + maxHeight / getMaxLifeTime());
        setHalfSizes(maxSize * lifeFactor, maxSize * lifeFactor);
        setAlphaFactor((float) (1 - lifeFactor));
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

    public void setVisible() {
        setHalfSizes(sizes, sizes);
    }


}
