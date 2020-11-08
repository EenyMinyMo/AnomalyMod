package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.client.tileentity.ClientDebugTileEntity;
import ru.somber.anomaly.server.tileentity.ServerDebugTileEntity;

public class AnomalyDebugBlock extends AbstractAnomalyBlock {

    public AnomalyDebugBlock() {
        super();
        this.setBlockName("anomaly_debug_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        if (world.isRemote) {
            return new ClientDebugTileEntity();
        } else {
            return new ServerDebugTileEntity();
        }
    }

}
