package com.cricketcraft.wrenched.tile;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.util.TeamColor;
import com.cricketcraft.wrenched.util.Util;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class TileEntityPlatform extends TileEntity {

    private static Block quartzBlock = null;
    //If it is the main platform it is the 16x one
    private boolean isMainPlatform;
    private TeamColor color;

    public TileEntityPlatform() {
        super();
    }

    private static Block getQuartzBlock() {
        if (quartzBlock == null) {
            quartzBlock = GameRegistry.findBlock("ExtraUtilities", "color_quartzBlock");
        }

        return quartzBlock;
    }

    /**
     * Clears the platform (Either the giant one or the mini one)
     *
     * @param xPos the x of the BlockPlatform
     * @param yPos the y of the BlockPlatform
     * @param zPos the z of the BlockPlatform
     */
    public void clearPlatform(World world, int xPos, int yPos, int zPos) {
        Block quartz = getQuartzBlock();
        if(color != null) {
            if (isMainPlatform) {
                //First we need to clear the platform all the way up to y256
                for (int x = 0; x < 16; x++) {
                    for (int y = 0; y < (256 - yPos); y++) {
                        for (int z = 0; z < 16; z++) {
                            world.setBlockToAir(xPos + x, yPos + y, zPos + z);
                        }
                    }
                }

                //Now that everything is gone lets put it back
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        world.setBlock(xPos + x, yPos + 1, zPos + z, quartz, color.meta, 2);
                    }
                }

            } else {
                //We don't have to worry about anything other than the platform
                for (int x = 0; x < 2; x++) {
                    for (int z = 0; z < 2; z++) {
                        if (color.hasBeenEliminated())
                            world.setBlock(xPos + x, yPos + 1, zPos + z, quartz, TeamColor.GRAY.meta, 2);
                    }
                }
            }
        }
    }

    /**
     * Spawns the chest because I have trust issues
     *
     * @param x the x of the BlockPlatform
     * @param y the y of the BlockPlatform
     * @param z the z of the BlockPlatform
     */
    public void spawnChest(World world, int x, int y, int z) {
        if (world.getBlock(x, y + 1, z) == getQuartzBlock()
                && world.getBlockMetadata(x, y + 2, z) == TeamColor.GRAY.meta)
            world.setBlock(x + 7, y + 1, z + 7, Blocks.chest);
    }

    public void fillChest(World world, int x, int y, int z) {
        //Don't have to check because I just put it there
        if (!(world.getBlock(x, y + 1, z) == getQuartzBlock()
                && world.getBlockMetadata(x, y + 1, z) == TeamColor.GRAY.meta)) {
            TileEntityChest chest = (TileEntityChest) world.getTileEntity(x + 7, y + 1, z + 7);

            ItemStack[] roundLoot = Util.getChestLootForRound(Wrenched.getCurrentGamemode());

            for (int c = 0; c < 4; c++) {
                chest.setInventorySlotContents(c, roundLoot[c]);
            }
        }
    }

    public TeamColor getTeamColor() {
        return color;
    }

    public void setTeamColor(TeamColor color) {
        this.color = color;
    }

    public boolean isMainPlatform() {
        return isMainPlatform;
    }

    public void setMainPlatform(boolean mainPlatform) {
        isMainPlatform = mainPlatform;
    }
}
