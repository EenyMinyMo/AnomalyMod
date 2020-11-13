package ru.somber.anomaly;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AnomalyMod.MOD_ID, name = AnomalyMod.MOD_NAME, version = AnomalyMod.MOD_VERSION)
public class AnomalyMod {
    public static final String MOD_ID = "somber_anomaly";
    public static final String MOD_NAME = "Somber anomaly mod";
    public static final String MOD_VERSION = "0.0.0";
    public static final boolean IS_SERVER = FMLCommonHandler.instance().getSide().isServer();


    @SidedProxy(clientSide = "ru.somber.anomaly.ClientProxy",
            serverSide = "ru.somber.anomaly.ServerProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
