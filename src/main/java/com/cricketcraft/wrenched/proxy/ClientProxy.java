package com.cricketcraft.wrenched.proxy;

import com.cricketcraft.wrenched.client.GUIVotingTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void displayVotingTableGui(EntityPlayer ep) {
        Minecraft.getMinecraft().displayGuiScreen(new GUIVotingTable());
    }
}
