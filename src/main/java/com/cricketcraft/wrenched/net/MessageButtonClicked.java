package com.cricketcraft.wrenched.net;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.util.TeamColor;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

public class MessageButtonClicked implements IMessage, IMessageHandler<MessageButtonClicked, IMessage> {
    public int teamID;

    public MessageButtonClicked() {}

    public MessageButtonClicked(int id) {
        teamID = id;
    }

    @Override
    public void fromBytes(ByteBuf io) {
        teamID = io.readUnsignedByte();
    }

    @Override
    public void toBytes(ByteBuf io) {
        io.writeByte(teamID);
    }

    @Override
    public IMessage onMessage(MessageButtonClicked m, MessageContext messageContext) {
        EntityPlayerMP playerMP = messageContext.getServerHandler().playerEntity;
        TeamColor team = TeamColor.values()[m.teamID];

        playerMP.addChatMessage(new ChatComponentText("You selected team " + team));
        Wrenched.wrenchedColors.add(team);

        return null;
    }
}