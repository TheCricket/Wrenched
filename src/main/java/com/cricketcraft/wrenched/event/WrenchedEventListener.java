package com.cricketcraft.wrenched.event;

import com.cricketcraft.wrenched.init.ModItems;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class WrenchedEventListener {

    private static boolean isPlayerWearingHat(EntityPlayer player) {
        ItemStack is = player.inventory.armorInventory[3];
        return is != null && is.getItem() == ModItems.judgesHat;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderOp(RenderPlayerEvent.Pre event) {
        if (isPlayerWearingHat(event.entityPlayer) && isPlayerWearingHat(Minecraft.getMinecraft().thePlayer)) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        } else if (isPlayerWearingHat(event.entityPlayer)) {
            event.setCanceled(true);
            event.renderer.shadowOpaque = 0;
            Minecraft.getMinecraft().gameSettings.particleSetting = 2;
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderOpPost(RenderPlayerEvent.Post event) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderSpecialsOp(RenderPlayerEvent.Specials.Pre event) {
        if (isPlayerWearingHat(event.entityPlayer) && isPlayerWearingHat(Minecraft.getMinecraft().thePlayer)) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        } else if (isPlayerWearingHat(event.entityPlayer)) {
            event.setCanceled(true);
            event.renderer.shadowOpaque = 0;
            Minecraft.getMinecraft().gameSettings.particleSetting = 2;
        }
    }
}
