package ru.somber.anomaly.client.particle.funnel;

import ru.somber.anomaly.client.particle.AbstractLeafParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class FunnelActiveLeafParticle extends AbstractLeafParticle {

    private static final float sizes = 0.07F;
    private static final int countTicksForLifting = 160;
    private static final int countTicksForFall = 20;

    private final float yStart;
    private final float xCenterCircle, zCenterCircle;
    private final float targetLiftingY;
    private final float minRadius, maxRadius;

    private boolean isLiftingMode;
    private float moveAngleSpeed = 3;
    private int currentTicks;
    private float currentAngle;
    private float radius;

    private float discardingFactor;
    private float xDiscardingVector;
    private float yDiscardingVector;
    private float zDiscardingVector;


    public FunnelActiveLeafParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime);

        Random random = SomberCommonUtil.RANDOMIZER;

        this.yStart = y - random.nextFloat() * 2F;
        this.xCenterCircle = x;
        this.zCenterCircle = z;
        this.targetLiftingY = y + 1.75F + random.nextFloat() * 2F;
        this.minRadius = 0.1F + random.nextFloat() * 0.4F;
        this.maxRadius = random.nextFloat() * 2.5F;

        this.radius = maxRadius;

        setPositionX(xCenterCircle + (float) Math.cos(currentAngle) * radius);
        setPositionZ(zCenterCircle + (float) Math.sin(currentAngle) * radius);

        setHalfSizes(sizes, sizes);

        setRotateAnglesZ(360 * SomberCommonUtil.RANDOMIZER.nextFloat());
    }


    @Override
    public void update() {
        super.update();

        currentTicks++;

        if (isLiftingMode) {
            setPositionX(xCenterCircle + (float) Math.cos(Math.toRadians(currentAngle)) * radius);
            setPositionZ(zCenterCircle + (float) Math.sin(Math.toRadians(currentAngle)) * radius);

            radius = SomberCommonUtil.interpolateBetween(minRadius, maxRadius, Math.max(1 - (float) currentTicks / countTicksForLifting, 0));

            currentAngle += moveAngleSpeed;
            moveAngleSpeed += 0.07F;

            float currentY = SomberCommonUtil.interpolateBetween(targetLiftingY, yStart, Math.max(1 - (float) currentTicks / countTicksForLifting, 0));
            setPositionY(currentY);
        } else {
            radius += 0.6F * (discardingFactor);

            addToPosition(xDiscardingVector, yDiscardingVector, zDiscardingVector);
            xDiscardingVector *= 0.92F;
            zDiscardingVector *= 0.92F;
            yDiscardingVector += -0.01F;

            if (currentTicks >= countTicksForFall) {
                setDie(true);
            }
            setAlphaFactor(1 - (float) currentTicks / countTicksForFall);
        }
    }

    public void setMode(boolean isLiftingMode) {
        this.isLiftingMode = isLiftingMode;
        if (! isLiftingMode) {
            Random randomizer = SomberCommonUtil.RANDOMIZER;

            discardingFactor = (1F - Math.max(1 - (float) currentTicks / countTicksForLifting, 0)) * (randomizer.nextFloat() * 0.35F + 0.65F);
//            discardingFactor;

            xDiscardingVector = (float) Math.cos(Math.toRadians(currentAngle)) * discardingFactor * 0.5F;
            zDiscardingVector = (float) Math.sin(Math.toRadians(currentAngle)) * discardingFactor * 0.5F;
            yDiscardingVector = (randomizer.nextFloat() - 0.5F) * (discardingFactor + 0.05F);
        }
        currentTicks = 0;
    }

    public void setCurrentAngle(float currentAngle) {
        this.currentAngle = currentAngle;
    }

}
