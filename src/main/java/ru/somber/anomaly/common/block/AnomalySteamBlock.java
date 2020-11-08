package ru.somber.anomaly.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.client.tileentity.ClientSteamTileEntity;
import ru.somber.anomaly.server.tileentity.ServerSteamTileEntity;

public class AnomalySteamBlock extends AbstractAnomalyBlock {

    public AnomalySteamBlock() {
        super();
        this.setBlockName("anomaly_steam_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        if (world.isRemote) {
            return new ClientSteamTileEntity();
        } else {
            return new ServerSteamTileEntity();
        }
    }

}
