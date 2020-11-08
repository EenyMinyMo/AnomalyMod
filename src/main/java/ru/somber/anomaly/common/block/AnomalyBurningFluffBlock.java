package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.client.tileentity.ClientBurningFluffTileEntity;
import ru.somber.anomaly.server.tileentity.ServerBurningFluffTileEntity;

public class AnomalyBurningFluffBlock extends AbstractAnomalyBlock {

    public AnomalyBurningFluffBlock() {
        super();
        this.setBlockName("anomaly_burningfluff_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        if (world.isRemote) {
            return new ClientBurningFluffTileEntity();
        } else {
            return new ServerBurningFluffTileEntity();
        }
    }

}
