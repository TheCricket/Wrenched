package com.cricketcraft.wrenched.blocks;

import com.cricketcraft.wrenched.Wrenched;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLocker extends Block {

    public static boolean areLocked;

    public BlockLocker() {
        super(Material.rock);
        setBlockName("locker");
        setBlockTextureName(Wrenched.MODID + ":locker");
        setCreativeTab(Wrenched.tabWrenched);
        setBlockUnbreakable();
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        FMLLog.info(world.isBlockIndirectlyGettingPowered(x, y, z) ? "TRUE" : "FALSE");
        if(world.isBlockIndirectlyGettingPowered(x, y, z)) {
            areLocked = true;
        } else {
            areLocked = false;
        }
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }
}
