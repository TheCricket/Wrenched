package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import net.minecraft.item.ItemArmor;

public class ItemSpectatorHat extends ItemArmor {

    public ItemSpectatorHat() {
        super(ItemArmor.ArmorMaterial.CHAIN, 0, 0);
        setUnlocalizedName("judgesHat");
        setTextureName(Wrenched.MODID + ":judgesHat");
        setCreativeTab(Wrenched.tabWrenched);
        setMaxStackSize(1);
    }
}
