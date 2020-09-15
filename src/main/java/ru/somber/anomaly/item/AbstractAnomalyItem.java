package ru.somber.anomaly.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import ru.somber.anomaly.block.AbstractAnomalyBlock;

public abstract class AbstractAnomalyItem extends ItemBlock {

    public AbstractAnomalyItem(AbstractAnomalyBlock block) {
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
