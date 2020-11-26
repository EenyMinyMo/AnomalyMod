package ru.somber.anomaly.client.particle;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public abstract class AbstractLeafParticle extends AbstractParticleSimpleData {

    public AbstractLeafParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, getRandomLeafIcon());
    }

    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }


    private static AtlasIcon getRandomLeafIcon() {
        Random random = SomberCommonUtil.RANDOMIZER;
        int randomNumber = random.nextInt(9);

        switch (randomNumber) {
            case 0 : {
                return ParticleIcons.trash3Icon;
            }

            case 1 : {
                return ParticleIcons.trash4Icon;
            }

            case 2 : {
                return ParticleIcons.trash5Icon;
            }

            case 3 : {
                return ParticleIcons.trash6Icon;
            }

            case 4 : {
                return ParticleIcons.trash7Icon;
            }

            case 5 : {
                return ParticleIcons.trash8Icon;
            }

            case 6 : {
                return ParticleIcons.trash9Icon;
            }

            case 7 : {
                return ParticleIcons.trash10Icon;
            }

            case 8 : {
                return ParticleIcons.trash11Icon;
            }
        }

        return ParticleIcons.trash0Icon;
    }

}
