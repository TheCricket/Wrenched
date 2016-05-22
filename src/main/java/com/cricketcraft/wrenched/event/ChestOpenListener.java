package com.cricketcraft.wrenched.event;

import com.cricketcraft.wrenched.Wrenched;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

public class ChestOpenListener {

    @SubscribeEvent
    public void onPlayerOpenChest(PlayerOpenContainerEvent event) {
        if(!event.entityPlayer.capabilities.isCreativeMode && Wrenched.lockedChests) {
            event.setResult(Event.Result.DENY);
        }
    }
}
