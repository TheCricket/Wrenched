package com.cricketcraft.wrenched.init;

import com.cricketcraft.wrenched.blocks.*;
import com.cricketcraft.wrenched.items.ItemBlockGameModeSwitcher;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;

public class ModBlocks {

    public static BlockPlatform platform;
    public static BlockTable votingTable;
    public static BlockLocker locker;
    public static BlockRoundSwitcher roundSwitcher;
    public static BlockGameModeSwitcher platformDestroyer;
    public static BlockChestFiller chestFiller;

    public static void preInit() {
        platform = new BlockPlatform();
        votingTable = new BlockTable();
        locker = new BlockLocker();
        roundSwitcher = new BlockRoundSwitcher();
        platformDestroyer = new BlockGameModeSwitcher();
        chestFiller = new BlockChestFiller();
    }

    public static void init() {
        registerBlock(platform);
        registerBlock(votingTable);
        registerBlock(locker);
        registerBlock(roundSwitcher);
        registerBlock(chestFiller);
        registerBlockWithMetadata(platformDestroyer, ItemBlockGameModeSwitcher.class);

        GameRegistry.registerTileEntity(TileEntityPlatform.class, "TileEntityPlatform");
    }

    private static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName());
    }

    private static void registerBlockWithMetadata(Block block, Class<? extends ItemBlockWithMetadata> clazz) {
        GameRegistry.registerBlock(block, clazz, block.getUnlocalizedName());
    }
}
