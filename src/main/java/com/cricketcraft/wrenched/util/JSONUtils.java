package com.cricketcraft.wrenched.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JSONUtils {

    public static class JsonToJava {

        /**
         * 0 = machines
         * 1 = redstone
         * 2 = transport
         * 3 = misc
         */
        public static void convert(File dir, File file, int type) throws IOException {
            try (Reader reader = new FileReader(file)) {
                Gson gson = new GsonBuilder().create();
                Mode mode = GameMode.getGameModeFromFile(dir).getMode();
                String[] args = gson.fromJson(reader, String[].class);
                switch (type) {
                    case 0:
                        mode.setMachineItems(args);
                        break;
                    case 1:
                        mode.setRedstoneItems(args);
                        break;
                    case 2:
                        mode.setTransportItems(args);
                        break;
                    case 3:
                        mode.setMiscItems(args);
                        break;
                }
                GameMode.getGameModeFromFile(dir).setMode(mode);
            }
        }
    }

    public static class JavaToJson {

        /**
         * 0 = machines
         * 1 = redstone
         * 2 = transport
         * 3 = misc
         */
        public static void convert(GameMode mode, File file, int type) throws IOException {
            Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            Gson gson = new GsonBuilder().create();
            switch (type) {
                case 0:
                    gson.toJson(mode.getMode().getMachineItems(), writer);
                    break;
                case 1:
                    gson.toJson(mode.getMode().getRedstoneItems(), writer);
                    break;
                case 2:
                    gson.toJson(mode.getMode().getTransportItems(), writer);
                    break;
                case 3:
                    gson.toJson(mode.getMode().getMiscItems(), writer);
                    break;
            }
            writer.close();
        }
    }
}
