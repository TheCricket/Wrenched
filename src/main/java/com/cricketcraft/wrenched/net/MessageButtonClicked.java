package com.cricketcraft.wrenched.net;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import com.cricketcraft.wrenched.util.TeamColor;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class MessageButtonClicked implements IMessage, IMessageHandler<MessageButtonClicked, IMessage> {
    public int teamID;

    public MessageButtonClicked() {
    }

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
        World world = messageContext.getServerHandler().playerEntity.getEntityWorld();
        for(int c = 0; c < world.loadedTileEntityList.size(); c++) {
            if(world.loadedTileEntityList.get(c) instanceof TileEntityPlatform) {
                TileEntityPlatform platform = (TileEntityPlatform) world.loadedTileEntityList.get(c);
                platform.getTeamColor().setEliminated(true);
            }
        }

        for(TeamColor color : Wrenched.currentTeams) {
            if(color.hasBeenEliminated()) {
                Wrenched.currentTeams.remove(color);
            }
        }
        return null;
    }
}