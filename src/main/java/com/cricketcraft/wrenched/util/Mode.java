package com.cricketcraft.wrenched.util;

import com.google.common.base.Strings;
import net.minecraft.item.ItemStack;

import java.io.File;

public class Mode {

    private String[] machineItems, redstoneItems, transportItems, miscItems;
    private File machines, redstone, transport, misc;

    public Mode(File machines, File redstone, File transport, File misc) {
        this.machines = machines;
        this.redstone = redstone;
        this.transport = transport;
        this.misc = misc;
    }

    public File[] getFiles() {
        return new File[]{machines, redstone, transport, misc};
    }

    public String[] getMachineItems() {
        return machineItems;
    }

    public void setMachineItems(String... val) {
        machineItems = val;
    }

    @Override
    public String toString() {
        return String.valueOf(machineItems);
    }

    public File getMisc() {
        return misc;
    }

    public void setMisc(File misc) {
        this.misc = misc;
    }

    public File getTransport() {
        return transport;
    }

    public void setTransport(File transport) {
        this.transport = transport;
    }

    public File getRedstone() {
        return redstone;
    }

    public void setRedstone(File redstone) {
        this.redstone = redstone;
    }

    public File getMachines() {
        return machines;
    }

    public void setMachines(File machines) {
        this.machines = machines;
    }

    public String[] getRedstoneItems() {
        return redstoneItems;
    }

    public void setRedstoneItems(String[] redstoneItems) {
        this.redstoneItems = redstoneItems;
    }

    public String[] getTransportItems() {
        return transportItems;
    }

    public void setTransportItems(String[] transportItems) {
        this.transportItems = transportItems;
    }

    public String[] getMiscItems() {
        return miscItems;
    }

    public void setMiscItems(String[] miscItems) {
        this.miscItems = miscItems;
    }

    public void setItemsByType(int type, String... items) {
        switch (type) {
            case 0:
                setMachineItems(items);
                break;
            case 1:
                setTransportItems(items);
                break;
            case 2:
                setRedstoneItems(items);
                break;
            case 3:
                setMiscItems(items);
                break;
        }
    }
}
