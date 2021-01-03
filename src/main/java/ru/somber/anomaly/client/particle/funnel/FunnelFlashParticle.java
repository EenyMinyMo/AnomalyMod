package ru.somber.anomaly.client.particle.funnel;

import ru.somber.anomaly.client.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class FunnelFlashParticle extends AbstractParticleSimpleData {

    private static final float minSizes = 0.1F;
    private static final float maxSizes = 2.5F;
    private static final float maxHeight = 2.8F;
    /** Время в тиках, которое видно частицу. */
    private static final int maxVisibleTime = 40;

    private final float yStart;
    private int visibleTime = 1;


    public FunnelFlashParticle(float x, float y, float z) {
        super(x, y, z, Integer.MAX_VALUE, ParticleIcons.anomaly16Icon);

        this.yStart = y;

        setHalfSizes(0F, 0F);
    }


    @Override
    public void update() {
        super.update();

        if (visibleTime > maxVisibleTime) {
            setInvisible();
        } else {
            float visibleFactor = (float) visibleTime / maxVisibleTime;

            float size = SomberCommonUtil.interpolateBetween(maxSizes, minSizes, visibleFactor);
            setHalfSizes(size * 1.15F, size);

            setPositionY(yStart + maxHeight * (1 - (float) Math.pow(visibleFactor, 1.5F)));
            addToRotateAngles(0, 0, 0.018F);

            float visibleOffset = visibleFactor - 0.5F;
            float alphaFactor = -visibleOffset * visibleOffset * visibleOffset * visibleOffset * 15 + 1;
            setAlphaFactor(alphaFactor * 0.25F);
        }
        visibleTime++;

        computeNormalVectorSphericalParticle();
    }


    public void setVisible() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        setHalfSizes(maxSizes * 1.15F, maxSizes);
        setRotateAnglesZ((float) Math.PI * 2 * randomizer.nextFloat());
        visibleTime = 1;

        setPositionY(yStart + maxHeight);
        setOldPositionY(yStart + maxHeight);
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
        visibleTime = maxVisibleTime;
    }

}
