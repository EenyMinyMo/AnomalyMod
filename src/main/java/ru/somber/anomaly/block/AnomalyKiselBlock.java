package ru.somber.anomaly.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.tileentity.KiselTileEntity;

public class AnomalyKiselBlock extends Block implements ITileEntityProvider {

    public AnomalyKiselBlock() {
        super(Material.rock);

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockName("anomaly_kisel_block");
        this.setBlockTextureName(AnomalyMod.MOD_ID + ":empty");

    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new KiselTileEntity(world, metadata);
    }

}
