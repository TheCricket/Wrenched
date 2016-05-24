package com.cricketcraft.wrenched.blocks;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.init.ModItems;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import com.cricketcraft.wrenched.util.TeamColor;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPlatform extends Block {
    public static final int PURPLE = 10;
    public static final int RED = 14;
    public static final int BLUE = 3;
    public static final int GREEN = 13;
    public static final int GRAY = 7;

    public BlockPlatform() {
        super(Material.rock);
        setResistance(5.0F);
        setBlockUnbreakable();
        setBlockName("platformBlock");
        setBlockTextureName(Wrenched.MODID + ":platformBlock");
        setCreativeTab(Wrenched.tabWrenched);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
        if (player.getCurrentEquippedItem() != null) {
            if (player.getCurrentEquippedItem().getItem() == ModItems.platformConnector && player.getCurrentEquippedItem().getItemDamage() == 0) {
                testAndSet(world, player, x, y, z, false);
            } else if (player.getCurrentEquippedItem().getItem() == ModItems.platformConnector && player.getCurrentEquippedItem().getItemDamage() == 1) {
                testAndSet(world, player, x, y, z, true);
            }
        }

        return true;
    }

    private void testAndSet(World world, EntityPlayer player, int x, int y, int z, boolean isMainPlatform) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityPlatform) {
            ((TileEntityPlatform) tile).setMainPlatform(isMainPlatform);
            if (world.getBlock(x, y + 1, z) == GameRegistry.findBlock("ExtraUtilities", "color_quartzBlock")) {
                int meta1 = world.getBlockMetadata(x, y + 1, z);

                switch (meta1) {
                    case PURPLE:
                        ((TileEntityPlatform) tile).setTeamColor(TeamColor.PURPLE);
                        world.playSoundAtEntity(player, "random.orb", 1.0F, 0.15F);
                        break;
                    case RED:
                        ((TileEntityPlatform) tile).setTeamColor(TeamColor.RED);
                        world.playSoundAtEntity(player, "random.orb", 1.0F, 0.6F);
                        break;
                    case BLUE:
                        ((TileEntityPlatform) tile).setTeamColor(TeamColor.BLUE);
                        world.playSoundAtEntity(player, "random.orb", 1.0F, 1.5F);
                        break;
                    case GREEN:
                        ((TileEntityPlatform) tile).setTeamColor(TeamColor.GREEN);
                        world.playSoundAtEntity(player, "random.orb", 1.0F, 2.5F);
                        break;
                }
            }
        }
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        return new TileEntityPlatform();
    }
}
