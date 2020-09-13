package ru.somber.anomaly.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.tileentity.AnomalyAerationTileEntity;

public class AnomalyAerationBlock extends AbstractAnomalyBlock {

    public AnomalyAerationBlock() {
        super();
        this.setBlockName("anomaly_aeration_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new AnomalyAerationTileEntity(world, metadata);
    }

}
