package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;


public class ItemPlatformConnector extends Item {

    public ItemPlatformConnector() {
        super();
        setHasSubtypes(true);
        setUnlocalizedName("platformConnector");
        setTextureName(Wrenched.MODID + ":platformConnector");
        setCreativeTab(Wrenched.tabWrenched);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return super.getItemStackDisplayName(stack) + " (" + (stack.getItemDamage() == 0 ? "2x2 Mode" : "16x16 Mode") + ")";
    }
}
