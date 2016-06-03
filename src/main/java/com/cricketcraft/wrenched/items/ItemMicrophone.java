package com.cricketcraft.wrenched.items;

import com.cricketcraft.wrenched.Wrenched;
import net.minecraft.item.Item;

public class ItemMicrophone extends Item {
    public ItemMicrophone() {
        super();
        setUnlocalizedName("microphone");
        setTextureName(Wrenched.MODID + ":microphone");
        setCreativeTab(Wrenched.tabWrenched);
        setFull3D();
    }
}
