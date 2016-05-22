package com.cricketcraft.wrenched.util;

import java.awt.*;

public enum TeamColor {
    RED(14, 255.0F, 0.0F, 0.0F),
    PURPLE(10, 255.0F, 0.0F, 255.0F),
    GREEN(13, Color.GREEN.getRed(), Color.GREEN.getGreen(), Color.GREEN.getBlue()),
    BLUE(3, 0.0F, 0.0F, 255.0F),
    GRAY(7, 117.0F, 117.0F, 117.0F);

    private int meta;
    private boolean eliminated = false;
    private float r, g, b;

    TeamColor(int meta, float r, float g, float b) {
        this.meta = meta;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }

    public boolean hasBeenEliminated() {
        return eliminated;
    }

    public int getMeta() {
        return meta;
    }

    public float getRed() {
        return r;
    }

    public float getGreen() {
        return g;
    }

    public float getBlue() {
        return b;
    }
}
