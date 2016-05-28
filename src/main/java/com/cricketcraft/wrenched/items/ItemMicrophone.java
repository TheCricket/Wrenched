package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import com.cricketcraft.wrenched.util.TeamColor;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.*;

import static com.cricketcraft.wrenched.util.TeamColor.*;

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
        }

        return super.onItemUse(stack, player, world, x, y, z, meta, hitX, hitY, hitZ);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
        list.add(StatCollector.translateToLocal("microphone.shifting.desc"));
    }
}
