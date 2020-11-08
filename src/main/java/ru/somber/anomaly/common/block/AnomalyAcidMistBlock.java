package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.client.tileentity.ClientAcidMistTileEntity;
import ru.somber.anomaly.server.tileentity.ServerAcidMistTileEntity;

public class AnomalyAcidMistBlock extends AbstractAnomalyBlock {

    public AnomalyAcidMistBlock() {
        super();
        this.setBlockName("anomaly_acidmist_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        if (world.isRemote) {
            return new ClientAcidMistTileEntity();
        } else {
            return new ServerAcidMistTileEntity();
        }
    }

}
