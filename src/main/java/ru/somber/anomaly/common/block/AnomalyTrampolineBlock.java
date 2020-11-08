package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.tileentity.AnomalyTrampolineTileEntity;

public class AnomalyTrampolineBlock extends AbstractAnomalyBlock {

    public AnomalyTrampolineBlock() {
        super();
        this.setBlockName("anomaly_trampoline_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new AnomalyTrampolineTileEntity(world, metadata);
    }

}
