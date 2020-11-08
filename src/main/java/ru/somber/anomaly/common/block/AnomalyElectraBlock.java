package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.client.tileentity.ClientElectraTileEntity;
import ru.somber.anomaly.server.tileentity.ServerElectraTileEntity;

public class AnomalyElectraBlock extends AbstractAnomalyBlock {

    public AnomalyElectraBlock() {
        super();
        this.setBlockName("anomaly_electra_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        if (world.isRemote) {
            return new ClientElectraTileEntity();
        } else {
            return new ServerElectraTileEntity();
        }
    }

}
