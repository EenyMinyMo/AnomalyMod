package ru.somber.anomaly;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import ru.somber.anomaly.client.ParticleIcons;
import ru.somber.anomaly.client.render.DistortionParticleRenderer;
import ru.somber.anomaly.client.render.RenderEntityBolt;
import ru.somber.anomaly.common.entity.EntityBolt;
import ru.somber.particlesystem.ParticleAPI;
import ru.somber.particlesystem.container.IEmitterContainer;
import ru.somber.particlesystem.container.IParticleContainer;
import ru.somber.particlesystem.container.ListEmitterContainer;
import ru.somber.particlesystem.container.ListParticleContainer;
import ru.somber.particlesystem.manager.IParticleManager;
import ru.somber.particlesystem.manager.SimpleParticleManager;
import ru.somber.particlesystem.render.GeometryShaderParticleRenderer;
import ru.somber.particlesystem.render.IParticleRenderer;
import ru.somber.util.clientutil.textureatlas.AtlasTexture;

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

        RenderingRegistry.registerEntityRenderingHandler(EntityBolt.class, new RenderEntityBolt());
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
        AtlasTexture atlas = new AtlasTexture("textures/particles", 16, 4);

        registerParticleAtlasIcon(atlas);
//        atlas.stitchTextureAtlas(Minecraft.getMinecraft().getResourceManager());

        atlas.setFilteringMode(GL11.GL_LINEAR_MIPMAP_LINEAR, GL11.GL_LINEAR);
        atlas.setWrapMode(GL12.GL_CLAMP_TO_EDGE);

        return atlas;
    }

    /**
     * Регистрирует все иконки частиц в переданную текстуру-атлас.
     * Для иконок может понадобиться индивидуальная настройка иконки, поэтому регистрация проводится в одном месте.
     */
    private void registerParticleAtlasIcon(AtlasTexture atlasTexture) {
        atlasTexture.registerIcon(ParticleIcons.distortion0Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion3Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion4Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion17Icon);
        atlasTexture.registerIcon(ParticleIcons.distortion21Icon);

        atlasTexture.registerIcon(ParticleIcons.anomaly0Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly1Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly15Icon);
        atlasTexture.registerIcon(ParticleIcons.anomaly16Icon);

        atlasTexture.registerIcon(ParticleIcons.lightEnerg7Icon);

        atlasTexture.registerIcon(ParticleIcons.fireAnimFlame0Icon);

        atlasTexture.registerIcon(ParticleIcons.smoke0Icon);
        atlasTexture.registerIcon(ParticleIcons.smoke1Icon);
        atlasTexture.registerIcon(ParticleIcons.smoke2Icon);

        atlasTexture.registerIcon(ParticleIcons.dust0Icon);
        atlasTexture.registerIcon(ParticleIcons.dust1Icon);
        atlasTexture.registerIcon(ParticleIcons.dust2Icon);
        atlasTexture.registerIcon(ParticleIcons.dust3Icon);
        atlasTexture.registerIcon(ParticleIcons.dust4Icon);
        atlasTexture.registerIcon(ParticleIcons.dust5Icon);
        atlasTexture.registerIcon(ParticleIcons.dust6Icon);
        atlasTexture.registerIcon(ParticleIcons.dust7Icon);

        atlasTexture.registerIcon(ParticleIcons.trash3Icon);
        atlasTexture.registerIcon(ParticleIcons.trash4Icon);
        atlasTexture.registerIcon(ParticleIcons.trash5Icon);
        atlasTexture.registerIcon(ParticleIcons.trash6Icon);
        atlasTexture.registerIcon(ParticleIcons.trash7Icon);
        atlasTexture.registerIcon(ParticleIcons.trash8Icon);
        atlasTexture.registerIcon(ParticleIcons.trash9Icon);
        atlasTexture.registerIcon(ParticleIcons.trash10Icon);
        atlasTexture.registerIcon(ParticleIcons.trash11Icon);

        atlasTexture.registerIcon(ParticleIcons.otherBubble3Icon);
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
