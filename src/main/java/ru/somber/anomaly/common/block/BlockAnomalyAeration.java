package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAnomalyAeration extends AbstractBlockAnomaly {

    public BlockAnomalyAeration() {
        super();
        this.setBlockName("anomaly_aeration_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return null;
    }

}
