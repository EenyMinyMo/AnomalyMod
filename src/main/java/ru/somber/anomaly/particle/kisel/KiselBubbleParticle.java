package ru.somber.anomaly.particle.kisel;

import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIconNames;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;

public class KiselBubbleParticle extends AbstractSphericalParticle {

    private final float maxHeight;

    public KiselBubbleParticle(Vector3f newPosition) {
        super(newPosition, 5 * 20 * ((int) (5 * Math.random())), ParticleIconNames.smokeAnimEnerg0Icon);

        maxHeight = 1.2F + (float) Math.random() * 0.3F;
//        setColorFactor(0.1F, 0.9F, 0.1F, 1F);
        setHalfSizes(0.15F, 0.15F);
    }

    @Override
    public void update() {
        super.update();

        lifeTime++;

        oldPosition.set(newPosition);
        newPosition.setY(newPosition.getY() + maxHeight * (1.0F / maxLifeTime));
//        setHalfSizes(1F * MathHelper.sin(lifeTime / ((float)Math.PI * 2)), 1F * MathHelper.sin(lifeTime / ((float)Math.PI * 2)));
    }
}
