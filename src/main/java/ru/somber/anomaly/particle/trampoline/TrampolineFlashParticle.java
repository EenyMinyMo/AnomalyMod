package ru.somber.anomaly.particle.trampoline;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;
import ru.somber.particlesystem.texture.ParticleAtlasIcon;

public class TrampolineFlashParticle extends AbstractSphericalParticle {

    private static final float sizes = 0.3F;
    private static final int maxVisTime = 3;
    private int visTime = 0;

    public TrampolineFlashParticle(float x, float y, float z, int maxLifeTime, ParticleAtlasIcon icon) {
        super(x, y, z, maxLifeTime, icon);

        setHalfSizes(0F, 0F);
        setAlphaFactor(0.1F);
        setBlendFactor(1);
    }

    public TrampolineFlashParticle(Vector3f newPosition, int maxLifeTime, ParticleAtlasIcon icon) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime, icon);
    }

    @Override
    public void update() {
        super.update();

        visTime++;
        if (visTime > maxVisTime) {
            setInvisible();
        }

//        setVisible();
//        setAlphaFactor(1);
//        setHalfSizes(0.3F, 0.3F);
    }

    public void setVisible() {
        setHalfSizes(sizes, sizes);
        visTime = 0;
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

}
