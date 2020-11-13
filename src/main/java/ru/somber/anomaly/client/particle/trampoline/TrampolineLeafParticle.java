package ru.somber.anomaly.client.particle.trampoline;

import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.util.clientutil.textureatlas.icon.MultiFrameAtlasIcon;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class TrampolineLeafParticle extends AbstractParticleSimpleData {

    private static final float xDelta = 4.2F;

    private final float xStart, yStart, zStart;
    private final float angle;
    private final float yFactor;
    private final float xFactor;
    private final int iconNumber;


    public TrampolineLeafParticle(float x, float y, float z, float angle, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.trashAnim3Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xStart = x + (randomizer.nextFloat() * 0.3F - 0.15F);
        this.yStart = y;
        this.zStart = z + (randomizer.nextFloat() * 0.3F - 0.15F);

        this.yFactor = 0.6F + randomizer.nextFloat() * 0.4F;
        this.xFactor = 0.4F + randomizer.nextFloat() * 0.2F;
        this.angle = angle;
        this.iconNumber = randomizer.nextInt(4);

        setPosition(xStart, yStart, zStart);
        setHalfSizes(0.08F, 0.08F);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        float lifeFactor = getLifeFactor();


        float x = getLifeFactor() * xDelta;

        float newX = xStart + MathHelper.cos(angle) * x * xFactor;
        float newY = yStart + (yFactor * 3 - (x - 2) * (x - 2)) * yFactor;
        float newZ = zStart + MathHelper.sin(angle) * x * xFactor;

        setPosition(newX, newY, newZ);
        setAlphaFactor(1);
//        setAlphaFactor((float) Math.pow((1 - lifeFactor), 0.5));
    }

    @Override
    public AtlasIcon getParticleIcon() {
        MultiFrameAtlasIcon multiIcon = (MultiFrameAtlasIcon) super.getParticleIcon();
        return multiIcon.getFrameIcon(iconNumber);
    }
}
