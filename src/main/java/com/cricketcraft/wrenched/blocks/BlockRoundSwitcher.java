package com.cricketcraft.wrenched.blocks;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import com.cricketcraft.wrenched.util.TeamColor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static com.cricketcraft.wrenched.util.TeamColor.*;
import static com.cricketcraft.wrenched.util.TeamColor.PURPLE;

public class BlockRoundSwitcher extends Block {

    public BlockRoundSwitcher() {
        super(Material.rock);
        setCreativeTab(Wrenched.tabWrenched);
        setBlockUnbreakable();
        setBlockName("roundSwitcher");
        setBlockTextureName(Wrenched.MODID + ":roundSwitcher");
    }

    /**
     * TODO: This just needs to determine the winner and then trigger all the blocks to clear the platform
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if(world.isBlockIndirectlyGettingPowered(x, y, z)) {
            if(!world.isRemote) {
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
                            platform.setEliminated(true);
                    }
                }

                Iterator<TeamColor> iterator = Wrenched.currentTeams.iterator();
                while (iterator.hasNext()) {
                    TeamColor color = iterator.next();
                    if (color.hasBeenEliminated()) {
                        sv.sendChatMsg(new ChatComponentText("Removing a team."));
                        iterator.remove();
                    }
                }

                Wrenched.wrenchedColors.clear();
            }
        }
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }
}
