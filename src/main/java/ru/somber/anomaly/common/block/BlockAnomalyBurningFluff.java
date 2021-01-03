package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockAnomalyBurningFluff extends AbstractBlockAnomaly {

    public BlockAnomalyBurningFluff() {
        super();
        this.setBlockName("anomaly_burningfluff_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return null;
    }

}
