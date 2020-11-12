package ru.somber.anomaly.client.particle.trampoline;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.util.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class TrampolineFlashParticle extends AbstractParticleSimpleData {

    /** Размер частиц. */
    private static final float sizes = 0.3F;
    /** Время в тиках, которое видно частиц. */
    private static final int maxVisibleTime = 2;

    /**
     * Время в тиках, которое видно частицу.
     * Когда частицу делают видимой, это значение используется для отсчета времени, которое видно частицу.
     * Когда значение в переменной сравняется с maxVisibleTime, частиц стане невидимой.
     */
    private int visibleTime = 0;


    public TrampolineFlashParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.anomaly0Icon);

        setHalfSizes(0F, 0F);
        setAlphaFactor(0.3F);
        setBlendFactor(1.0F);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        if (visibleTime >= maxVisibleTime) {
            setInvisible();
        } else {
            float size = sizes + sizes * visibleTime / maxVisibleTime;
            setHalfSizes(size,size);
        }
        visibleTime++;
    }

    public void setVisible() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        setHalfSizes(sizes, sizes);
        setRotateAnglesZ((float) Math.PI * 2 * randomizer.nextFloat());
        visibleTime = 0;
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

}
