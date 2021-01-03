package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AnomalyAerationBlock extends AbstractAnomalyBlock {

    public AnomalyAerationBlock() {
        super();
        this.setBlockName("anomaly_aeration_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return null;
    }

}
