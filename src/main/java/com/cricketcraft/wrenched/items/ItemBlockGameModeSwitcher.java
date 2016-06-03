package com.cricketcraft.wrenched.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.List;

public class ItemBlockGameModeSwitcher extends ItemBlockWithMetadata {

    private List<String> lewdNames = new ArrayList<String>();

    public ItemBlockGameModeSwitcher(Block block) {
        super(block, block);
        lewdNames.add("Rajecent");
        lewdNames.add("Hermyone");
        lewdNames.add("Cricket");
        lewdNames.add("Beanxxbot");
        lewdNames.add("Blkdragon112");
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "_" + stack.getItemDamage();
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
        list.add(StatCollector.translateToLocal("platformDestroyer.desc"));
        if(stack.getItemDamage() == 2 && lewdNames.contains(player.getDisplayName())){
            list.add(StatCollector.translateToLocal("lewd.desc"));
        }
    }
}
