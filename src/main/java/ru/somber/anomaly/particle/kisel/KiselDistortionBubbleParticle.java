package ru.somber.anomaly.particle.kisel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

public class KiselDistortionBubbleParticle extends AbstractSphericalParticle {

    private final float maxHeight;
    private final float xStart, yStart, zStart;

    public KiselDistortionBubbleParticle(float x, float y, float z) {
        super(x, y, z, 3 * 20 + ((int) (10 * Math.random())), ParticleIcons.distortion17Icon);

        this.xStart = x;
        this.yStart = y;
        this.zStart = z;

        maxHeight = 0.8F + (float) Math.random() * 0.2F;
        setRotateAnglesZ((float) Math.toRadians(-180));
        setAlphaFactor(0.2F + (float) Math.random() * 0.1F);
//        setColorFactor(3, 3, 3, 3);

        float sizeRandom = 0.1F + (float) Math.random() * 0.05F;
        setHalfSizes(sizeRandom, sizeRandom);
//        setBlendFactor(1);
    }

    public KiselDistortionBubbleParticle(Vector3f newPosition) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ());
    }

    @Override
    public void update() {
        super.update();

        setPositionY(getPositionY() + maxHeight * (1.0F / getMaxLifeTime()));


//        newPosition.setX(x - 0.3F * (1 - MathHelper.sin((float) Math.PI / 2 + (float) Math.PI * lifeTime / maxLifeTime / 2)));
//        newPosition.setZ(z - 0.3F * (1 - MathHelper.sin((float) Math.PI / 2 + (float) Math.PI * lifeTime / maxLifeTime / 2)));

//        float log = (float) Math.log10((double) lifeTime / maxLifeTime);
//        float colorFactor =  1 - (log + 1F);
//        setColorFactor(1, 1, 1, 0.5F * colorFactor);
    }
}
