package com.cricketcraft.wrenched.util;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Util {

    public static ItemStack getStackFromString(String string) {
        String[] args = string.split(":");
        Item item = GameRegistry.findItem(args[0], args[1]);
        int size = 1;
        int meta = 0;
        if (args.length == 3)
            size = Integer.valueOf(args[2]);
        if (args.length == 4)
            meta = Integer.valueOf(args[3]);
        if (item instanceof ItemBlock)
            return new ItemStack(Block.getBlockFromItem(item), size, meta);
        return new ItemStack(item, size, meta);
    }

    public static ItemStack[] getStacksFromStrings(String... args) {
        ItemStack[] ret = new ItemStack[args.length];
        for (int c = 0; c < args.length; c++) {
            ret[c] = getStackFromString(args[c]);
        }

        return ret;
    }

    public static String getStringFromStack(ItemStack stack) {
        if(stack == null)
            return "";
        String unlocalizedName = stack.getUnlocalizedName();
        return unlocalizedName + ":" + stack.stackSize + ":" + stack.getItemDamage();
    }

    public static String[] getStringsFromStacks(ItemStack... stacks) {
        List<String> ret = new ArrayList<>();
        for(int c = 0; c < stacks.length; c++) {
            if(stacks[c] != null)
                ret.add(getStringFromStack(stacks[c]));
        }
        String[] trueRet = Arrays.copyOf(ret.toArray(), ret.toArray().length, String[].class);
        return trueRet;
    }

    public static ItemStack[] getChestLootForRound(GameMode mode) {
        ItemStack[] stacks = new ItemStack[4];
        ItemStack defa = new ItemStack(Blocks.wool, 1, 6);
        ItemStack[] machineStacks, redstoneStacks, transportStacks, miscStacks;
        Random random = new Random();

        machineStacks = getStacksFromStrings(mode.getMode().getMachineItems());
        redstoneStacks = getStacksFromStrings(mode.getMode().getRedstoneItems());
        transportStacks = getStacksFromStrings(mode.getMode().getTransportItems());
        miscStacks = getStacksFromStrings(mode.getMode().getMiscItems());

        if(machineStacks != null)
            stacks[0] = machineStacks[random.nextInt(machineStacks.length)];
        else
            stacks[0] = defa;
        if(redstoneStacks != null)
            stacks[1] = redstoneStacks[random.nextInt(redstoneStacks.length)];
        else
            stacks[1] = defa;
        if(transportStacks != null)
            stacks[2] = transportStacks[random.nextInt(transportStacks.length)];
        else
            stacks[2] = defa;
        if(miscStacks != null)
            stacks[3] = miscStacks[random.nextInt(miscStacks.length)];
        else
            stacks[3] = defa;

        return stacks;
    }
}
