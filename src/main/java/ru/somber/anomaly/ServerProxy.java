package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.somber.anomaly.server.tileentity.AbstractServerTileEntity;
import ru.somber.anomaly.server.tileentity.ServerDebugTileEntity;
import ru.somber.anomaly.server.tileentity.ServerFryTileEntity;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy {

    public ServerProxy() {

    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        GameRegistry.registerTileEntity(ServerDebugTileEntity.class, "server_debug_tileentity");
        GameRegistry.registerTileEntity(ServerFryTileEntity.class, "server_fry_tileentity");
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
