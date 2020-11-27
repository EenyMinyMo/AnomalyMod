package ru.somber.anomaly.client.particle;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public abstract class AbstractLeafParticle extends AbstractParticleSimpleData {

    private static final AtlasIcon[] iconsArray = new AtlasIcon[] {
            ParticleIcons.trash3Icon,
            ParticleIcons.trash4Icon,
            ParticleIcons.trash5Icon,
            ParticleIcons.trash6Icon,
            ParticleIcons.trash7Icon,
            ParticleIcons.trash8Icon,
            ParticleIcons.trash9Icon,
            ParticleIcons.trash10Icon,
            ParticleIcons.trash11Icon,
    };


    public AbstractLeafParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, getRandomLeafIcon());
    }

    @Override
    public void computeNormalVector(Vector3f destination, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, interpolateFactor);
    }


    private static AtlasIcon getRandomLeafIcon() {
        Random random = SomberCommonUtil.RANDOMIZER;
        int randomNumber = random.nextInt(iconsArray.length);
        return iconsArray[randomNumber];
    }

}
