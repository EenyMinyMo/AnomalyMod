package ru.somber.anomaly.client.particle.fry;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.util.clientutil.textureatlas.icon.MultiFrameAtlasIcon;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class BurnParticle extends AbstractParticleSimpleData {

    private final float maxHeight;
    private final float minSize, maxSize;
    private float xForce, yForce, zForce;


    public BurnParticle(float x, float y, float z) {
        super(x, y, z, 10 + SomberCommonUtil.RANDOMIZER.nextInt(15), ParticleIcons.fireAnimFlame0Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;


        this.maxHeight = 5F;
        this.minSize = 1F;
        this.maxSize = 1.5F;

        this.xForce = 0;
        this.yForce = maxHeight / getMaxLifeTime();
        this.zForce = 0;

//        setColorFactor(0.9F, 0.75F, 0.75F, 1);

        setRotateAnglesZ((float) (randomizer.nextFloat() * Math.PI * 2));
        setHalfSizes(minSize, minSize);
        setBlendFactor(1);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }

    @Override
    public AtlasIcon getParticleIcon() {
        MultiFrameAtlasIcon icon = (MultiFrameAtlasIcon) super.getParticleIcon();

        int iconNumber = (int) ((getLifeTime() - 1) * 4F / getMaxLifeTime());

        return icon.getFrameIcon(iconNumber);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        this.xForce += ((randomizer.nextFloat() - 0.5F) * 0.015F);
        this.zForce += ((randomizer.nextFloat() - 0.5F) * 0.015F);
        this.yForce *= 1.002F;

        setPositionX(getPositionX() + xForce);
        setPositionY(getPositionY() + yForce);
        setPositionZ(getPositionZ() + zForce);

        float lifeFactor = getLifeFactor();
        float alphaFactor = (float) (-Math.pow((lifeFactor - 0.7F), 2) + 0.25);

        setBlendFactor((1 - lifeFactor) * 0.5F + 0.5F);
        setAlphaFactor(alphaFactor);

        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, (float) (-Math.pow((lifeFactor - 0.5F), 2) + 0.25F) * 4F);
        setHalfSizes(size, size);
    }
}
