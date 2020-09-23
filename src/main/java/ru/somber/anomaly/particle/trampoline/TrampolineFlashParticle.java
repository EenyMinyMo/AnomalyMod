package ru.somber.anomaly.particle.trampoline;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtils;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

import java.util.Random;

public class TrampolineFlashParticle extends AbstractSphericalParticle {

    private static final float sizes = 0.38F;
    private static final int maxVisTime = 3;

    private int visTime = 0;


    public TrampolineFlashParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.anomaly0Icon);

        Random randomizer = SomberCommonUtils.RANDOMIZER;

        setHalfSizes(0F, 0F);
        setAlphaFactor(0.2F);
        setBlendFactor(1);
    }

    public TrampolineFlashParticle(Vector3f newPosition, int maxLifeTime) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtils.RANDOMIZER;

        visTime++;
        if (visTime > maxVisTime) {
            setInvisible();
        }

//        setVisible();
//        setHalfSizes(0.3F, 0.3F);
    }

    public void setVisible() {
        Random randomizer = SomberCommonUtils.RANDOMIZER;

        setHalfSizes(sizes, sizes);
        setRotateAnglesZ((float) Math.PI * 2 * randomizer.nextFloat());
        visTime = 0;
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

}
