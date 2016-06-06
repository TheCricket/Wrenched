package com.cricketcraft.wrenched.client;

import com.cricketcraft.wrenched.Wrenched;
import com.cricketcraft.wrenched.net.MessageButtonClicked;
import com.cricketcraft.wrenched.net.NetHandler;
import com.cricketcraft.wrenched.util.TeamColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ftb.lib.api.client.FTBLibClient;
import ftb.lib.api.client.GlStateManager;
import ftb.lib.api.gui.FTBLibLang;
import ftb.lib.api.gui.GuiLM;
import ftb.lib.api.gui.IGuiLM;
import ftb.lib.api.gui.widgets.ButtonLM;
import ftb.lib.api.gui.widgets.TextBoxLM;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GUIVotingTable extends GuiLM {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Wrenched.MODID, "textures/gui/voting_table.png");
    public List<ButtonVoteTeam> buttons;
    public ButtonLM buttonAccept;
    public ButtonVoteTeam selectedTeam;

    public GUIVotingTable() {
        super(null, TEXTURE);

        mainPanel.width = 158;
        mainPanel.height = 72;

        buttons = new ArrayList<>();
        buttons.add(new ButtonVoteTeam(TeamColor.GREEN, 9, 9));
        buttons.add(new ButtonVoteTeam(TeamColor.BLUE, 45, 9));
        buttons.add(new ButtonVoteTeam(TeamColor.YELLOW, 81, 9));
        buttons.add(new ButtonVoteTeam(TeamColor.RED, 117, 9));

        buttonAccept = new ButtonLM(this, 51, 49, 56, 13) {
            @Override
            public void onButtonPressed(int i) {
                FTBLibClient.playClickSound();

                if (selectedTeam != null) {
                    NetHandler.NET.sendToServer(new MessageButtonClicked(selectedTeam.color.ordinal()));
                    mc.thePlayer.closeScreen();
                }
            }
        };
    }

    @Override
    public void addWidgets() {
        for (ButtonVoteTeam b : buttons) {
            mainPanel.add(b);
        }

        mainPanel.add(buttonAccept);
    }

    @Override
    public void drawBackground() {
        super.drawBackground();

        if (selectedTeam != null) {
            selectedTeam.renderWidget();
        }

        drawCenteredString(getFontRenderer(), FTBLibLang.button_accept(), buttonAccept.getAX() + buttonAccept.width / 2, buttonAccept.getAY() + 2, selectedTeam == null ? 0xFF888888 : 0xFFFFFFFF);
        for(int c = 0; c < buttons.size(); c++) {
            drawCenteredString(getFontRenderer(), buttons.get(c).color.name(), buttons.get(c).getAX() + buttons.get(c).width / 2, buttons.get(c).getAY() + 2, 0xFFFFFFFF);
        }
    }

    public class ButtonVoteTeam extends ButtonLM {
        public final TeamColor color;

        public ButtonVoteTeam(TeamColor c, int x, int y) {
            super(GUIVotingTable.this, x, y, 32, 32);
            color = c;
        }

        @Override
        public void onButtonPressed(int i) {
            FTBLibClient.playClickSound();
            selectedTeam = this;
        }

        @Override
        public void renderWidget() {
            if (selectedTeam == this) {
                float alpha = (float) (0.3D + 0.2D * Math.sin(System.currentTimeMillis() * 0.007D));
                GlStateManager.color(1F, 1F, 1F, alpha);
                GuiLM.drawBlankRect(getAX(), getAY(), gui.getZLevel(), width, height);
                GlStateManager.color(1F, 1F, 1F, 1F);
            }
        }
    }
}