package ru.somber.anomaly.client.particle.funnel;

import ru.somber.anomaly.client.particle.AbstractDustParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class FunnelActiveDustParticle extends AbstractDustParticle {

    private static final float sizes = 1.5F;
    private static final int countTicksForLifting = 100;
    private static final int countTicksForFall = 60;

    private final float yStart;
    private final float xCenterCircle, zCenterCircle;
    private final float targetLiftingY;
    private final float minRadius, maxRadius;

    private boolean isLiftingMode;
    private float moveAngleSpeed = 2.5F;
    private int currentTicks;
    private float currentAngle;
    private float radius;

    private float discardingFactor;
    private float xDiscardingVector;
    private float yDiscardingVector;
    private float zDiscardingVector;


    public FunnelActiveDustParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime);

        Random random = SomberCommonUtil.RANDOMIZER;

        this.yStart = y - random.nextFloat() * 1.2F;
        this.xCenterCircle = x + (random.nextFloat() * 0.1F - 0.05F);
        this.zCenterCircle = z + (random.nextFloat() * 0.1F - 0.05F);

        this.targetLiftingY = yStart + 3.75F + random.nextFloat() * 0.25F;
        this.minRadius = 0.1F + random.nextFloat() * 0.3F;
        this.maxRadius = 0.3F + random.nextFloat() * 4F;

        this.radius = maxRadius;

        setPositionX(xCenterCircle + (float) Math.cos(currentAngle) * radius);
        setPositionZ(zCenterCircle + (float) Math.sin(currentAngle) * radius);

        setHalfSizes(sizes, sizes);
        setRotateAnglesZ((float) Math.PI * 2 * SomberCommonUtil.RANDOMIZER.nextFloat());
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
            moveAngleSpeed += 0.08F;

            float currentY = SomberCommonUtil.interpolateBetween(targetLiftingY, yStart, Math.max(1 - (float) currentTicks / countTicksForLifting, 0));
            setPositionY(currentY);
        } else {
            radius += 0.6F * (discardingFactor);

            addToPosition(xDiscardingVector, yDiscardingVector, zDiscardingVector);
            xDiscardingVector *= 0.86F;
            zDiscardingVector *= 0.86F;
            yDiscardingVector += -0.005F;

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

            xDiscardingVector = (float) Math.cos(Math.toRadians(currentAngle)) * discardingFactor * (randomizer.nextFloat() * 0.3F + 0.35F);
            zDiscardingVector = (float) Math.sin(Math.toRadians(currentAngle)) * discardingFactor * (randomizer.nextFloat() * 0.3F + 0.35F);
            yDiscardingVector = (randomizer.nextFloat() - 0.9F) * (discardingFactor * 0.3F);
        }
        currentTicks = 0;
    }

    public void setCurrentAngle(float currentAngle) {
        this.currentAngle = currentAngle;
    }

}
