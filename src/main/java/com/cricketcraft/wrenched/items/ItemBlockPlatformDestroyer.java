package com.cricketcraft.wrenched.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockPlatformDestroyer extends ItemBlockWithMetadata {

    public ItemBlockPlatformDestroyer(Block block) {
        super(block, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "_" + stack.getItemDamage();
    }
}
