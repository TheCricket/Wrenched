package com.cricketcraft.wrenched.blocks;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import com.cricketcraft.wrenched.tile.TileEntityPlatformDestroyer;
import com.cricketcraft.wrenched.util.GameMode;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockGameModeSwitcher extends Block implements ITileEntityProvider {

    public BlockGameModeSwitcher() {
        super(Material.rock);
        setCreativeTab(Wrenched.tabWrenched);
        setBlockUnbreakable();
        //I'm too lazy to rename it...
        setBlockName("platformDestroyer");
        setBlockTextureName(Wrenched.MODID + ":platformDestroyer");
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int c = 0; c < 3; c++) {
            list.add(new ItemStack(item, 1, c));
        }
    }


    /**
     * 0 = Easy
     * 1 = Medium
     * 2 = Hard
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if(world.isBlockIndirectlyGettingPowered(x, y, z)) {
            switch(world.getBlockMetadata(x, y, z)) {
                case 0:
                    Wrenched.setCurrentGamemode(GameMode.EASY);
                    break;
                case 1:
                    Wrenched.setCurrentGamemode(GameMode.MEDIUM);
                    break;
                case 2:
                    Wrenched.setCurrentGamemode(GameMode.HARD);
                    break;
            }
        }
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityPlatformDestroyer();
    }
}
