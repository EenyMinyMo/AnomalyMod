package ru.somber.anomaly.particle.kisel;

import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIconNames;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

public class KiselDistortionBubbleParticle extends AbstractSphericalParticle {

    private final float maxHeight;
    private float x;
    private float y;
    private float z;

    public KiselDistortionBubbleParticle(Vector3f newPosition) {
        super(newPosition, 3 * 20 + ((int) (10 * Math.random())), ParticleIconNames.distortion17Icon);

        x = newPosition.x;
        y = newPosition.y;
        z = newPosition.z;

        maxHeight = 0.8F + (float) Math.random() * 0.2F;
        setColorFactor(1, 1, 1, 0.3F + (float) Math.random() * 0.3F);

        float sizeRandom = 0.1F + (float) Math.random() * 0.05F;
//        sizeRandom = 0.5F;
        setHalfSizes(sizeRandom, sizeRandom);
    }

    @Override
    public void update() {
        super.update();

        lifeTime++;

        oldPosition.set(newPosition);
        newPosition.setY(newPosition.getY() + maxHeight * (1.0F / maxLifeTime));

//        newPosition.setX(x - 0.3F * (1 - MathHelper.sin((float) Math.PI / 2 + (float) Math.PI * lifeTime / maxLifeTime / 2)));
//        newPosition.setZ(z - 0.3F * (1 - MathHelper.sin((float) Math.PI / 2 + (float) Math.PI * lifeTime / maxLifeTime / 2)));

//        float log = (float) Math.log10((double) lifeTime / maxLifeTime);
//        float colorFactor =  1 - (log + 1F);
//        setColorFactor(1, 1, 1, 0.5F * colorFactor);
    }
}
