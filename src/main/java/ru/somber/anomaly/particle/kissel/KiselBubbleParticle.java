package ru.somber.anomaly.particle.kissel;

import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.commonutil.SomberCommonUtils;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

public class KiselBubbleParticle extends AbstractSphericalParticle {

    private final float maxHeight;
    private final float minSize, maxSize;
    private final float xStart, yStart, zStart;

    public KiselBubbleParticle(float x, float y, float z) {
        super(x, y, z, 20 + ((int) (Math.random() * 5)), ParticleIcons.otherBubble3Icon);

        this.xStart = x;
        this.yStart = y;
        this.zStart = z;

        this.maxHeight = 0.6F + (float) Math.random() * 0.3F;
        this.minSize = 0.085F;
        this.maxSize = 0.6F;

        setColorFactor(0.0F, 1.0F, 0.0F, 1.0F);
        setHalfSizes(0.3F, 0.6F);
        setBlendFactor(1.0F);
    }

    public KiselBubbleParticle(Vector3f newPosition) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ());
    }

    @Override
    public void update() {
        super.update();

        float lifeFactor = ((float) getLifeTime()) / getMaxLifeTime();

        float sin = MathHelper.sin( ((float)Math.PI * lifeFactor));
        setPositionY(yStart + maxHeight * (float) Math.pow(sin, 0.75F) - 0.1F);

        float size = SomberCommonUtils.interpolateBetween(minSize, maxSize, lifeFactor* 0.9F);
        setHalfSizes(size,size);

//        setHalfSizes(1F * MathHelper.sin(getLifeTime() / ((float)Math.PI * 2)), 1F * MathHelper.sin(getLifeTime() / ((float)Math.PI * 2)));
//        setRotateAnglesZ(((float)Math.PI * lifeFactor));
    }

}

