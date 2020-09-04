package ru.somber.anomaly.particle.kisel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.particlesystem.particle.AbstractSphericalParticle;
import ru.somber.particlesystem.texture.ParticleAtlasIcon;

public class KiselBubbleGroupParticle extends AbstractSphericalParticle {


    public KiselBubbleGroupParticle(Vector3f newPosition, int maxLifeTime, ParticleAtlasIcon icon) {
        super(newPosition, maxLifeTime, icon);
    }
}
