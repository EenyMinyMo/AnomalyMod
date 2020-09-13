package ru.somber.anomaly.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.tileentity.AnomalyFryTileEntity;

public class AnomalyFryBlock extends AbstractAnomalyBlock {

    public AnomalyFryBlock() {
        super();
        this.setBlockName("anomaly_fry_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new AnomalyFryTileEntity(world, metadata);
    }

}
