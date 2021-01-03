package ru.somber.anomaly.client.particle.trampoline;

import net.minecraft.util.MathHelper;
import ru.somber.anomaly.client.particle.AbstractLeafParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class TrampolineLeafParticle extends AbstractLeafParticle {

    private static final float xDelta = 4.2F;

    private final float xStart, yStart, zStart;
    private final float angle;
    private final float yFactor;
    private final float xFactor;


    public TrampolineLeafParticle(float x, float y, float z, float angle, int maxLifeTime) {
        super(x, y, z, maxLifeTime);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xStart = x + (randomizer.nextFloat() * 0.3F - 0.15F);
        this.yStart = y;
        this.zStart = z + (randomizer.nextFloat() * 0.3F - 0.15F);

        this.yFactor = 0.6F + randomizer.nextFloat() * 0.4F;
        this.xFactor = 0.4F + randomizer.nextFloat() * 0.2F;
        this.angle = angle;

        setPosition(xStart, yStart, zStart);
        setHalfSizes(0.08F, 0.08F);

        setAlphaFactor(1);
    }


    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        float lifeFactor = getLifeFactor();

        float x = lifeFactor * xDelta;

        float newX = xStart + MathHelper.cos(angle) * x * xFactor;
        float newY = yStart + (yFactor * 3 - (x - 2) * (x - 2)) * yFactor;
        float newZ = zStart + MathHelper.sin(angle) * x * xFactor;

        setPosition(newX, newY, newZ);
    }

}
