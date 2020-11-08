package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import ru.somber.anomaly.client.render.DistortionParticleRenderer;
import ru.somber.anomaly.client.tileentity.ClientDebugTileEntity;
import ru.somber.anomaly.client.tileentity.ClientFryTileEntity;
import ru.somber.clientutil.textureatlas.AtlasTexture;
import ru.somber.particlesystem.ParticleAPI;
import ru.somber.particlesystem.container.IEmitterContainer;
import ru.somber.particlesystem.container.IParticleContainer;
import ru.somber.particlesystem.container.ListEmitterContainer;
import ru.somber.particlesystem.container.ListParticleContainer;
import ru.somber.particlesystem.manager.IParticleManager;
import ru.somber.particlesystem.manager.SimpleParticleManager;
import ru.somber.particlesystem.render.GeometryShaderParticleRenderer;
import ru.somber.particlesystem.render.IParticleRenderer;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    /** Текстура-атлас с текстурами используемых частиц. */
    private static AtlasTexture particleAtlasTexture;
    /** Менеджер частиц обычных частиц. */
    private static IParticleManager particleManager;
    /** Менеджер частиц distortion частиц. */
    private static IParticleManager distortionParticleManager;
    /** Контейнер эмиттеров частиц. */
    private static IEmitterContainer emitterContainer;


    public ClientProxy() {}


    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        GameRegistry.registerTileEntity(ClientDebugTileEntity.class, "client_debug_tileentity");
        GameRegistry.registerTileEntity(ClientFryTileEntity.class, "client_fry_tileentity");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        particleAtlasTexture = createParticleAtlasTexture();
        particleManager = createParticleManager();
        distortionParticleManager = createDistortionParticleManager();
        emitterContainer = createEmitterContainer();

        ParticleAPI particleAPI = ParticleAPI.getInstance();
        particleAPI.addParticleManager(0, distortionParticleManager);
        particleAPI.addParticleManager(1, particleManager);
        particleAPI.addEmitterContainer(emitterContainer);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }


    /**
     * Создает и заполняет текстуру-атлас с текстурами частиц.
     */
    private AtlasTexture createParticleAtlasTexture() {
        AtlasTexture atlas = new AtlasTexture("textures/particles");

        registerParticleAtlasIcon(atlas);
        atlas.stitchTextureAtlas(Minecraft.getMinecraft().getResourceManager());

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, atlas.getGlTextureId());
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

        return atlas;
    }

    /**
     * Регистрирует все иконки частиц в переданную текстуру-атлас.
     * Для иконок может понадобиться индивидуальная настройка иконки, поэтому регистрация проводится в одном месте.
     */
    private void registerParticleAtlasIcon(AtlasTexture atlasTexture) {
        atlasTexture.registerIcon(ParticleIcons.distortion0Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion1Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion2Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion3Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion4Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion5Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion6Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion7Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion8Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion9Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion10Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion11Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion12Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion13Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion14Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion15Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion16Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion17Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion18Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion19Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion20Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion21Icon);

        atlasTexture.registerIcon(ParticleIcons.distortionGlass0Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass1Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass2Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass3Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass4Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass5Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass6Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass7Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass8Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass9Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass10Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionGlass11Icon);

        atlasTexture.registerIcon(ParticleIcons.distortionLens0Icon);
        atlasTexture.registerIcon(ParticleIcons.distortionLens1Icon);


        atlasTexture.registerIcon(ParticleIcons.anomaly0Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly1Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly2Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly3Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly4Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly5Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly6Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly7Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly8Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly9Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly10Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly11Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly12Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly13Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly14Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly15Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly16Icon);

        atlasTexture.registerIcon(ParticleIcons.anomalyAnim0Icon);
        atlasTexture.registerIcon(ParticleIcons.anomalyAnim1Icon);
        atlasTexture.registerIcon(ParticleIcons.anomalyAnim2Icon);
        atlasTexture.registerIcon(ParticleIcons.anomalyAnim3Icon);
        atlasTexture.registerIcon(ParticleIcons.anomalyAnim4Icon);
        atlasTexture.registerIcon(ParticleIcons.anomalyAnim5Icon);

        atlasTexture.registerIcon(ParticleIcons.anomalyEnerg0Icon);
        atlasTexture.registerIcon(ParticleIcons.anomalyEnerg1Icon);
        atlasTexture.registerIcon(ParticleIcons.anomalyEnerg2Icon);
        atlasTexture.registerIcon(ParticleIcons.anomalyEnerg3Icon);
        atlasTexture.registerIcon(ParticleIcons.anomalyEnerg4Icon);


        atlasTexture.registerIcon(ParticleIcons.light0Icon);
        atlasTexture.registerIcon(ParticleIcons.light1Icon);
        atlasTexture.registerIcon(ParticleIcons.light2Icon);
        atlasTexture.registerIcon(ParticleIcons.light3Icon);

        atlasTexture.registerIcon(ParticleIcons.lightAnimEnerg0Icon);
        atlasTexture.registerIcon(ParticleIcons.lightAnimEnerg1Icon);

        atlasTexture.registerIcon(ParticleIcons.lightEnerg0Icon);
        atlasTexture.registerIcon(ParticleIcons.lightEnerg1Icon);
        atlasTexture.registerIcon(ParticleIcons.lightEnerg2Icon);
        atlasTexture.registerIcon(ParticleIcons.lightEnerg3Icon);
        atlasTexture.registerIcon(ParticleIcons.lightEnerg4Icon);
        atlasTexture.registerIcon(ParticleIcons.lightEnerg5Icon);
        atlasTexture.registerIcon(ParticleIcons.lightEnerg6Icon);
        atlasTexture.registerIcon(ParticleIcons.lightEnerg7Icon);
        atlasTexture.registerIcon(ParticleIcons.lightEnerg8Icon);


        atlasTexture.registerIcon(ParticleIcons.animfireExplosionAnim0Icon);
        atlasTexture.registerIcon(ParticleIcons.animfireFireAnimEnerg0Icon);


        atlasTexture.registerIcon(ParticleIcons.mercury0Icon);
        atlasTexture.registerIcon(ParticleIcons.mercury1Icon);
        atlasTexture.registerIcon(ParticleIcons.mercury2Icon);
        atlasTexture.registerIcon(ParticleIcons.mercury3Icon);


        atlasTexture.registerIcon(ParticleIcons.smoke0Icon);
        atlasTexture.registerIcon(ParticleIcons.smoke1Icon);
        atlasTexture.registerIcon(ParticleIcons.smoke2Icon);
        atlasTexture.registerIcon(ParticleIcons.smoke3Icon);
        atlasTexture.registerIcon(ParticleIcons.smoke4Icon);
        atlasTexture.registerIcon(ParticleIcons.smoke5Icon);
        atlasTexture.registerIcon(ParticleIcons.smoke6Icon);

        atlasTexture.registerIcon(ParticleIcons.smokeAnim0Icon);
        atlasTexture.registerIcon(ParticleIcons.smokeAnim1Icon);
        atlasTexture.registerIcon(ParticleIcons.smokeAnim2Icon);

        atlasTexture.registerIcon(ParticleIcons.smokeAnimEnerg0Icon);


        atlasTexture.registerIcon(ParticleIcons.trash0Icon);
        atlasTexture.registerIcon(ParticleIcons.trash1Icon);
        atlasTexture.registerIcon(ParticleIcons.trash2Icon);
        atlasTexture.registerIcon(ParticleIcons.trash3Icon);

        atlasTexture.registerIcon(ParticleIcons.trashAnim0Icon);
        atlasTexture.registerIcon(ParticleIcons.trashAnim1Icon);
        atlasTexture.registerIcon(ParticleIcons.trashAnim2Icon);
        atlasTexture.registerIcon(ParticleIcons.trashAnim3Icon);


        atlasTexture.registerIcon(ParticleIcons.otherActPuh0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherBlood0Icon);
        atlasTexture.registerIcon(ParticleIcons.otherBloodEnerg0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherBubble0Icon);
        atlasTexture.registerIcon(ParticleIcons.otherBubble1Icon);
        atlasTexture.registerIcon(ParticleIcons.otherBubble2Icon);
        atlasTexture.registerIcon(ParticleIcons.otherBubble3Icon);
        atlasTexture.registerIcon(ParticleIcons.otherBubble4Icon);
        atlasTexture.registerIcon(ParticleIcons.otherBubble5Icon);
        atlasTexture.registerIcon(ParticleIcons.otherBubble6Icon);
        atlasTexture.registerIcon(ParticleIcons.otherBubble7Icon);

        atlasTexture.registerIcon(ParticleIcons.otherByaka0Icon);
        atlasTexture.registerIcon(ParticleIcons.otherByaka1Icon);

        atlasTexture.registerIcon(ParticleIcons.otherCresty0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherFlash0Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash1Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash2Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash3Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash4Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash5Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash6Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash7Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash8Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash9Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash10Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash11Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash12Icon);
        atlasTexture.registerIcon(ParticleIcons.otherFlash13Icon);

        atlasTexture.registerIcon(ParticleIcons.otherRingfire0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherRocket0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherSparksAnim0Icon);
        atlasTexture.registerIcon(ParticleIcons.otherSparksAnim1Icon);

        atlasTexture.registerIcon(ParticleIcons.otherSpiral0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherSplash0Icon);
        atlasTexture.registerIcon(ParticleIcons.otherSplash1Icon);
        atlasTexture.registerIcon(ParticleIcons.otherSplash2Icon);

        atlasTexture.registerIcon(ParticleIcons.otherStuden0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherSynus0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherTrasser0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherShells0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherWaterWave0Icon);

        atlasTexture.registerIcon(ParticleIcons.otherSkinSplashAnim0Icon);
    }

    /**
     * Создает и подготавливает менеджер частиц для обычных частиц.
     */
    private IParticleManager createParticleManager() {
        IParticleContainer particleContainer = new ListParticleContainer();
        IParticleRenderer particleRenderer = new GeometryShaderParticleRenderer();
        particleRenderer.setAtlasTexture(particleAtlasTexture);

        IParticleManager manager = new SimpleParticleManager();
        manager.setParticleContainer(particleContainer);
        manager.setParticleRenderer(particleRenderer);

        return manager;
    }

    /**
     * Создает и подготавливает менеджер частиц для distortion (размытие) частиц.
     */
    private IParticleManager createDistortionParticleManager() {
        IParticleContainer distortionParticleContainer = new ListParticleContainer();
        IParticleRenderer distortionParticleRenderer = new DistortionParticleRenderer();
        distortionParticleRenderer.setAtlasTexture(particleAtlasTexture);

        IParticleManager manager = new SimpleParticleManager();
        manager.setParticleContainer(distortionParticleContainer);
        manager.setParticleRenderer(distortionParticleRenderer);

        return manager;
    }

    /**
     * Создает контейнер для эмиттеров частиц.
     */
    private IEmitterContainer createEmitterContainer() {
        IEmitterContainer container = new ListEmitterContainer();
        return container;
    }


    /**
     * Возвращает менеджер частиц обычных цветных частиц
     * (в контейнер этого менеджера добавлять обычные частицы).
     */
    public static IParticleManager getParticleManager() {
        return particleManager;
    }

    /**
     * Возвращает менеджер частиц обычных distortion (размытие) частиц
     * (в контейнер этого менеджера добавлять distortion частицы).
     */
    public static IParticleManager getDistortionParticleManager() {
        return distortionParticleManager;
    }

    /**
     * Возвращает контейнер эмиттеров частиц (сюда добавлять эмиттеры частиц).
     */
    public static IEmitterContainer getEmitterContainer() {
        return emitterContainer;
    }

    /**
     * Вовзвращает текстуру-атлас с текстурами частиц.
     */
    public static AtlasTexture getParticleAtlasTexture() {
        return particleAtlasTexture;
    }

}
