package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.client.tileentity.ClientFunnelTileEntity;
import ru.somber.anomaly.common.tileentity.FunnelTileEntity;
import ru.somber.anomaly.server.tileentity.ServerFunnelTileEntity;

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