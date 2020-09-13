package ru.somber.anomaly.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.tileentity.AnomalyAcidMistTileEntity;

public class AnomalyAcidMistBlock extends AbstractAnomalyBlock {

    public AnomalyAcidMistBlock() {
        super();
        this.setBlockName("anomaly_acid_mist_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new AnomalyAcidMistTileEntity(world, metadata);
    }

}
