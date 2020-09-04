package ru.somber.anomaly.particle.kisel;

import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

public class KiselBubbleParticle extends AbstractSphericalParticle {

    private final float maxHeight;
    private final float xStart, yStart, zStart;

    public KiselBubbleParticle(float x, float y, float z) {
        super(x, y, z, 20 + ((int) (Math.random() * 5)), ParticleIcons.otherBubble5Icon);

        this.xStart = x;
        this.yStart = y;
        this.zStart = z;

        maxHeight = 0.5F + (float) Math.random() * 0.25F;
        setColorFactor(0.0F, 1.0F, 0.0F, 1.0F);
        setHalfSizes(0.3F, 0.6F);
        setBlendFactor(1.0F);
        setLightFactor(1.0F);
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

//        setHalfSizes(1F * MathHelper.sin(getLifeTime() / ((float)Math.PI * 2)), 1F * MathHelper.sin(getLifeTime() / ((float)Math.PI * 2)));
//        setRotateAnglesZ(((float)Math.PI * lifeFactor));
    }

}

