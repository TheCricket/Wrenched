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

import java.util.*;

import static com.cricketcraft.wrenched.util.TeamColor.*;

public class BlockRoundStarter extends Block {

    public BlockRoundStarter() {
        super(Material.rock);
        setCreativeTab(Wrenched.tabWrenched);
        setBlockUnbreakable();
        setBlockName("roundSwitcher");
        setBlockTextureName(Wrenched.MODID + ":roundSwitcher");
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if(world.isBlockIndirectlyGettingPowered(x, y, z)) {
            if(!world.isRemote) {
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
        }
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }
}
