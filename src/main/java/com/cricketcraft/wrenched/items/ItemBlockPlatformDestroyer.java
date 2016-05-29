package com.cricketcraft.wrenched.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemBlockPlatformDestroyer extends ItemBlockWithMetadata {

    public ItemBlockPlatformDestroyer(Block block) {
        super(block, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "_" + stack.getItemDamage();
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
        list.add(StatCollector.translateToLocal("platformDestroyer.desc"));
    }
}
