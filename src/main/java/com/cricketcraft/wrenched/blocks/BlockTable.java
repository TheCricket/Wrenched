package com.cricketcraft.wrenched.blocks;

import com.cricketcraft.wrenched.Wrenched;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockTable extends Block {
    public BlockTable() {
        super(Material.wood);
        setBlockUnbreakable();
        setBlockTextureName(Wrenched.MODID + ":table");
        setBlockName("table");
        setCreativeTab(Wrenched.tabWrenched);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            Wrenched.proxy.displayVotingTableGui(player);
        }

        return true;
    }
}
