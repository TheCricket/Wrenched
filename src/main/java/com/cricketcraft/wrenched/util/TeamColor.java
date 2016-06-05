package com.cricketcraft.wrenched.util;

public enum TeamColor {
    RED(14, 0xFFFF4C4C),
    GREEN(13, 0xFF4CFF4C),
    YELLOW(4, 0xFFFF00),
    BLUE(3, 0xFF4CA5FF),
    GRAY(7, 0xFF808080);

    public final int meta;
    public final int color;
    private boolean eliminated = false;

    TeamColor(int meta, int col) {
        this.meta = meta;
        this.color = col;
    }

    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }

    public boolean hasBeenEliminated() {
        return eliminated;
    }
}