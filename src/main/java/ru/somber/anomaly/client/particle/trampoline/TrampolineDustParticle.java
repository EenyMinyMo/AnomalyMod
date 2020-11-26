package ru.somber.anomaly.client.particle.trampoline;

import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class TrampolineDustParticle extends AbstractParticleSimpleData {

    private final float minSize, maxSize;
    private float moveX, moveY, moveZ;



    public TrampolineDustParticle(float x, float y, float z, float angle, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.smoke1Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        this.moveX = 0;
        this.moveY = 0;
        this.moveZ = 0;

        float speed = 0.5F;
        this.moveX = MathHelper.cos(angle) * speed * (randomizer.nextFloat() * 0.6F + 0.4F);
        this.moveZ = MathHelper.sin(angle) * speed * (randomizer.nextFloat() * 0.6F + 0.4F);
        this.moveY = randomizer.nextFloat() * 0.13F + 0.01F;

        this.minSize = 1F + randomizer.nextFloat() * 0.2F;
        this.maxSize = 2F + randomizer.nextFloat() * 1F;

        setHalfSizes(minSize, minSize);
        setBlendFactor(0.0F);
    }


    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorSphericalParticle(destination, lookAtX, lookAtY, lookAtZ, interpolateFactor);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float lifeFactor = getLifeFactor();

        moveX *= 0.905F;
        moveZ *= 0.905F;
        moveY -= moveY * 0.05F;

        addToPosition(moveX, moveY, moveZ);

        float size = SomberCommonUtil.interpolateBetween(minSize,maxSize,lifeFactor);
        setHalfSizes(size, size);

        float alphaFactor = 1 - lifeFactor;
        setAlphaFactor((float) Math.pow(alphaFactor, 1.25F) * 0.25F);
    }

}
