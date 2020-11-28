package ru.somber.anomaly.client.particle.funnel;

import ru.somber.anomaly.client.particle.AbstractLeafParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class FunnelLeafParticle extends AbstractLeafParticle {

    private static final float maxHeight = 2.6F;
    /** Время в тиках, которое видно частицу. */
    private static final int maxVisibleTime = 30;

    private final float xStart, yStart, zStart;
    private float xVector, yVector, zVector;
    private int visibleTime = 1;
    private float angle;


    public FunnelLeafParticle(float x, float y, float z) {
        super(x, y, z, Integer.MAX_VALUE);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xStart = x + (randomizer.nextFloat() * 0.2F - 0.1F);
        this.yStart = y + (randomizer.nextFloat() * 0.4F - 0.3F);
        this.zStart = z + (randomizer.nextFloat() * 0.2F - 0.1F);

        angle = (float) Math.PI * 2 * randomizer.nextFloat();
        visibleTime = randomizer.nextInt(maxVisibleTime);

        setHalfSizes(0.08F, 0.08F);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        if (visibleTime >= maxVisibleTime) {
            angle = (float) Math.PI * 2 * randomizer.nextFloat();

            float x = (float) Math.cos(angle) * 2.5F + xStart;
            float z = (float) Math.sin(angle) * 2.5F + zStart;

            setPosition(x, yStart, z);
            setOldPosition(x, yStart, z);

            xVector = - (float) Math.cos(angle) * 2.3F / maxVisibleTime;
            zVector = - (float) Math.sin(angle) * 2.3F / maxVisibleTime;

            yVector = 0.6F;

            visibleTime = 1;
        }

        yVector -= 0.043F;
        addToPosition(xVector, yVector,zVector);
        addToRotateAngles(0, 0, 0.5F);

        visibleTime++;
    }


    public void setVisible() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        setHalfSizes(0.08F, 0.08F);
        setRotateAnglesZ((float) Math.PI * 2 * randomizer.nextFloat());
        visibleTime = randomizer.nextInt(maxVisibleTime);


//        setPositionY(yStart + maxHeight);
//        setOldPositionY(yStart + maxHeight);
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

}
