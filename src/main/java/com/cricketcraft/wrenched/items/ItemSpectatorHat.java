package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemSpectatorHat extends ItemArmor {

    public ItemSpectatorHat() {
        super(ItemArmor.ArmorMaterial.CHAIN, 0, 0);
        setUnlocalizedName("judgesHat");
        setTextureName(Wrenched.MODID + ":judgesHat");
        setCreativeTab(Wrenched.tabWrenched);
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
        list.add(StatCollector.translateToLocal("spectatorhat.desc"));
    }
}
