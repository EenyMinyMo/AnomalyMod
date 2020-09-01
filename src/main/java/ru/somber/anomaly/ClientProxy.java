package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
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
import ru.somber.particlesystem.texture.ParticleAtlasAnimatedIcon;
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
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
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
        particleAPI.addParticleManager(0, particleManager);
        particleAPI.addParticleManager(1, distortionParticleManager);

        emitterContainer = new ListEmitterContainer();
        particleAPI.addEmitterContainer(emitterContainer);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }


    private void prepareParticleAtlasIcon() {
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion3Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion4Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion5Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion6Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion7Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion8Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion9Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion10Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion11Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion12Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion13Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion14Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion15Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion16Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion17Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion18Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion19Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion20Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortion21Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass3Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass4Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass5Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass6Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass7Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass8Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass9Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass10Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionGlass11Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.distortionLens0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.distortionLens1Icon);


        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly3Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly4Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly5Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly6Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly7Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly8Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly9Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly10Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly11Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly12Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly13Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly14Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly15Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomaly16Icon);

        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.anomalyAnim0Icon, 4, 2, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.anomalyAnim1Icon, 4, 2, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.anomalyAnim2Icon, 4, 2, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.anomalyAnim3Icon, 4, 2, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.anomalyAnim4Icon, 4, 2, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.anomalyAnim5Icon, 4, 2, true));

        particleAtlasTexture.registerIcon(ParticleIconNames.anomalyEnerg0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomalyEnerg1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomalyEnerg2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomalyEnerg3Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.anomalyEnerg4Icon);


        particleAtlasTexture.registerIcon(ParticleIconNames.light0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.light1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.light2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.light3Icon);

        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.lightAnimEnerg0Icon, 2, 2, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.lightAnimEnerg1Icon, 2, 2, true));

        particleAtlasTexture.registerIcon(ParticleIconNames.lightEnerg0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.lightEnerg1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.lightEnerg2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.lightEnerg3Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.lightEnerg4Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.lightEnerg5Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.lightEnerg6Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.lightEnerg7Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.lightEnerg8Icon);


        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.animfireExplosionAnim0Icon, 8, 7, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.animfireFireAnimEnerg0Icon, 11, 7, true));


        particleAtlasTexture.registerIcon(ParticleIconNames.mercury0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.mercury1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.mercury2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.mercury3Icon);


        particleAtlasTexture.registerIcon(ParticleIconNames.smoke0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.smoke1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.smoke2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.smoke3Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.smoke4Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.smoke5Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.smoke6Icon);

        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.smokeAnim0Icon, 8, 8, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.smokeAnim1Icon, 8, 7, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.smokeAnim2Icon, 8, 8, true));

        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.smokeAnimEnerg0Icon, 10, 10, true));


        particleAtlasTexture.registerIcon(ParticleIconNames.trash0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.trash1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.trash2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.trash3Icon);

        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.trashAnim0Icon, 4, 4, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.trashAnim1Icon, 1, 2, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.trashAnim2Icon, 2, 2, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.trashAnim3Icon, 2, 2, true));



        particleAtlasTexture.registerIcon(ParticleIconNames.otherBlood0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherBloodEnerg0Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.otherBubble0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherBubble1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherBubble2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherBubble3Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherBubble4Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherBubble5Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherBubble6Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherBubble7Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.otherByaka0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherByaka1Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.otherCresty0Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash2Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash3Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash4Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash5Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash6Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash7Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash8Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash9Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash10Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash11Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash12Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherFlash13Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.otherRingfire0Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.otherRocket0Icon);

        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.otherSparksAnim0Icon, 4, 2, true));
        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.otherSparksAnim1Icon, 4, 2, true));

        particleAtlasTexture.registerIcon(ParticleIconNames.otherSpiral0Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.otherSplash0Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherSplash1Icon);
        particleAtlasTexture.registerIcon(ParticleIconNames.otherSplash2Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.otherStuden0Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.otherSynus0Icon);

        particleAtlasTexture.registerIcon(ParticleIconNames.otherTrasser0Icon);

        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.otherShells0Icon, 8, 4, true));

        particleAtlasTexture.registerIcon(ParticleIconNames.otherWaterWave0Icon);

        particleAtlasTexture.registerIcon(new ParticleAtlasAnimatedIcon(ParticleIconNames.otherSkinSplashAnim0Icon, 4, 4, true));



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
