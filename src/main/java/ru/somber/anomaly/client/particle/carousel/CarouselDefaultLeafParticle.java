package ru.somber.anomaly.client.particle.carousel;

import ru.somber.anomaly.client.particle.AbstractLeafParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class CarouselDefaultLeafParticle extends AbstractLeafParticle {

    private static final float sizes = 0.06F;
    private static final int countTicksForAppearance = 80;
    private static final float maxAlpha = 1F;
    private static final float moveAngleSpeed = 4.25F;

    private final float xCenterCircle, zCenterCircle;
    private final float radius;
    private final float rotateZAngleSpeed;
    private float visibleTicks;
    private float currentAngle;


    public CarouselDefaultLeafParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime);

        Random random = SomberCommonUtil.RANDOMIZER;

        this.xCenterCircle = x;
        this.zCenterCircle = z;

        this.radius = 1F + random.nextFloat() * 0.6F - 0.3F;
        this.rotateZAngleSpeed = 0.1F * random.nextFloat() - 0.05F;

        setPositionX(xCenterCircle + (float) Math.cos(currentAngle) * radius);
        setPositionZ(zCenterCircle + (float) Math.sin(currentAngle) * radius);

        setHalfSizes(sizes, sizes);

        setBlendFactor(0);
        setRotateAnglesZ((float) Math.PI * 2 * SomberCommonUtil.RANDOMIZER.nextFloat());
    }


    @Override
    public void update() {
        super.update();

        visibleTicks++;

        setPositionX(xCenterCircle + (float) Math.cos(Math.toRadians(currentAngle)) * radius);
        setPositionZ(zCenterCircle + (float) Math.sin(Math.toRadians(currentAngle)) * radius);

        currentAngle += moveAngleSpeed;

        setAlphaFactor(Math.min(visibleTicks / countTicksForAppearance, 1) * maxAlpha);

        addToRotateAngles(0, 0, rotateZAngleSpeed);
    }

    public void setVisible() {
        visibleTicks = 0;
        setHalfSizes(sizes, sizes);
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

    public void setCurrentAngle(float currentAngle) {
        this.currentAngle = currentAngle;
    }

}
