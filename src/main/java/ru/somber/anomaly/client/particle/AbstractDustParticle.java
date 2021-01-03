package ru.somber.anomaly.client.particle;

import ru.somber.anomaly.client.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public abstract class AbstractDustParticle extends AbstractParticleSimpleData {

    private static final AtlasIcon[] iconsArray = new AtlasIcon[] {
            ParticleIcons.dust0Icon,
            ParticleIcons.dust1Icon,
            ParticleIcons.dust2Icon,
            ParticleIcons.dust3Icon,
            ParticleIcons.dust4Icon,
            ParticleIcons.dust5Icon,
            ParticleIcons.dust6Icon,
            ParticleIcons.dust7Icon,
    };


    public AbstractDustParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, getRandomDustIcon());
    }

    @Override
    public void update() {
        super.update();
        computeNormalVectorSphericalParticle();
    }


    private static AtlasIcon getRandomDustIcon() {
        Random random = SomberCommonUtil.RANDOMIZER;
        int randomNumber = random.nextInt(iconsArray.length);
        return iconsArray[randomNumber];
    }

}
