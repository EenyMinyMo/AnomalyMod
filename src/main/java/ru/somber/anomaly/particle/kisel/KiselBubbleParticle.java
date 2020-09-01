package ru.somber.anomaly.particle.kisel;

import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIconNames;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

public class KiselBubbleParticle extends AbstractSphericalParticle {

    private final float maxHeight;
    private float x, y, z;

    public KiselBubbleParticle(Vector3f newPosition) {
        super(newPosition, 20 + ((int) (Math.random() * 5)), ParticleIconNames.otherBubble0Icon);

        x = newPosition.getX();
        y = newPosition.getY();
        z = newPosition.getZ();

        maxHeight = 0.5F + (float) Math.random() * 0.25F;
//        setColorFactor(0.1F, 0.9F, 0.1F, 1F);
        setHalfSizes(0.2F, 0.4F);
    }

    @Override
    public void update() {
        super.update();

        lifeTime++;

        oldPosition.set(newPosition);

        float lifeFactor = ((float)lifeTime) / maxLifeTime;
        float sin = MathHelper.sin( ((float)Math.PI * lifeFactor));
        newPosition.setY(y + maxHeight * (float) Math.pow(sin, 0.75F) - 0.1F);

//        setHalfSizes(1F * MathHelper.sin(lifeTime / ((float)Math.PI * 2)), 1F * ));
    }
}

