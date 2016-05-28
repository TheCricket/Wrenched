package com.cricketcraft.wrenched.blocks;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import com.cricketcraft.wrenched.tile.TileEntityPlatformDestroyer;
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

public class BlockPlatformDestroyer extends Block implements ITileEntityProvider {

    public BlockPlatformDestroyer() {
        super(Material.rock);
        setCreativeTab(Wrenched.tabWrenched);
        setBlockUnbreakable();
        setBlockName("platformDestroyer");
        setBlockTextureName(Wrenched.MODID + ":platformDestroyer");
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int c = 0; c < 4; c++) {
            list.add(new ItemStack(item, 1, c));
        }
    }


    /**
     * 0 = Green
     * 1 = Blue
     * 2 = Red
     * 3 = Purple
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if(world.isBlockIndirectlyGettingPowered(x, y, z)) {
            //TODO: Clear the platform based on the metadata
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
