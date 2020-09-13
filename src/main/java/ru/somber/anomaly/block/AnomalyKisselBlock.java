package ru.somber.anomaly.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.tileentity.AnomalyKisselTileEntity;

public class AnomalyKisselBlock extends AbstractAnomalyBlock {

    public AnomalyKisselBlock() {
        super();
        this.setBlockName("anomaly_kissel_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new AnomalyKisselTileEntity(world, metadata);
    }

}
