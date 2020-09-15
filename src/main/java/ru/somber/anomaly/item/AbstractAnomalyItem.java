package ru.somber.anomaly.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import ru.somber.anomaly.block.AbstractAnomalyBlock;

public abstract class AbstractAnomalyItem extends ItemBlock {

    public AbstractAnomalyItem(AbstractAnomalyBlock block) {
        super(block);
        setMaxStackSize(1);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabBlock);
    }



}
