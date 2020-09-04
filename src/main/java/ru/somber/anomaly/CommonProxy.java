package ru.somber.anomaly;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import ru.somber.anomaly.block.AnomalyKiselBlock;
import ru.somber.anomaly.tileentity.KiselTileEntity;

public class CommonProxy {

    public static Block anomalyKiselBlock;


    public CommonProxy() {

    }

    public void preInit(FMLPreInitializationEvent event) {
        anomalyKiselBlock = new AnomalyKiselBlock();
        GameRegistry.registerBlock(anomalyKiselBlock, anomalyKiselBlock.getUnlocalizedName());
        GameRegistry.registerTileEntity(KiselTileEntity.class, "tessellator_particle_tile_entity");
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
