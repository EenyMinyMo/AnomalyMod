package ru.somber.anomaly;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class AnomalyMod {
    public static final String MOD_ID = "";
    public static final String MOD_NAME = "";
    public static final String MOD_VERSION = "";

    @SidedProxy(clientSide = "ru.somber.anomaly.ClientProxy", serverSide = "ru.somber.anomaly.ServerProxy")
    public CommonProxy proxy;

    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
