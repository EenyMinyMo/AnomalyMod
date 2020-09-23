package ru.somber.anomaly.particle.trampoline;

import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.clientutil.textureatlas.icon.MultiFrameAtlasIcon;
import ru.somber.commonutil.SomberCommonUtils;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

import java.util.Random;

public class TrampolineLeafParticle extends AbstractSphericalParticle {

    private final float xStart, yStart, zStart;
    private final float minRadius, maxRadius;
    private final float maxHeight;
    private final float offsetAngleRadians;
    private final int iconNumber;


    public TrampolineLeafParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.trashAnim3Icon);

        Random randomizer = SomberCommonUtils.RANDOMIZER;

        this.xStart = x;
        this.yStart = y;
        this.zStart = z;

        this.minRadius = 0.1F + randomizer.nextFloat() * 0.3F;
        this.maxRadius = 1.5F + randomizer.nextFloat() * 1.5F;
        this.maxHeight = 0.4F + randomizer.nextFloat() * 3.6F;

        this.offsetAngleRadians = (float) Math.PI * 2 * randomizer.nextFloat();

        this.iconNumber = randomizer.nextInt(4);

        setHalfSizes(0.08F, 0.08F);


        float newY = yStart;
        float newX = xStart + MathHelper.cos(offsetAngleRadians) * minRadius;
        float newZ = zStart + MathHelper.sin(offsetAngleRadians) * minRadius;
        setPosition(newX, newY, newZ);
    }

    public TrampolineLeafParticle(Vector3f newPosition, int maxLifeTime) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtils.RANDOMIZER;
        float lifeFactor = getLifeFactor();

        float rotateAngleRadian = (float) Math.PI * lifeFactor * 3 + offsetAngleRadians;
        float interpolateRadius = SomberCommonUtils.interpolateBetween(minRadius, maxRadius, lifeFactor);
        float newY = yStart + maxHeight * lifeFactor * lifeFactor;
        float newX = xStart + MathHelper.cos(rotateAngleRadian) * interpolateRadius;
        float newZ = zStart + MathHelper.sin(rotateAngleRadian) * interpolateRadius;

        setPosition(newX, newY, newZ);
        setAlphaFactor((float) Math.pow((1 - lifeFactor), 0.5));
    }

    @Override
    public AtlasIcon getParticleIcon() {
        MultiFrameAtlasIcon multiIcon = (MultiFrameAtlasIcon) super.getParticleIcon();
        return multiIcon.getFrameIcon(iconNumber);
    }
}
