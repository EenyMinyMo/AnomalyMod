package ru.somber.anomaly.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.tileentity.AnomalyCarouselTileEntity;

public class AnomalyCarouselBlock extends AbstractAnomalyBlock {

    public AnomalyCarouselBlock() {
        super();
        this.setBlockName("anomaly_carousel_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new AnomalyCarouselTileEntity(world, metadata);
    }

}