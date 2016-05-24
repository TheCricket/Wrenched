package com.cricketcraft.wrenched.init;

import com.cricketcraft.wrenched.items.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems {

    public static ItemSpectatorHat judgesHat;
    public static ItemMicrophone microphone;
    public static ItemPlatformConnector platformConnector;
    public static ItemDebug debug;
    public static ItemChestSetter chestSetter;

    public static void preInit() {
        judgesHat = new ItemSpectatorHat();
        microphone = new ItemMicrophone();
        platformConnector = new ItemPlatformConnector();
        debug = new ItemDebug();
        chestSetter = new ItemChestSetter();
    }

    public static void init() {
        registerItem(judgesHat);
        registerItem(microphone);
        registerItem(platformConnector);
        registerItem(debug);
        registerItem(chestSetter);
    }

    private static void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName());
    }
}
