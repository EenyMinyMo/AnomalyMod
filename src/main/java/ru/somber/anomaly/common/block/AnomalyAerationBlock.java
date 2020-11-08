package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.client.tileentity.ClientAerationTileEntity;
import ru.somber.anomaly.server.tileentity.ServerAerationTileEntity;

public class AnomalyAerationBlock extends AbstractAnomalyBlock {

    public AnomalyAerationBlock() {
        super();
        this.setBlockName("anomaly_aeration_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        if (world.isRemote) {
            return new ClientAerationTileEntity();
        } else {
            return new ServerAerationTileEntity();
        }
    }

}
