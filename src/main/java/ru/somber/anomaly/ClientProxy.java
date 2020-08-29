package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.somber.anomaly.render.DistortionParticleRenderer;
import ru.somber.particlesystem.ParticleAPI;
import ru.somber.particlesystem.container.IParticleContainer;
import ru.somber.particlesystem.container.ListParticleContainer;
import ru.somber.particlesystem.manager.IParticleManager;
import ru.somber.particlesystem.manager.SimpleParticleManager;
import ru.somber.particlesystem.render.GeometryShaderParticleRenderer;
import ru.somber.particlesystem.render.IParticleRenderer;
import ru.somber.particlesystem.texture.ParticleAtlasTexture;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    private static IParticleManager particleManager;
    private static IParticleManager distortionParticleManager;
    private static ParticleAtlasTexture particleAtlasTexture;

    public ClientProxy() {

    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        particleAtlasTexture = new ParticleAtlasTexture(AnomalyMod.MOD_ID + ":" + "textures/particles");

        IParticleContainer particleContainer = new ListParticleContainer();
        IParticleRenderer particleRenderer = new GeometryShaderParticleRenderer();
        particleRenderer.setParticleTextureAtlas(particleAtlasTexture);
        particleManager = new SimpleParticleManager();
        particleManager.setParticleContainer(particleContainer);
        particleManager.setParticleRenderer(particleRenderer);


        IParticleContainer distortionParticleContainer = new ListParticleContainer();
        IParticleRenderer distortionParticleRenderer = new DistortionParticleRenderer();
        distortionParticleRenderer.setParticleTextureAtlas(particleAtlasTexture);
        distortionParticleManager = new SimpleParticleManager();
        distortionParticleManager.setParticleContainer(distortionParticleContainer);
        distortionParticleManager.setParticleRenderer(distortionParticleRenderer);

        ParticleAPI particleAPI = ParticleAPI.getInstance();
        particleAPI.addParticleManager(0, particleManager);
        particleAPI.addParticleManager(1, distortionParticleManager);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }


    public static IParticleManager getParticleManager() {
        return particleManager;
    }

    public static IParticleManager getDistortionParticleManager() {
        return distortionParticleManager;
    }

    public static ParticleAtlasTexture getParticleAtlasTexture() {
        return particleAtlasTexture;
    }

}
