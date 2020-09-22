package ru.somber.anomaly.emitter;

import ru.somber.anomaly.ClientProxy;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.anomaly.particle.trampoline.TrampolineDistortionParticle;
import ru.somber.anomaly.particle.trampoline.TrampolineFlashParticle;
import ru.somber.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.particlesystem.emitter.AbstractEmitter;

public class TrampolineEmitter extends AbstractAnomalyEmitter {

    private TrampolineFlashParticle flashParticle;
    private boolean particleInit;
    private int deltaTimeFlashParticle;

    public TrampolineEmitter(float x, float y, float z) {
        super(x, y, z);
        setTick((int) (Math.random() * 100));
        deltaTimeFlashParticle = 20 + (int) (20 * Math.random());
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

        if (! particleInit) {
            flashParticle = createFlashParticle();

            addParticleInEmitter(flashParticle);

            ClientProxy.getParticleManager().getParticleContainer().addParticle(flashParticle);

            particleInit = true;
        }


        updateIdleParticle();

        if (getTick() % 60 == 0) {
            createDistortionParticle();
        }

    }

    private void updateIdleParticle() {
        if (getTick() % deltaTimeFlashParticle == 0) {
            float randomX = getPositionX() - 0.3F + ((float) Math.random()) * 0.6F;
            float randomY = getPositionY() + 0.2F + ((float) Math.random()) * 0.6F;
            float randomZ = getPositionZ() - 0.3F + ((float) Math.random()) * 0.6F;
            flashParticle.setPosition(randomX, randomY, randomZ);
            flashParticle.setVisible();

            deltaTimeFlashParticle = 30 + (int) (20 * Math.random());
        }
    }

    private void createDistortionParticle() {
        int maxLifeTime = 60;
        AtlasIcon icon = ParticleIcons.distortion3Icon;

        TrampolineDistortionParticle distortionParticle = new TrampolineDistortionParticle(getPositionX(), getPositionY(), getPositionZ(), maxLifeTime, icon);

        addParticleInEmitter(distortionParticle);
        ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(distortionParticle);
    }

    private TrampolineFlashParticle createFlashParticle() {
//        int cycleTime = 60;
        AtlasIcon icon = ParticleIcons.anomaly0Icon;

        TrampolineFlashParticle flashParticle = new TrampolineFlashParticle(getPositionX(), getPositionY(), getPositionZ(), Integer.MAX_VALUE, icon);

        return flashParticle;
    }

}
