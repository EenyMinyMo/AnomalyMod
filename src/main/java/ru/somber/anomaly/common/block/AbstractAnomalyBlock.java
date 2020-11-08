package ru.somber.anomaly.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import ru.somber.anomaly.AnomalyMod;

public abstract class AbstractAnomalyBlock extends Block implements ITileEntityProvider {

    public AbstractAnomalyBlock() {
        super(Material.rock);

        this.setBlockTextureName(AnomalyMod.MOD_ID + ":empty");
        this.setCreativeTab(CreativeTabs.tabBlock);
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
    public abstract TileEntity createNewTileEntity(World world, int metadata);

}