package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.acidmist.AcidMistBigFogParticle;
import ru.somber.anomaly.client.particle.acidmist.AcidMistSmallFogParticle;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.IParticle;

import java.util.Random;

public class AcidMistEmitter extends AbstractAnomalyEmitter {

    public AcidMistEmitter(float x, float y, float z) {
        super(x, y, z);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void delete() {
        super.delete();
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        if (getTick() % 18 == 1) {
            createBigFogParticle();
        }

        if (getTick() % 16 == 1) {
            createSmallFogParticle();
        }
    }


    private void createBigFogParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 3F - 1.5F;
        float y = getPositionY() + 0.3F;
        float z = getPositionZ() + randomizer.nextFloat() * 3F - 1.5F;

        IParticle particle = new AcidMistBigFogParticle(x, y, z);

        addParticleInEmitter(particle);
        addParticleContainer(particle);

        particle = new AcidMistBigFogParticle(x - 0.4F, y + 0.3F, z + 0.3F);

        addParticleInEmitter(particle);
        addParticleContainer(particle);

        particle = new AcidMistBigFogParticle(x + 0.35F, y - 0.1F, z + 0.1F);

        addParticleInEmitter(particle);
        addParticleContainer(particle);
    }

    private void createSmallFogParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 3F - 1.5F;
        float y = getPositionY() + 0.3F;
        float z = getPositionZ() + randomizer.nextFloat() * 3F - 1.5F;

        IParticle particle = new AcidMistSmallFogParticle(x, y, z);

        addParticleInEmitter(particle);
        addParticleContainer(particle);
    }

}

