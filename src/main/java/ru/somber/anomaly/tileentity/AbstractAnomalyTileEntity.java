package ru.somber.anomaly.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class AbstractAnomalyTileEntity extends TileEntity {

    public AbstractAnomalyTileEntity(World world, int metadata) {
        super();

        setWorldObj(world);
        this.blockMetadata = metadata;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 256 * 256;
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    /** Здесь убивать эмиттеры и прочие убиваемые вещи. */
    @Override
    public void invalidate() {
        super.invalidate();
    }


    /** Здесь производить обновление эмиттеров и прочих данных сущности. */
    @Override
    public abstract void updateEntity();

}
