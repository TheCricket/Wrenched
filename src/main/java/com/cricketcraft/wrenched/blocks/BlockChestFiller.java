package com.cricketcraft.wrenched.blocks;

import com.cricketcraft.wrenched.tile.TileEntityPlatform;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author - Kolatra
 */
public class BlockChestFiller extends Block {
    public BlockChestFiller() {
        super(Material.rock);
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
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
                    platform.fillChest(world, platform.xCoord, platform.yCoord, platform.zCoord);
                }
            }
        }
    }
}
