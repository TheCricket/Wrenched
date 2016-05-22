package com.cricketcraft.wrenched.blocks;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.client.GUIVotingTable;
import com.cricketcraft.wrenched.tile.TileEntityTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTable extends BlockContainer {
    public BlockTable() {
        super(Material.wood);
        setBlockUnbreakable();
        setBlockTextureName(Wrenched.MODID + ":table");
        setBlockName("table");
        setCreativeTab(Wrenched.tabWrenched);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            Minecraft.getMinecraft().displayGuiScreen(new GUIVotingTable());
        }
        return super.onBlockActivated(world, x, y, z, player, meta, hitX, hitY, hitZ);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityTable();
    }
}
