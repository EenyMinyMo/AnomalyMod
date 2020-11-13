package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy {

    public ServerProxy() {

    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

//        GameRegistry.registerTileEntity(ServerDebugTileEntity.class, "server_anomaly_debug_tileentity");
//        GameRegistry.registerTileEntity(ServerAcidMistTileEntity.class, "server_anomaly_acidmist_tileentity");
//        GameRegistry.registerTileEntity(ServerAerationTileEntity.class, "server_anomaly_aeration_tileentity");
//        GameRegistry.registerTileEntity(ServerBurningFluffTileEntity.class, "server_anomaly_burningfluff_tileentity");
//        GameRegistry.registerTileEntity(ServerCarouselTileEntity.class, "server_anomaly_carousel_tileentity");
//        GameRegistry.registerTileEntity(ServerElectraTileEntity.class, "server_anomaly_electra_tileentity");
//        GameRegistry.registerTileEntity(ServerFryTileEntity.class, "server_anomaly_fry_tileentity");
//        GameRegistry.registerTileEntity(ServerFunnelTileEntity.class, "server_anomaly_funnel_tileentity");
//        GameRegistry.registerTileEntity(ServerKisselTileEntity.class, "server_anomaly_kissel_tileentity");
//        GameRegistry.registerTileEntity(ServerSteamTileEntity.class, "server_anomaly_steam_tileentity");
//        GameRegistry.registerTileEntity(ServerTrampolineTileEntity.class, "server_anomaly_trampoline_tileentity");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

}
