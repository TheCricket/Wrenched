package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.util.GameMode;
import com.cricketcraft.wrenched.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
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
        Block block = world.getBlock(x, y, z);
        if(block instanceof BlockChest) {
            TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y, z);
            //TODO: Make this randomized
            for(int c = 0; c < GameMode.EASY.getMode().getMachineItems().length; c++ ) {
                chest.setInventorySlotContents(c, Util.getStackFromString(GameMode.EASY.getMode().getMachineItems()[c]));
            }
        }
        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
}
