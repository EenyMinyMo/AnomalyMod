package ru.somber.anomaly.client.emitter;

import ru.somber.anomaly.client.particle.electra.*;
import ru.somber.particlesystem.particle.IParticle;
import ru.somber.util.commonutil.SomberCommonUtil;

import java.util.Random;

public class ElectraEmitter extends AbstractAnomalyEmitter {

    private float cooldownAdditionalStaticParticle;
    private float cooldownSphericalParticle;


    public ElectraEmitter(float x, float y, float z) {
        super(x, y, z);
    }

    @Override
    public void create() {
        super.create();

        setTick(SomberCommonUtil.RANDOMIZER.nextInt(100));
    }

    @Override
    public void delete() {
        super.delete();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void updateDefaultPhase(int currentTick, int phaseTickDuration) {
        super.updateDefaultPhase(currentTick, phaseTickDuration);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        createStaticElectraParticle();


        if (cooldownAdditionalStaticParticle == 0) {
            createAdditionStaticElectraParticle();
            cooldownAdditionalStaticParticle = 6 + randomizer.nextInt(10);
        }

        if (cooldownSphericalParticle == 0) {
            createSphericalElectraParticle();
            cooldownSphericalParticle = 1 + randomizer.nextInt(3);
        }

        cooldownAdditionalStaticParticle--;
        cooldownSphericalParticle--;
    }

    @Override
    public void updateActivePhase(int currentTick, int phaseTickDuration) {
        super.updateActivePhase(currentTick, phaseTickDuration);

        for (int i = 0; i < 36; i++) {
            createDischargeStaticElectraParticle();
            createDischargeSphericalElectraParticle();
        }
    }

    @Override
    public void updateSleepPhase(int currentTick, int phaseTickDuration) {
        super.updateSleepPhase(currentTick, phaseTickDuration);
    }



    private void createStaticElectraParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 1.8F - 0.9F;
        float y = getPositionY() + 0.03125F;
        float z = getPositionZ() + randomizer.nextFloat() * 1.8F - 0.9F;

        IParticle particle = new ElectraStaticParticle(x, y, z, 10);
        addParticleContainer(particle);
    }

    private void createAdditionStaticElectraParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 1.6F - 0.8F;
        float y = getPositionY() + 0.03125F;
        float z = getPositionZ() + randomizer.nextFloat() * 1.6F - 0.8F;

        IParticle particle = new ElectraStaticAdditionalParticle(x, y, z, 10);
        addParticleContainer(particle);
    }

    private void createSphericalElectraParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 1.8F - 0.9F;
        float y = getPositionY() + randomizer.nextFloat() * 0.8F;
        float z = getPositionZ() + randomizer.nextFloat() * 1.8F - 0.9F;

        IParticle particle = new ElectraSphericalParticle(x, y, z, 8);
        addParticleContainer(particle);
    }

    private void createDischargeStaticElectraParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 3F - 1.5F;
        float y = getPositionY() + randomizer.nextFloat() + 0.2F;
        float z = getPositionZ() + randomizer.nextFloat() * 3F - 1.5F;

        IParticle particle = new ElectraDischargeStaticParticle(x, y, z, 6 + randomizer.nextInt(6));
        addParticleContainer(particle);
    }

    private void createDischargeSphericalElectraParticle() {
        Random randomizer = SomberCommonUtil.RANDOMIZER;

        float x = getPositionX() + randomizer.nextFloat() * 3F - 1.5F;
        float y = getPositionY() + randomizer.nextFloat() + 0.2F;
        float z = getPositionZ() + randomizer.nextFloat() * 3F - 1.5F;

        IParticle particle = new ElectraDischargeSphericalParticle(x, y, z, 4 + randomizer.nextInt(10));
        addParticleContainer(particle);
    }


}
