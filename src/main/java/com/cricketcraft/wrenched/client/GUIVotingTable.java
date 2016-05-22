package com.cricketcraft.wrenched.client;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.tile.TileEntityPlatform;
import com.cricketcraft.wrenched.util.TeamColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIVotingTable extends GuiScreen {

    public GUIVotingTable() {

    }

    @Override
    public void initGui() {
        super.initGui();

    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        buttonList.clear();
        int buttonID = 0;
        for(int c = 0; c < Wrenched.currentTeams.size(); c++ ) {
            TeamColor color = Wrenched.currentTeams.get(c);
            switch (c) {
                case 0:
                    buttonList.add(new TeamSelectionButton(buttonID, (width / 2) - 125, (height / 2) - 125, "", color.getRed(), color.getGreen(), color.getBlue()));
                    break;
                case 1:
                    buttonList.add(new TeamSelectionButton(buttonID, (width / 2) + 125, (height / 2) - 125, "", color.getRed(), color.getGreen(), color.getBlue()));
                    break;
                case 2:
                    buttonList.add(new TeamSelectionButton(buttonID, (width / 2) - 125, (height / 2) + 125, "", color.getRed(), color.getGreen(), color.getBlue()));
                    break;
                case 3:
                    buttonList.add(new TeamSelectionButton(buttonID, (width / 2) + 125, (height / 2) + 125, "", color.getRed(), color.getGreen(), color.getBlue()));
                    break;
            }
            buttonID++;
        }
    }

    @Override
    public void drawScreen(int width, int height, float partialTicks) {
        super.drawScreen(width, height, partialTicks);
        updateScreen();
    }

    @Override
    protected void mouseClickMove(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_) {
        super.mouseClickMove(p_146273_1_, p_146273_2_, p_146273_3_, p_146273_4_);
    }

    @Override
    protected void actionPerformed(GuiButton p_146284_1_) {
        super.actionPerformed(p_146284_1_);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    static class TeamSelectionButton extends GuiButton {

        private float r, g, b;

        public TeamSelectionButton(int id, int x, int y, String buttonText, float r, float g, float b) {
            super(id, x, y, buttonText);
            this.r = r;
            this.g = g;
            this.b = b;
        }

        @Override
        public void drawButton(Minecraft minecraft, int x, int y) {
            boolean isButtonPressed = (x >= xPosition)
                    && (y >= yPosition)
                    && (x < xPosition + width)
                    && (y < yPosition + height);
            GL11.glColor4f(r / 255.0F, g / 255.0F, b / 255.0F, 0.5F);
            minecraft.getTextureManager().bindTexture(new ResourceLocation(Wrenched.MODID + ":textures/gui/button"));
            int textureX = 0;
            int textureY = 0;

            if(isButtonPressed) {
                GL11.glColor4f(r / 255.0F, g / 255.0F, b / 255.0F, 1.0F);
            }
            drawTexturedModalRect(xPosition, yPosition, textureX, textureY, 64, 64);
        }
    }
}
