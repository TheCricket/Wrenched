package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemDebug extends Item {

    public ItemDebug() {
        super();
        setUnlocalizedName("debug");
        setTextureName(Wrenched.MODID + ":debug");
        setCreativeTab(Wrenched.tabWrenched);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        player.addChatMessage(new ChatComponentText("You used it..."));
        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
        list.add(StatCollector.translateToLocal("debug.desc"));
    }
}
