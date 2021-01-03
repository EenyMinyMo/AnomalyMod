package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.common.tileentity.ElectraTileEntity;

public class BlockAnomalyElectra extends AbstractBlockAnomaly {

    public BlockAnomalyElectra() {
        super();
        this.setBlockName("anomaly_electra_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new ElectraTileEntity();
    }

}
