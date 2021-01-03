package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.common.tileentity.SteamTileEntity;

public class AnomalySteamBlock extends AbstractAnomalyBlock {

    public AnomalySteamBlock() {
        super();
        this.setBlockName("anomaly_steam_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new SteamTileEntity();
    }

}
