package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import ru.somber.anomaly.render.DistortionParticleRenderer;
import ru.somber.particlesystem.ParticleAPI;
import ru.somber.particlesystem.container.IEmitterContainer;
import ru.somber.particlesystem.container.IParticleContainer;
import ru.somber.particlesystem.container.ListEmitterContainer;
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
    private static IEmitterContainer emitterContainer;

    private static ParticleAtlasTexture particleAtlasTexture;


    public ClientProxy() {}


    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        particleAtlasTexture = new ParticleAtlasTexture("textures/particles");
        prepareParticleAtlasIcon();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, particleAtlasTexture.getGlTextureId());
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);


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
        particleAPI.addParticleManager(0, distortionParticleManager);
        particleAPI.addParticleManager(1, particleManager);

        emitterContainer = new ListEmitterContainer();
        particleAPI.addEmitterContainer(emitterContainer);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }


    private void prepareParticleAtlasIcon() {
        particleAtlasTexture.registerIcon(ParticleIcons.distortion0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion3Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion4Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion5Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion6Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion7Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion8Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion9Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion10Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion11Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion12Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion13Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion14Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion15Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion16Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion17Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion18Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion19Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion20Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortion21Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass3Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass4Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass5Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass6Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass7Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass8Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass9Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass10Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionGlass11Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.distortionLens0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.distortionLens1Icon);


        particleAtlasTexture.registerIcon(ParticleIcons.anomaly0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly3Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly4Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly5Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly6Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly7Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly8Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly9Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly10Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly11Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly12Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly13Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly14Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly15Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomaly16Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.anomalyAnim0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomalyAnim1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomalyAnim2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomalyAnim3Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomalyAnim4Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomalyAnim5Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.anomalyEnerg0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomalyEnerg1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomalyEnerg2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomalyEnerg3Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.anomalyEnerg4Icon);


        particleAtlasTexture.registerIcon(ParticleIcons.light0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.light1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.light2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.light3Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.lightAnimEnerg0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.lightAnimEnerg1Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.lightEnerg0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.lightEnerg1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.lightEnerg2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.lightEnerg3Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.lightEnerg4Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.lightEnerg5Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.lightEnerg6Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.lightEnerg7Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.lightEnerg8Icon);


        particleAtlasTexture.registerIcon(ParticleIcons.animfireExplosionAnim0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.animfireFireAnimEnerg0Icon);


        particleAtlasTexture.registerIcon(ParticleIcons.mercury0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.mercury1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.mercury2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.mercury3Icon);


        particleAtlasTexture.registerIcon(ParticleIcons.smoke0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.smoke1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.smoke2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.smoke3Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.smoke4Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.smoke5Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.smoke6Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.smokeAnim0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.smokeAnim1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.smokeAnim2Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.smokeAnimEnerg0Icon);


        particleAtlasTexture.registerIcon(ParticleIcons.trash0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.trash1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.trash2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.trash3Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.trashAnim0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.trashAnim1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.trashAnim2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.trashAnim3Icon);



        particleAtlasTexture.registerIcon(ParticleIcons.otherBlood0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherBloodEnerg0Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherBubble0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherBubble1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherBubble2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherBubble3Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherBubble4Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherBubble5Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherBubble6Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherBubble7Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherByaka0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherByaka1Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherCresty0Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash2Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash3Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash4Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash5Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash6Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash7Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash8Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash9Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash10Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash11Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash12Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherFlash13Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherRingfire0Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherRocket0Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherSparksAnim0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherSparksAnim1Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherSpiral0Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherSplash0Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherSplash1Icon);
        particleAtlasTexture.registerIcon(ParticleIcons.otherSplash2Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherStuden0Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherSynus0Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherTrasser0Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherShells0Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherWaterWave0Icon);

        particleAtlasTexture.registerIcon(ParticleIcons.otherSkinSplashAnim0Icon);



        particleAtlasTexture.setAnisotropicFiltering(16);

        particleAtlasTexture.loadTextureAtlas(Minecraft.getMinecraft().getResourceManager());
    }


    public static IParticleManager getParticleManager() {
        return particleManager;
    }

    public static IParticleManager getDistortionParticleManager() {
        return distortionParticleManager;
    }

    public static IEmitterContainer getEmitterContainer() {
        return emitterContainer;
    }

    public static ParticleAtlasTexture getParticleAtlasTexture() {
        return particleAtlasTexture;
    }

}
