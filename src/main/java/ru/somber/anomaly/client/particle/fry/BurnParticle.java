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
        super(x, y, z, 20 + SomberCommonUtil.RANDOMIZER.nextInt(5), ParticleIcons.fireAnimFlame0Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;


        this.maxHeight = 4.3F;
        this.minSize = 1F;
        this.maxSize = 1.5F;

        this.xForce = 0;
        this.yForce = maxHeight / getMaxLifeTime();
        this.zForce = 0;

        setRotateAnglesZ((float) (randomizer.nextFloat() * Math.PI * 2));
        setHalfSizes(minSize, minSize);
        setBlendFactor(1);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, interpolateFactor);
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
        this.yForce *= 1.02F;

        setPositionX(getPositionX() + xForce);
        setPositionY(getPositionY() + yForce);
        setPositionZ(getPositionZ() + zForce);

        float lifeFactor = getLifeFactor();
        float alphaFactor = (float) (-Math.pow((lifeFactor - 0.7F), 2) + 0.3F);

        setBlendFactor((1 - lifeFactor) * 0.5F + 0.5F);
        setAlphaFactor(alphaFactor);

        float size = SomberCommonUtil.interpolateBetween(minSize, maxSize, getLifeFactor());
        setHalfSizes(size, size);
    }
}
