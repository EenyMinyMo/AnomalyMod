package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import ru.somber.anomaly.block.*;
import ru.somber.anomaly.tileentity.*;

public class CommonProxy {

    public static Block anomalyAcidMistBlock;
    public static Block anomalyAerationBlock;
    public static Block anomalyBurningFluffBlock;
    public static Block anomalyCarouselBlock;
    public static Block anomalyElectraBlock;
    public static Block anomalyFryBlock;
    public static Block anomalyFunnelBlock;
    public static Block anomalyKisselBlock;
    public static Block anomalySteamBlock;
    public static Block anomalyTrampolineBlock;


    public CommonProxy() {}

    public void preInit(FMLPreInitializationEvent event) {
        anomalyAcidMistBlock = new AnomalyAcidMistBlock();
        GameRegistry.registerBlock(anomalyAcidMistBlock, anomalyAcidMistBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyAcidMistTileEntity.class, "anomaly_acidmist_tileentity");

        anomalyAerationBlock = new AnomalyAerationBlock();
        GameRegistry.registerBlock(anomalyAerationBlock, anomalyAerationBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyAerationTileEntity.class, "anomaly_aeration_tileentity");

        anomalyBurningFluffBlock = new AnomalyBurningFluffBlock();
        GameRegistry.registerBlock(anomalyBurningFluffBlock, anomalyBurningFluffBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyBurningFluffTileEntity.class, "anomaly_burningfluff_tileentity");

        anomalyCarouselBlock = new AnomalyCarouselBlock();
        GameRegistry.registerBlock(anomalyCarouselBlock, anomalyCarouselBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyCarouselTileEntity.class, "anomaly_carousel_tileentity");

        anomalyElectraBlock = new AnomalyElectraBlock();
        GameRegistry.registerBlock(anomalyElectraBlock, anomalyElectraBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyElectraTileEntity.class, "anomaly_electra_tileentity");

        anomalyFryBlock = new AnomalyFryBlock();
        GameRegistry.registerBlock(anomalyFryBlock, anomalyFryBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyFryTileEntity.class, "anomaly_fry_tileentity");

        anomalyFunnelBlock = new AnomalyFunnelBlock();
        GameRegistry.registerBlock(anomalyFunnelBlock, anomalyFunnelBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyFunnelTileEntity.class, "anomaly_funnel_tileentity");

        anomalyKisselBlock = new AnomalyKisselBlock();
        GameRegistry.registerBlock(anomalyKisselBlock, anomalyKisselBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyKisselTileEntity.class, "anomaly_kissel_tileentity");

        anomalySteamBlock = new AnomalySteamBlock();
        GameRegistry.registerBlock(anomalySteamBlock, anomalySteamBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalySteamTileEntity.class, "anomaly_steam_tileentity");

        anomalyTrampolineBlock = new AnomalyTrampolineBlock();
        GameRegistry.registerBlock(anomalyTrampolineBlock, anomalyTrampolineBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(AnomalyTrampolineTileEntity.class, "anomaly_trampoline_tileentity");
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
