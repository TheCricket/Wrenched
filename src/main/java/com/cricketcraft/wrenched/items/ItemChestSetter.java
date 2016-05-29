package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.util.GameMode;
import com.cricketcraft.wrenched.util.JSONUtils;
import com.cricketcraft.wrenched.util.Util;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.cricketcraft.wrenched.util.GameMode.*;

public class ItemChestSetter extends Item {

    enum EnumChestSetter {
        EASY_MACHINE(EASY, Wrenched.easyMachine, 0),
        EASY_TRANSPORT(EASY, Wrenched.easyTransport, 1),
        EASY_REDSTONE(EASY, Wrenched.easyRedstone, 2),
        EASY_MISC(EASY, Wrenched.easyMisc, 3),
        MEDIUM_MACHINE(MEDIUM, Wrenched.mediumMachine, 0),
        MEDIUM_TRANSPORT(MEDIUM, Wrenched.mediumTransport, 1),
        MEDIUM_REDSTONE(MEDIUM, Wrenched.mediumRedstone, 2),
        MEDIUM_MISC(MEDIUM, Wrenched.mediumMisc, 3),
        HARD_MACHINE(HARD, Wrenched.hardMachine, 0),
        HARD_TRANSPORT(HARD, Wrenched.hardTransport, 1),
        HARD_REDSTONE(HARD, Wrenched.hardRedstone, 2),
        HARD_MISC(HARD, Wrenched.hardMisc, 3);

        private GameMode mode;
        private File json;
        private int type;

        EnumChestSetter(GameMode mode, File json, int type) {
            this.mode = mode;
            this.json = json;
            this.type = type;
        }

        GameMode getMode() {
            return mode;
        }

        int getType() {
            return type;
        }

        public File getJson() {
            return json;
        }
    }

    public String[] textureNames  = new String[]{
            "easy_machines",
            "easy_transport",
            "easy_redstone",
            "easy_misc",
            "medium_machines",
            "medium_transport",
            "medium_redstone",
            "medium_misc",
            "hard_machines",
            "hard_transport",
            "hard_redstone",
            "hard_misc",
    };

    @SideOnly(Side.CLIENT)
    private IIcon[] icons = new IIcon[textureNames.length];

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        for(int c = 0; c < textureNames.length; c++) {
            icons[c] = iconRegister.registerIcon(Wrenched.MODID + ":" + textureNames[c]);
        }
    }

    public ItemChestSetter() {
        super();
        setUnlocalizedName("chestSetter");
        setHasSubtypes(true);
        setMaxStackSize(1);
        setTextureName(Wrenched.MODID + ":chestSetter");
        setCreativeTab(Wrenched.tabWrenched);
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return icons[damage];
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            EnumChestSetter chestSetter = EnumChestSetter.values()[stack.getItemDamage()];
            if(world.getTileEntity(x, y, z) != null) {
                if(world.getTileEntity(x, y, z) instanceof TileEntityChest) {
                    TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y, z);
                    ItemStack[] inventory = new ItemStack[chest.getSizeInventory()];
                    for(int c = 0; c < chest.getSizeInventory(); c++) {
                        if(chest.getStackInSlot(c) != null) {
                            ItemStack stack1 = chest.getStackInSlot(c);
                            inventory[c] = chest.getStackInSlot(c);
                        }
                    }

                    chestSetter.getMode().getMode().setItemsByType(chestSetter.getType(), Util.getStringsFromStacks(inventory));

                    try {
                        JSONUtils.JavaToJson.convert(Wrenched.getCurrentGamemode(), chestSetter.getJson(), chestSetter.getType());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    FMLLog.info(String.format("[Wrenched] Saved items %s to JSON %s!", Arrays.toString(Util.getStringsFromStacks(inventory)), chestSetter.getJson().getAbsolutePath()));
                }
            }
        }

        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for(int c = 0; c < 12; c++) {
            list.add(new ItemStack(item, 1, c));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "." + stack.getItemDamage();
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
        list.add(StatCollector.translateToLocal("chestSetter.desc"));
    }
}
