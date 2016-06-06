package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class ItemMicrophone extends Item {
    public ItemMicrophone() {
        super();
        setUnlocalizedName("microphone");
        setTextureName(Wrenched.MODID + ":microphone");
        setCreativeTab(Wrenched.tabWrenched);
        setFull3D();
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (player.isSneaking()) {
                if (world.getTileEntity(x, y, z) != null) {
                    TileEntityChest clickedChest = (TileEntityChest) world.getTileEntity(x, y, z);
                    ItemStack[] inventory = new ItemStack[clickedChest.getSizeInventory()];
                    for (int i = 0; i < clickedChest.getSizeInventory(); i++) {
                        if (clickedChest.getStackInSlot(i) != null) {
                            inventory[i] = clickedChest.getStackInSlot(i);
                        }
                    }
                    List<TileEntityPlatform> platforms = new ArrayList<>();
                    for (int c = 0; c < world.loadedTileEntityList.size(); c++) {
                        if (world.loadedTileEntityList.get(c) instanceof TileEntityPlatform) {
                            platforms.add((TileEntityPlatform) world.loadedTileEntityList.get(c));
                        }
                    }
                    for (TileEntityPlatform platform : platforms) {
                        if (world.getBlock(x, y, z) == null) {
                            platform.spawnChest(world, platform.xCoord, platform.yCoord, platform.zCoord);
                        }
                        int chestX = platform.xCoord + 7;
                        int chestY = platform.yCoord + 2;
                        int chestZ = platform.zCoord + 7;
                        TileEntityChest chest = (TileEntityChest) world.getTileEntity(chestX, chestY, chestZ);
                        for (int i = 0; i < inventory.length; i++) {
                            chest.setInventorySlotContents(i, inventory[i]);
                        }
                    }
                }
            }
        }
        return false;
    }
}
