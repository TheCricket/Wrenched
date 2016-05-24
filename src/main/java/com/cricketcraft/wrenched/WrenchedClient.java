package com.cricketcraft.wrenched;

import com.cricketcraft.wrenched.client.GUIVotingTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by LatvianModder on 24.05.2016.
 */
@SideOnly(Side.CLIENT)
public class WrenchedClient extends WrenchedCommon
{
    @Override
    public void displayVotingTableGui(EntityPlayer ep)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GUIVotingTable());
    }
}
