package com.cricketcraft.wrenched.init;

import com.cricketcraft.wrenched.items.ItemDebug;
import com.cricketcraft.wrenched.items.ItemMicrophone;
import com.cricketcraft.wrenched.items.ItemPlatformConnector;
import com.cricketcraft.wrenched.items.ItemSpectatorHat;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems {

    public static ItemSpectatorHat judgesHat;
    public static ItemMicrophone microphone;
    public static ItemPlatformConnector platformConnector;
    public static ItemDebug debug;

    public static void preInit() {
        judgesHat = new ItemSpectatorHat();
        microphone = new ItemMicrophone();
        platformConnector = new ItemPlatformConnector();
        debug = new ItemDebug();
    }

    public static void init() {
        registerItem(judgesHat);
        registerItem(microphone);
        registerItem(platformConnector);
        registerItem(debug);
    }

    private static void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName());
    }
}
