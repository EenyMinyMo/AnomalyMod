package ru.somber.anomaly.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import ru.somber.anomaly.common.block.AbstractBlockAnomaly;

public abstract class AbstractItemAnomaly extends ItemBlock {

    public AbstractItemAnomaly(AbstractBlockAnomaly block) {
        super(block);
        setMaxStackSize(1);
        setMaxDamage(0);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(this.getIconString());
    }

    @SideOnly(Side.CLIENT)
    public int getSpriteNumber() {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_) {
        return this.itemIcon;
    }

}
