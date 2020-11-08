package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.client.tileentity.ClientCarouselTileEntity;
import ru.somber.anomaly.server.tileentity.ServerCarouselTileEntity;

public class AnomalyCarouselBlock extends AbstractAnomalyBlock {

    public AnomalyCarouselBlock() {
        super();
        this.setBlockName("anomaly_carousel_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        if (world.isRemote) {
            return new ClientCarouselTileEntity();
        } else {
            return new ServerCarouselTileEntity();
        }
    }

}