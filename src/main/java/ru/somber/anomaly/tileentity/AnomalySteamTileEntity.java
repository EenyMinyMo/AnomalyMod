package ru.somber.anomaly.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import ru.somber.anomaly.ClientProxy;
import ru.somber.anomaly.client.emitter.SteamEmitter;

public class AnomalySteamTileEntity extends AbstractAnomalyTileEntity {

    @SideOnly(Side.CLIENT)
    private SteamEmitter emitter;

    public AnomalySteamTileEntity(World world, int metadata) {
        super(world, metadata);

        if (world.isRemote) {
            emitter = new SteamEmitter(xCoord + 0.5F, yCoord, zCoord + 0.5F);
            ClientProxy.getEmitterContainer().addEmitter(emitter);
        }
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();

        if (worldObj.isRemote) {
            emitter.setDie();
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();

        if (worldObj.isRemote) {
            emitter.setDie();
        }
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) {
            emitter.setPosition(xCoord + 0.5F, yCoord, zCoord + 0.5F);  //каждый раз ставить позицию, т.к. по другому позицию не поставить.
        }
    }

}

