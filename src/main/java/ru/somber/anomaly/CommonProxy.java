package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import ru.somber.anomaly.client.emitter.AcidMistEmitter;
import ru.somber.anomaly.common.block.*;
import ru.somber.anomaly.common.item.*;
import ru.somber.anomaly.common.tileentity.*;

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
        anomalyAerationBlock = new AnomalyAerationBlock();
        anomalyBurningFluffBlock = new AnomalyBurningFluffBlock();
        anomalyCarouselBlock = new AnomalyCarouselBlock();
        anomalyElectraBlock = new AnomalyElectraBlock();
        anomalyFryBlock = new AnomalyFryBlock();
        anomalyFunnelBlock = new AnomalyFunnelBlock();
        anomalyKisselBlock = new AnomalyKisselBlock();
        anomalySteamBlock = new AnomalySteamBlock();
        anomalyTrampolineBlock = new AnomalyTrampolineBlock();


        GameRegistry.registerBlock(anomalyAcidMistBlock, AnomalyAcidMistItem.class, anomalyAcidMistBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyAerationBlock, AnomalyAerationItem.class, anomalyAerationBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyBurningFluffBlock, AnomalyBurningFluffItem.class, anomalyBurningFluffBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyCarouselBlock, AnomalyCarouselItem.class, anomalyCarouselBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyElectraBlock, AnomalyElectraItem.class, anomalyElectraBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyFryBlock, AnomalyFryItem.class, anomalyFryBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyFunnelBlock, AnomalyFunnelItem.class, anomalyFunnelBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyKisselBlock, AnomalyKisselItem.class, anomalyKisselBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalySteamBlock, AnomalySteamItem.class, anomalySteamBlock.getUnlocalizedName());
        GameRegistry.registerBlock(anomalyTrampolineBlock, AnomalyTrampolineItem.class, anomalyTrampolineBlock.getUnlocalizedName());


        GameRegistry.registerTileEntity(AcidMistTileEntity.class, "anomaly_acidmist_tileentity");
        GameRegistry.registerTileEntity(CarouselTileEntity.class, "anomaly_carousel_tileentity");
        GameRegistry.registerTileEntity(ElectraTileEntity.class, "anomaly_electra_tileentity");
        GameRegistry.registerTileEntity(FryTileEntity.class, "anomaly_fry_tileentity");
        GameRegistry.registerTileEntity(FunnelTileEntity.class, "anomaly_funnel_tileentity");
        GameRegistry.registerTileEntity(KisselTileEntity.class, "anomaly_kissel_tileentity");
        GameRegistry.registerTileEntity(SteamTileEntity.class, "anomaly_steam_tileentity");
        GameRegistry.registerTileEntity(TrampolineTileEntity.class, "anomaly_trampoline_tileentity");

    }

}
