package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.common.tileentity.FryTileEntity;

public class AnomalyFryBlock extends AbstractAnomalyBlock {

    public AnomalyFryBlock() {
        super();
        this.setBlockName("anomaly_fry_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new FryTileEntity();
    }

}
