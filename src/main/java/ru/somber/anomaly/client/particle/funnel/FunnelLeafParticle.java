package ru.somber.anomaly.client.particle.funnel;

import ru.somber.anomaly.client.particle.AbstractLeafParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class FunnelLeafParticle extends AbstractLeafParticle {

    private static final float maxHeight = 2.6F;
    /** Время в тиках, которое видно частицу. */
    private static final int maxVisibleTime = 36;

    private final float xStart, yStart, zStart;
    private final float radius;
    private float xVector, yVector, zVector;
    private int visibleTime = 1;
    private float angle;


    public FunnelLeafParticle(float x, float y, float z) {
        super(x, y, z, Integer.MAX_VALUE);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xStart = x + (randomizer.nextFloat() * 0.16F - 0.08F);
        this.yStart = y + (randomizer.nextFloat() + 1.9F);
        this.zStart = z + (randomizer.nextFloat() * 0.16F - 0.08F);

        this.radius = 1.5F;

        this.angle = (float) Math.PI * 2 * randomizer.nextFloat();
        this.visibleTime = randomizer.nextInt(maxVisibleTime);

        setHalfSizes(0.08F, 0.08F);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        if (visibleTime >= maxVisibleTime) {
            angle = (float) Math.PI * 2 * randomizer.nextFloat();

            float x = (float) Math.cos(angle) * radius + xStart;
            float z = (float) Math.sin(angle) * radius + zStart;

            setPosition(x, yStart, z);
            setOldPosition(x, yStart, z);

            xVector = - (float) Math.cos(angle) * radius / maxVisibleTime;
            zVector = - (float) Math.sin(angle) * radius / maxVisibleTime;
            yVector = 0.26F;

            visibleTime = 1;
        }

        yVector -= 0.02F;
        addToPosition(xVector, yVector,zVector);
        addToRotateAngles(0, 0, 0.15F);

        visibleTime++;
    }


    public void setVisible() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        setHalfSizes(0.08F, 0.08F);
        setRotateAnglesZ((float) Math.PI * 2 * randomizer.nextFloat());
        visibleTime = randomizer.nextInt(maxVisibleTime);
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

}
