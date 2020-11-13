package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.common.tileentity.CarouselTileEntity;

public class AnomalyCarouselBlock extends AbstractAnomalyBlock {

    public AnomalyCarouselBlock() {
        super();
        this.setBlockName("anomaly_carousel_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new CarouselTileEntity();
    }

}