package ru.somber.anomaly.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.common.entity.EntityBolt;

public class ItemBolt extends Item {

    public ItemBolt() {
        super();

        this.setUnlocalizedName("item_bolt");
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setTextureName(AnomalyMod.MOD_ID + ":" + "bolt");
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.bow;
    }

    @Override
    public int getItemEnchantability() {
        return 1;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int useDuration) {
        int deltaUseDuration = this.getMaxItemUseDuration(itemStack) - useDuration;

        float forceFactor = deltaUseDuration / 12.0F;

        if (forceFactor < 0.1D) {
            return;
        }

        if (forceFactor > 1.0F) {
            forceFactor = 1.0F;
        }

        EntityBolt entityBolt = new EntityBolt(world, forceFactor, player);
        world.spawnEntityInWorld(entityBolt);
    }

}
