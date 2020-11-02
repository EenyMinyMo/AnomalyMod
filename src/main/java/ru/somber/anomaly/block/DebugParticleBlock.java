package ru.somber.anomaly.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.somber.anomaly.tileentity.DebugParticleTileEntity;

public class DebugParticleBlock extends AbstractAnomalyBlock {

    public DebugParticleBlock() {
        super();
        this.setBlockName("debug_particle_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new DebugParticleTileEntity(world, metadata);
    }

}
