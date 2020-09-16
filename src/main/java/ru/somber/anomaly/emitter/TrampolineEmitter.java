package ru.somber.anomaly.emitter;

import ru.somber.anomaly.ClientProxy;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.anomaly.particle.trampoline.TrampolineDistortionParticle;
import ru.somber.anomaly.particle.trampoline.TrampolineFlashParticle;
import ru.somber.particlesystem.emitter.AbstractEmitter;
import ru.somber.particlesystem.texture.ParticleAtlasIcon;

public class TrampolineEmitter extends AbstractEmitter {

    private TrampolineDistortionParticle distortionParticle;
    private TrampolineFlashParticle flashParticle;

    public TrampolineEmitter(float x, float y, float z) {
        super(x, y, z);

        createDistortionParticle();
        createFlashParticle();
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

        createDistortionParticle();

    }

    private void updateIdleParticle() {

    }

    private void createDistortionParticle() {
        int maxLifeTime = 60;
        ParticleAtlasIcon icon = ParticleIcons.distortion3Icon;

        if (getTick() % maxLifeTime == 0) {
            distortionParticle = new TrampolineDistortionParticle(getPositionX(), getPositionY(), getPositionZ(), maxLifeTime, icon);

            addParticleInEmitter(distortionParticle);
            ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(distortionParticle);
        }

    }

    private void createFlashParticle() {
        int maxLifeTime = 60;
        ParticleAtlasIcon icon = ParticleIcons.distortion3Icon;

        if (getTick() % maxLifeTime == 0) {
            flashParticle = new TrampolineFlashParticle(getPositionX(), getPositionY(), getPositionZ(), maxLifeTime, icon);

            addParticleInEmitter(flashParticle);
            ClientProxy.getDistortionParticleManager().getParticleContainer().addParticle(flashParticle);
        }

    }

}
