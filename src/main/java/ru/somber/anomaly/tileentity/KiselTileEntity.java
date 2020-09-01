package ru.somber.anomaly.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.ClientProxy;
import ru.somber.anomaly.emitter.KiselEmitter;
import ru.somber.particlesystem.emitter.IParticleEmitter;

public class KiselTileEntity extends TileEntity {

    @SideOnly(Side.CLIENT)
    private IParticleEmitter emitter;

    public KiselTileEntity(World world, int metadata) {
        super();

        setWorldObj(world);
        this.blockMetadata = metadata;

        if (world.isRemote) {
            emitter = new KiselEmitter(xCoord + 0.5F, yCoord, zCoord + 0.5F);
            ClientProxy.getEmitterContainer().addEmitter(emitter);
        }
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return 128 * 128;
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) {
            emitter.setPosition(xCoord + 0.5F, yCoord, zCoord + 0.5F);
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

}
