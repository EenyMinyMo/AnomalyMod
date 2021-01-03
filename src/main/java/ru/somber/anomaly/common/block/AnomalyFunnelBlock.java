package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.common.tileentity.FunnelTileEntity;

public class AnomalyFunnelBlock extends AbstractAnomalyBlock {

    public AnomalyFunnelBlock() {
        super();
        this.setBlockName("anomaly_funnel_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new FunnelTileEntity();
    }

}