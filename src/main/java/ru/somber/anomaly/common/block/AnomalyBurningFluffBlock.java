package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.tileentity.AnomalyBurningFluffTileEntity;

public class AnomalyBurningFluffBlock extends AbstractAnomalyBlock {

    public AnomalyBurningFluffBlock() {
        super();
        this.setBlockName("anomaly_burningfluff_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new AnomalyBurningFluffTileEntity(world, metadata);
    }

}
