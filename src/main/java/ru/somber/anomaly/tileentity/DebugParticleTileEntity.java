package ru.somber.anomaly.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.ClientProxy;
import ru.somber.anomaly.client.emitter.DebugEmitter;

public class DebugParticleTileEntity extends AbstractAnomalyTileEntity {

    @SideOnly(Side.CLIENT)
    private DebugEmitter emitter;


    @SideOnly(Side.SERVER)
    public DebugParticleTileEntity() {
        super(null, -1);
    }

    public DebugParticleTileEntity(World world, int metadata) {
        super(world, metadata);

        if (world.isRemote) {
            emitter = new DebugEmitter(xCoord + 0.5F, yCoord, zCoord + 0.5F);
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

            if (getWorldObj().getTotalWorldTime() % 40 == 0) {
//                markDirty();
//                getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        } else {
            if (getWorldObj().getTotalWorldTime() % 40 == 0) {
//                markDirty();
                getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);
            }
//            MinecraftServer server = MinecraftServer.getServer();
//            WorldServer[] worldServers = server.worldServers;
//            worldServers[0].
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setLong("server_tick", getWorldObj().getTotalWorldTime());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        System.out.println("Пакет отправил ебать " + nbt.toString());
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 10, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        System.out.println("Пакет получил ебать " + pkt.func_148857_g().toString());
        readFromNBT(pkt.func_148857_g());
    }
}
