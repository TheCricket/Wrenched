package com.cricketcraft.wrenched.init;

import com.cricketcraft.wrenched.blocks.BlockLocker;
import com.cricketcraft.wrenched.blocks.BlockPlatform;
import com.cricketcraft.wrenched.blocks.BlockTable;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {

    public static BlockPlatform platform;
    public static BlockTable votingTable;
    public static BlockLocker locker;

    public static void preInit() {
        platform = new BlockPlatform();
        votingTable = new BlockTable();
        locker = new BlockLocker();
    }

    public static void init() {
        registerBlock(platform);
        registerBlock(votingTable);
        registerBlock(locker);

        GameRegistry.registerTileEntity(TileEntityPlatform.class, "TileEntityPlatform");
    }

    private static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName());
    }
}
