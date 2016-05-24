package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemMicrophone extends Item {
    public ItemMicrophone() {
        super();
        setUnlocalizedName("microphone");
        setTextureName(Wrenched.MODID + ":microphone");
        setCreativeTab(Wrenched.tabWrenched);
        setFull3D();
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
        if (player.isSneaking()) {
            List<TileEntityPlatform> allPlatforms = new ArrayList<TileEntityPlatform>();

            for (int c = 0; c < world.loadedTileEntityList.size(); c++) {
                if (world.loadedTileEntityList.get(c) instanceof TileEntityPlatform) {
                    allPlatforms.add((TileEntityPlatform) world.loadedTileEntityList.get(c));
                }
            }

            for (TileEntityPlatform platform : allPlatforms) {
                platform.clearPlatform(world, platform.xCoord, platform.yCoord, platform.zCoord);
                platform.spawnChest(world, platform.xCoord, platform.yCoord, platform.zCoord);
                platform.fillChest(world, platform.xCoord, platform.yCoord, platform.zCoord);
            }
        } else {
            Wrenched.lockedChests = !Wrenched.lockedChests;
            player.addChatMessage(new ChatComponentText(Wrenched.lockedChests ? "Chests are now locked" : "Chests are now unlocked"));
        }

        return super.onItemUse(stack, player, world, x, y, z, meta, hitX, hitY, hitZ);
    }
}
