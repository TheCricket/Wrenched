package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import com.cricketcraft.wrenched.util.TeamColor;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.FMLLog;

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
        } else {
            if (world.isRemote) {
                return super.onItemUse(stack, player, world, x, y, z, meta, hitX, hitY, hitZ);
            }
            ServerConfigurationManager sv = MinecraftServer.getServer().getConfigurationManager();
            sv.sendChatMsg(new ChatComponentText("Voting has ended!"));
            int red = 0;
            int green = 0;
            int blue = 0;
            int purple = 0;

            for (int c = 0; c < Wrenched.wrenchedColors.size(); c++) {
                switch (Wrenched.wrenchedColors.get(c)) {
                    case RED:
                        red++;
                        break;
                    case PURPLE:
                        purple++;
                        break;
                    case GREEN:
                        green++;
                        break;
                    case BLUE:
                        blue++;
                        break;
                }
            }

            Integer[] array = {red, green, blue, purple};
            Arrays.sort(array, Collections.reverseOrder());
            TeamColor eliminated = GRAY;
            if (array[0] == red) {
                eliminated = RED;
            } else if (array[0] == blue) {
                eliminated = BLUE;
            } else if (array[0] == green) {
                eliminated = GREEN;
            } else if (array[0] == purple) {
                eliminated = PURPLE;
            }

            for (int c = 0; c < world.loadedTileEntityList.size(); c++) {
                if (world.loadedTileEntityList.get(c) instanceof TileEntityPlatform) {
                    TileEntityPlatform platform = (TileEntityPlatform) world.loadedTileEntityList.get(c);
                    if (platform.getTeamColor() == eliminated)
                        platform.getTeamColor().setEliminated(true);
                }
            }

            Iterator<TeamColor> iterator = Wrenched.currentTeams.iterator();
            while (iterator.hasNext()) {
                TeamColor color = iterator.next();
                if (color.hasBeenEliminated()) {
                    FMLLog.info("Removing a team.");
                    iterator.remove();
                }
            }
        }

        return super.onItemUse(stack, player, world, x, y, z, meta, hitX, hitY, hitZ);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
        list.add(StatCollector.translateToLocal("microphone.shifting.desc"));
        list.add(StatCollector.translateToLocal("microphone.nonshifting.desc"));
    }
}
