package com.cricketcraft.wrenched.util;

import com.cricketcraft.wrenched.Wrenched;

import java.io.File;

public enum GameMode {
    EASY(Wrenched.easyDir, Wrenched.easyMode),
    MEDIUM(Wrenched.mediumDir, Wrenched.normalMode),
    HARD(Wrenched.hardDir, Wrenched.hardMode);

    public final File dir;
    private Mode mode;

    GameMode(File dir, Mode mode) {
        this.dir = dir;
        this.mode = mode;
    }

    public static GameMode getGameModeFromFile(File file) {
        if (file.equals(Wrenched.easyDir))
            return GameMode.EASY;
        else if (file.equals(Wrenched.mediumDir))
            return GameMode.MEDIUM;
        else if (file.equals(Wrenched.hardDir))
            return GameMode.HARD;
        return null;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }


    @Override
    public String toString() {
        switch (this) {
            case EASY:
                return "EASY";
            case MEDIUM:
                return "MEDIUM";
            case HARD:
                return "HARD";
        }

        return "NULL";
    }
}
