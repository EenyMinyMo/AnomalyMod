package ru.somber.anomaly.emitter;

import ru.somber.anomaly.ParticleIcons;
import ru.somber.anomaly.particle.trampoline.TrampolineDistortionParticle;
import ru.somber.anomaly.particle.trampoline.TrampolineFlashParticle;
import ru.somber.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.commonutil.SomberCommonUtils;

import java.util.Random;

public class TrampolineEmitter extends AbstractAnomalyEmitter {

    private TrampolineFlashParticle flashParticle;
    private boolean particleInit;
    private int deltaTimeFlashParticle;

    public TrampolineEmitter(float x, float y, float z) {
        super(x, y, z);

        Random randomizer = SomberCommonUtils.RANDOMIZER;
        deltaTimeFlashParticle = 20 + randomizer.nextInt(20);
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

        Random randomizer = SomberCommonUtils.RANDOMIZER;

        if (! particleInit) {
            particleInit = true;
            flashParticle = createFlashParticle(randomizer);
            addParticleContainer(flashParticle);
        }


        updateIdleParticle(randomizer);

        if (getTick() % 100 == 0) {
            createDistortionParticle(randomizer);
        }

    }

    private void updateIdleParticle(Random randomizer) {
        if (getTick() % deltaTimeFlashParticle == 0) {
            float randomX = getPositionX() - 0.3F + randomizer.nextFloat() * 0.6F;
            float randomY = getPositionY() + 0.2F + randomizer.nextFloat() * 0.6F;
            float randomZ = getPositionZ() - 0.3F + randomizer.nextFloat() * 0.6F;
            flashParticle.setPosition(randomX, randomY, randomZ);
            flashParticle.setVisible();

            deltaTimeFlashParticle = 30 + randomizer.nextInt(20);
        }
    }

    private void createDistortionParticle(Random randomizer) {
        int maxLifeTime = 100;
        AtlasIcon icon = ParticleIcons.distortion3Icon;

        TrampolineDistortionParticle distortionParticle = new TrampolineDistortionParticle(getPositionX(), getPositionY(), getPositionZ(), maxLifeTime, icon);

        addDistortionParticleContainer(distortionParticle);
    }

    private TrampolineFlashParticle createFlashParticle(Random randomizer) {
        AtlasIcon icon = ParticleIcons.anomaly0Icon;

        TrampolineFlashParticle flashParticle = new TrampolineFlashParticle(getPositionX(), getPositionY(), getPositionZ(), Integer.MAX_VALUE, icon);

        return flashParticle;
    }

}
