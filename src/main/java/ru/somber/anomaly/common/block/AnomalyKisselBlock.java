package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.common.tileentity.KisselTileEntity;

public class AnomalyKisselBlock extends AbstractAnomalyBlock {

    public AnomalyKisselBlock() {
        super();
        this.setBlockName("anomaly_kissel_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new KisselTileEntity();
    }

}
