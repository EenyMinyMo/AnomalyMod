package ru.somber.anomaly.client.particle.funnel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class FunnelDistortionParticle extends AbstractParticleSimpleData {

    private static final float minSizes = 0.6F;
    private static final float maxSizes = 4.5F;
    private static final float maxHeight = 2.4F;
    /** Время в тиках, которое видно частицу. */
    private static final int maxVisibleTime = 35;

    private final float yStart;
    private int visibleTime = 1;


    public FunnelDistortionParticle(float x, float y, float z) {
        super(x, y, z, Integer.MAX_VALUE, ParticleIcons.distortion21Icon);

        this.yStart = y + 0.3F;

        setHalfSizes(0F, 0F);
        setAlphaFactor(0.6F);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        if (visibleTime > maxVisibleTime) {
            setInvisible();
        } else {
            float visibleFactor = (float) visibleTime / maxVisibleTime;

            float size = SomberCommonUtil.interpolateBetween(maxSizes, minSizes, visibleFactor);
            setHalfSizes(size, size);
            setPositionY(yStart + maxHeight * (1 - visibleFactor));

            addToRotateAngles(0, 0, 0.012F);
        }
        visibleTime++;
    }


    public void setVisible() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        setHalfSizes(maxSizes, maxSizes);
        setRotateAnglesZ((float) Math.PI * 2 * randomizer.nextFloat());
        visibleTime = 1;

        setPositionY(yStart + maxHeight);
        setOldPositionY(yStart + maxHeight);
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

}
