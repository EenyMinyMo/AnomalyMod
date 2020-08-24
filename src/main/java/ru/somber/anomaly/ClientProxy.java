package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.somber.particlesystem.container.IParticleContainer;
import ru.somber.particlesystem.container.ListParticleContainer;
import ru.somber.particlesystem.manager.IParticleManager;
import ru.somber.particlesystem.manager.SimpleParticleManager;
import ru.somber.particlesystem.render.GeometryShaderParticleRenderer;
import ru.somber.particlesystem.render.IParticleRenderer;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    private static IParticleManager particleManager;
    private static IParticleManager distortionParticleManager;

    public ClientProxy() {

    }

    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);

        IParticleContainer particleContainer = new ListParticleContainer();
        IParticleRenderer particleRenderer = new GeometryShaderParticleRenderer();
        particleManager = new SimpleParticleManager();
        particleManager.setParticleContainer(particleContainer);
        particleManager.setParticleRenderer(particleRenderer);


        IParticleContainer distortionParticleContainer = new ListParticleContainer();
        IParticleRenderer distortionParticleRenderer = new GeometryShaderParticleRenderer();
        distortionParticleManager = new SimpleParticleManager();
        distortionParticleManager.setParticleContainer(distortionParticleContainer);
        distortionParticleManager.setParticleRenderer(distortionParticleRenderer);
    }

    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }


    public static IParticleManager getParticleManager() {
        return particleManager;
    }

    public static IParticleManager getDistortionParticleManager() {
        return distortionParticleManager;
    }
}
