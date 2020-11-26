package ru.somber.anomaly.client.particle;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public abstract class AbstractDustParticle extends AbstractParticleSimpleData {

    public AbstractDustParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, getRandomDustIcon());
    }

    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }


    private static AtlasIcon getRandomDustIcon() {
        Random random = SomberCommonUtil.RANDOMIZER;
        int randomNumber = random.nextInt(8);

        switch (randomNumber) {
            case 0 : {
                return ParticleIcons.dust0Icon;
            }

            case 1 : {
                return ParticleIcons.dust1Icon;
            }

            case 2 : {
                return ParticleIcons.dust2Icon;
            }

            case 3 : {
                return ParticleIcons.dust3Icon;
            }

            case 4 : {
                return ParticleIcons.dust4Icon;
            }

            case 5 : {
                return ParticleIcons.dust5Icon;
            }

            case 6 : {
                return ParticleIcons.dust6Icon;
            }

            case 7 : {
                return ParticleIcons.dust7Icon;
            }
        }

        return ParticleIcons.dust0Icon;
    }

}
