package ru.somber.anomaly.particle.trampoline;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.commonutil.SomberCommonUtils;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

import java.util.Random;

public class TrampolineDistortionParticle extends AbstractSphericalParticle {

    private static final float sizes = 0.15F;

    private final float maxHeight;
    private final float maxSize;


    public TrampolineDistortionParticle(float x, float y, float z, int maxLifeTime, AtlasIcon icon) {
        super(x, y, z, maxLifeTime, icon);

        Random randomizer = SomberCommonUtils.RANDOMIZER;

        this.maxHeight = 0.9F;
        this.maxSize = 2F;

        setHalfSizes(0F, 0F);
    }

    public TrampolineDistortionParticle(Vector3f newPosition, int maxLifeTime, AtlasIcon icon) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime, icon);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtils.RANDOMIZER;
        float lifeFactor = getLifeFactor();

        setPositionY(getPositionY() + maxHeight / getMaxLifeTime());
        setHalfSizes(maxSize * lifeFactor, maxSize * lifeFactor);
        setAlphaFactor((1 - lifeFactor));
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

    public void setVisible() {
        setHalfSizes(sizes, sizes);
    }


}
