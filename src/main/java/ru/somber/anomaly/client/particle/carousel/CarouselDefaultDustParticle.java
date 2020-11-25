package ru.somber.anomaly.client.particle.carousel;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class CarouselDefaultDustParticle extends AbstractParticleSimpleData {

    private static final float sizes = 1.2F;
    private static final int countTicksForAppearance = 80;
    private static final float maxAlpha = 0.4F;
    private static final float moveAngleSpeed = 4.25F;

    private final float xCenterCircle, zCenterCircle;
    private final float radius;
    private final float rotateZAngleSpeed;
    private float visibleTicks;
    private float currentAngle;


    public CarouselDefaultDustParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, getRandomIcon());

        Random random = SomberCommonUtil.RANDOMIZER;

        this.xCenterCircle = x;
        this.zCenterCircle = z;

        this.radius = 1F + random.nextFloat() * 0.2F - 0.1F;
        this.rotateZAngleSpeed = 0.04F * random.nextFloat();

        setPositionX(xCenterCircle + (float) Math.cos(currentAngle) * radius);
        setPositionZ(zCenterCircle + (float) Math.sin(currentAngle) * radius);

        setHalfSizes(sizes, sizes);

        setBlendFactor(0);
        setRotateAnglesZ(360 * SomberCommonUtil.RANDOMIZER.nextFloat());
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        visibleTicks++;

        setPositionX(xCenterCircle + (float) Math.cos(Math.toRadians(currentAngle)) * radius);
        setPositionZ(zCenterCircle + (float) Math.sin(Math.toRadians(currentAngle)) * radius);

        currentAngle += moveAngleSpeed;

        setAlphaFactor(Math.min(visibleTicks / countTicksForAppearance, 1) * maxAlpha);

        addToRotateAngles(0, 0, rotateZAngleSpeed);
    }

    public void setVisible() {
        visibleTicks = 0;
        setHalfSizes(sizes, sizes);
    }

    public void setInvisible() {
        setHalfSizes(0, 0);
    }

    public void setCurrentAngle(float currentAngle) {
        this.currentAngle = currentAngle;
    }


    private static AtlasIcon getRandomIcon() {
        Random random = SomberCommonUtil.RANDOMIZER;
        int randomNumber = random.nextInt(8);

        switch (randomNumber) {
            case 0 : {
                return ParticleIcons.carouselDust0Icon;
            }

            case 1 : {
                return ParticleIcons.carouselDust1Icon;
            }

            case 2 : {
                return ParticleIcons.carouselDust2Icon;
            }

            case 3 : {
                return ParticleIcons.carouselDust3Icon;
            }

            case 4 : {
                return ParticleIcons.carouselDust4Icon;
            }

            case 5 : {
                return ParticleIcons.carouselDust5Icon;
            }

            case 6 : {
                return ParticleIcons.carouselDust6Icon;
            }

            case 7 : {
                return ParticleIcons.carouselDust7Icon;
            }
        }

        return ParticleIcons.carouselDust0Icon;
    }

}
