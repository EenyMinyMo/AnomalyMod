package ru.somber.anomaly.particle.trampoline;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.commonutil.SomberCommonUtils;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

import java.util.Random;

public class TrampolineDistortionParticle extends AbstractSphericalParticle {

    private static final float sizes = 0.15F;

    private final float maxHeight;
    private final float maxSize;


    public TrampolineDistortionParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.distortion3Icon);

        Random randomizer = SomberCommonUtils.RANDOMIZER;

        this.maxHeight = 0.6F;
        this.maxSize = 1.2F;

        setHalfSizes(0F, 0F);
    }

    public TrampolineDistortionParticle(Vector3f newPosition, int maxLifeTime) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtils.RANDOMIZER;
        float lifeFactor = getLifeFactor();

        setPositionY(getPositionY() + maxHeight / getMaxLifeTime());
        setHalfSizes(maxSize * lifeFactor, maxSize * lifeFactor);
        setAlphaFactor((float) Math.pow((1 - lifeFactor), 0.5));
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

    public void setVisible() {
        setHalfSizes(sizes, sizes);
    }


}
