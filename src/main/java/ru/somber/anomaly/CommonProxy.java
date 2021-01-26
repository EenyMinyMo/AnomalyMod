package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import ru.somber.anomaly.common.block.*;
import ru.somber.anomaly.common.entity.EntityBolt;
import ru.somber.anomaly.common.item.*;
import ru.somber.anomaly.common.tileentity.*;

public class CommonProxy {

    public static AbstractBlockAnomaly anomalyAcidMistBlock;
    public static AbstractBlockAnomaly anomalyCarouselBlock;
    public static AbstractBlockAnomaly anomalyElectraBlock;
    public static AbstractBlockAnomaly anomalyFryBlock;
    public static AbstractBlockAnomaly anomalyFunnelBlock;
    public static AbstractBlockAnomaly anomalyKisselBlock;
    public static AbstractBlockAnomaly anomalySteamBlock;
    public static AbstractBlockAnomaly anomalyTrampolineBlock;
    public static ItemBolt itemBolt;


    public CommonProxy() {}

    public void preInit(FMLPreInitializationEvent event) {
        registerAnomaly();
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}

    private void registerAnomaly() {
        anomalyAcidMistBlock = new BlockAnomalyAcidMist();
        anomalyCarouselBlock = new BlockAnomalyCarousel();
        anomalyElectraBlock = new BlockAnomalyElectra();
        anomalyFryBlock = new BlockAnomalyFry();
        anomalyFunnelBlock = new BlockAnomalyFunnel();
        anomalyKisselBlock = new BlockAnomalyKissel();
        anomalySteamBlock = new BlockAnomalySteam();
        anomalyTrampolineBlock = new BlockAnomalyTrampoline();

        GameRegistry.registerBlock(anomalyAcidMistBlock, ItemAnomalyAcidMist.class, anomalyAcidMistBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyCarouselBlock, ItemAnomalyCarousel.class, anomalyCarouselBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyElectraBlock, ItemAnomalyElectra.class, anomalyElectraBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyFryBlock, ItemAnomalyFry.class, anomalyFryBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyFunnelBlock, ItemAnomalyFunnel.class, anomalyFunnelBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyKisselBlock, ItemAnomalyKissel.class, anomalyKisselBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalySteamBlock, ItemAnomalySteam.class, anomalySteamBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyTrampolineBlock, ItemAnomalyTrampoline.class, anomalyTrampolineBlock.getUnlocalizedName());

        GameRegistry.registerTileEntity(AcidMistTileEntity.class, "anomaly_acidmist_tileentity");
        GameRegistry.registerTileEntity(CarouselTileEntity.class, "anomaly_carousel_tileentity");
        GameRegistry.registerTileEntity(ElectraTileEntity.class, "anomaly_electra_tileentity");
        GameRegistry.registerTileEntity(FryTileEntity.class, "anomaly_fry_tileentity");
        GameRegistry.registerTileEntity(FunnelTileEntity.class, "anomaly_funnel_tileentity");
        GameRegistry.registerTileEntity(KisselTileEntity.class, "anomaly_kissel_tileentity");
        GameRegistry.registerTileEntity(SteamTileEntity.class, "anomaly_steam_tileentity");
        GameRegistry.registerTileEntity(TrampolineTileEntity.class, "anomaly_trampoline_tileentity");

        itemBolt = new ItemBolt();
        GameRegistry.registerItem(itemBolt, itemBolt.getUnlocalizedName());
        EntityRegistry.registerModEntity(EntityBolt.class, "staler_entity_bolt", EntityRegistry.findGlobalUniqueEntityId(), AnomalyMod.getInstance(), 64, 10, true);
    }

}
