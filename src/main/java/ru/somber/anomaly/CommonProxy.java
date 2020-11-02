package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import ru.somber.anomaly.block.*;
import ru.somber.anomaly.item.*;
import ru.somber.anomaly.tileentity.*;

public class CommonProxy {

    public static AbstractAnomalyBlock anomalyAcidMistBlock;
    public static AbstractAnomalyBlock anomalyAerationBlock;
    public static AbstractAnomalyBlock anomalyBurningFluffBlock;
    public static AbstractAnomalyBlock anomalyCarouselBlock;
    public static AbstractAnomalyBlock anomalyElectraBlock;
    public static AbstractAnomalyBlock anomalyFryBlock;
    public static AbstractAnomalyBlock anomalyFunnelBlock;
    public static AbstractAnomalyBlock anomalyKisselBlock;
    public static AbstractAnomalyBlock anomalySteamBlock;
    public static AbstractAnomalyBlock anomalyTrampolineBlock;
    public static AbstractAnomalyBlock debugBlock;


    public CommonProxy() {}

    public void preInit(FMLPreInitializationEvent event) {
        registerAnomalyBlock();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    private void registerAnomalyBlock() {
        anomalyAcidMistBlock = new AnomalyAcidMistBlock();
        GameRegistry.registerBlock(anomalyAcidMistBlock, AnomalyAcidMistItem.class, anomalyAcidMistBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyAcidMistTileEntity.class, "anomaly_acidmist_tileentity");

        anomalyAerationBlock = new AnomalyAerationBlock();
        GameRegistry.registerBlock(anomalyAerationBlock, AnomalyAerationItem.class, anomalyAerationBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyAerationTileEntity.class, "anomaly_aeration_tileentity");

        anomalyBurningFluffBlock = new AnomalyBurningFluffBlock();
        GameRegistry.registerBlock(anomalyBurningFluffBlock, AnomalyBurningFluffItem.class, anomalyBurningFluffBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyBurningFluffTileEntity.class, "anomaly_burningfluff_tileentity");

        anomalyCarouselBlock = new AnomalyCarouselBlock();
        GameRegistry.registerBlock(anomalyCarouselBlock, AnomalyCarouselItem.class, anomalyCarouselBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyCarouselTileEntity.class, "anomaly_carousel_tileentity");

        anomalyElectraBlock = new AnomalyElectraBlock();
        GameRegistry.registerBlock(anomalyElectraBlock, AnomalyElectraItem.class, anomalyElectraBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyElectraTileEntity.class, "anomaly_electra_tileentity");

        anomalyFryBlock = new AnomalyFryBlock();
        GameRegistry.registerBlock(anomalyFryBlock, AnomalyFryItem.class, anomalyFryBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyFryTileEntity.class, "anomaly_fry_tileentity");

        anomalyFunnelBlock = new AnomalyFunnelBlock();
        GameRegistry.registerBlock(anomalyFunnelBlock, AnomalyFunnelItem.class, anomalyFunnelBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyFunnelTileEntity.class, "anomaly_funnel_tileentity");

        anomalyKisselBlock = new AnomalyKisselBlock();
        GameRegistry.registerBlock(anomalyKisselBlock, AnomalyKisselItem.class, anomalyKisselBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyKisselTileEntity.class, "anomaly_kissel_tileentity");

        anomalySteamBlock = new AnomalySteamBlock();
        GameRegistry.registerBlock(anomalySteamBlock, AnomalySteamItem.class, anomalySteamBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalySteamTileEntity.class, "anomaly_steam_tileentity");

        anomalyTrampolineBlock = new AnomalyTrampolineBlock();
        GameRegistry.registerBlock(anomalyTrampolineBlock, AnomalyTrampolineItem.class, anomalyTrampolineBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyTrampolineTileEntity.class, "anomaly_trampoline_tileentity");

        debugBlock = new DebugParticleBlock();
        GameRegistry.registerBlock(debugBlock, debugBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(DebugParticleTileEntity.class, "debug_tileentity");
    }

}
