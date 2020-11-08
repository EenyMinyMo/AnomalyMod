package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.client.tileentity.ClientTrampolineTileEntity;
import ru.somber.anomaly.server.tileentity.ServerTrampolineTileEntity;

public class AnomalyTrampolineBlock extends AbstractAnomalyBlock {

    public AnomalyTrampolineBlock() {
        super();
        this.setBlockName("anomaly_trampoline_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        if (world.isRemote) {
            return new ClientTrampolineTileEntity();
        } else {
            return new ServerTrampolineTileEntity();
        }
    }

}
