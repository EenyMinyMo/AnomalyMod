package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import ru.somber.anomaly.client.tileentity.ClientDebugTileEntity;
import ru.somber.anomaly.common.block.*;
import ru.somber.anomaly.common.item.*;
import ru.somber.anomaly.common.tileentity.CarouselTileEntity;
import ru.somber.anomaly.common.tileentity.FryTileEntity;
import ru.somber.anomaly.common.tileentity.TrampolineTileEntity;

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
    public static AbstractAnomalyBlock anomalyDebugBlock;


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

        anomalyAerationBlock = new AnomalyAerationBlock();
        GameRegistry.registerBlock(anomalyAerationBlock, AnomalyAerationItem.class, anomalyAerationBlock.getUnlocalizedName());

        anomalyBurningFluffBlock = new AnomalyBurningFluffBlock();
        GameRegistry.registerBlock(anomalyBurningFluffBlock, AnomalyBurningFluffItem.class, anomalyBurningFluffBlock.getUnlocalizedName());

        anomalyCarouselBlock = new AnomalyCarouselBlock();
        GameRegistry.registerBlock(anomalyCarouselBlock, AnomalyCarouselItem.class, anomalyCarouselBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(CarouselTileEntity.class, "anomaly_carousel_tileentity");

        anomalyElectraBlock = new AnomalyElectraBlock();
        GameRegistry.registerBlock(anomalyElectraBlock, AnomalyElectraItem.class, anomalyElectraBlock.getUnlocalizedName());

        anomalyFryBlock = new AnomalyFryBlock();
        GameRegistry.registerBlock(anomalyFryBlock, AnomalyFryItem.class, anomalyFryBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(FryTileEntity.class, "anomaly_fry_tileentity");

        anomalyFunnelBlock = new AnomalyFunnelBlock();
        GameRegistry.registerBlock(anomalyFunnelBlock, AnomalyFunnelItem.class, anomalyFunnelBlock.getUnlocalizedName());

        anomalyKisselBlock = new AnomalyKisselBlock();
        GameRegistry.registerBlock(anomalyKisselBlock, AnomalyKisselItem.class, anomalyKisselBlock.getUnlocalizedName());

        anomalySteamBlock = new AnomalySteamBlock();
        GameRegistry.registerBlock(anomalySteamBlock, AnomalySteamItem.class, anomalySteamBlock.getUnlocalizedName());

        anomalyTrampolineBlock = new AnomalyTrampolineBlock();
        GameRegistry.registerBlock(anomalyTrampolineBlock, AnomalyTrampolineItem.class, anomalyTrampolineBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(TrampolineTileEntity.class, "anomaly_trampoline_tileentity");

        anomalyDebugBlock = new AnomalyDebugBlock();
        GameRegistry.registerBlock(anomalyDebugBlock, anomalyDebugBlock.getUnlocalizedName());
    }

}
