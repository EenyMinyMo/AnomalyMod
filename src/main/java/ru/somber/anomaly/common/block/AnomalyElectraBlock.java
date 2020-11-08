package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.tileentity.AnomalyElectraTileEntity;

public class AnomalyElectraBlock extends AbstractAnomalyBlock {

    public AnomalyElectraBlock() {
        super();
        this.setBlockName("anomaly_electra_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new AnomalyElectraTileEntity(world, metadata);
    }

}
