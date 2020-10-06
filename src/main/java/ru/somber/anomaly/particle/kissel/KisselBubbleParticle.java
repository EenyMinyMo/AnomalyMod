package ru.somber.anomaly.particle.kissel;

import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class KisselBubbleParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private final float minSize, maxSize;
    private final float xStart, yStart, zStart;


    public KisselBubbleParticle(float x, float y, float z) {
        super(x, y, z, 20 + SomberCommonUtil.RANDOMIZER.nextInt(5), ParticleIcons.otherBubble3Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.xStart = x;
        this.yStart = y;
        this.zStart = z;

        this.maxHeight = 0.6F + randomizer.nextFloat() * 0.3F;
        this.minSize = 0.085F;
        this.maxSize = 0.6F;

        setColorFactor(0.0F, 1.0F, 0.0F, 1.0F);
        setHalfSizes(0.3F, 0.6F);
        setBlendFactor(1.0F);
    }

    public KisselBubbleParticle(Vector3f newPosition) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ());
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
        float sin = MathHelper.sin( ((float)Math.PI * lifeFactor));
        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, lifeFactor * 0.9F);

        setPositionY(yStart + maxHeight * (float) Math.pow(sin, 0.75F) - 0.1F);
        setHalfSizes(size,size);

//        setHalfSizes(1F * MathHelper.sin(getLifeTime() / ((float)Math.PI * 2)), 1F * MathHelper.sin(getLifeTime() / ((float)Math.PI * 2)));
//        setRotateAnglesZ(((float)Math.PI * lifeFactor));
    }

}

