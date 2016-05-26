package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.util.GameMode;
import com.cricketcraft.wrenched.util.TeamColor;
import com.cricketcraft.wrenched.util.Util;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemDebug extends Item {

    public ItemDebug() {
        super();
        setUnlocalizedName("debug");
        setTextureName(Wrenched.MODID + ":debug");
        setCreativeTab(Wrenched.tabWrenched);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = GameRegistry.findBlock("ExtraUtilities", "color_quartzBlock");
        if (player.isSneaking()) {
            TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y, z);
            if (world.getBlock(x, y, z) == Blocks.chest) {
                for (int i = 0; i < chest.getSizeInventory(); i++) {
                    if (chest.getStackInSlot(i) != null) {
                        System.out.println("Found an item at slot number " + i);
                        System.out.println(chest.getStackInSlot(i).getDisplayName());
                    }
                }
            }
        } else {
            for(int x1 = 0; x1 < 16; x1++) {
                for(int z1 = 0; z1 < 16; z1++) {
                    world.setBlock(x + x1, y + 1, z + z1, block);
                    world.setBlockMetadataWithNotify(x + x1, y + 1, z + z1, world.getBlockMetadata(x, y, z), 2);
                }
            }
        }
        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
}
