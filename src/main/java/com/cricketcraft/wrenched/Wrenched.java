package com.cricketcraft.wrenched;

import com.cricketcraft.wrenched.event.ChestOpenListener;
import com.cricketcraft.wrenched.event.EventListener;
import com.cricketcraft.wrenched.init.ModBlocks;
import com.cricketcraft.wrenched.init.ModItems;
import com.cricketcraft.wrenched.util.GameMode;
import com.cricketcraft.wrenched.util.JSONUtils;
import com.cricketcraft.wrenched.util.Mode;
import com.cricketcraft.wrenched.util.TeamColor;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TODO: Finish GUI; Lock the chests until the round starts; Speed boosts for players
 */
@Mod(modid = Wrenched.MODID, name = Wrenched.NAME, version = Wrenched.VERSION)
public class Wrenched {
    public static final String MODID = "wrenched";
    public static final String NAME = "Wrenched";
    public static final String VERSION = "1.0";

    public static ArrayList<TeamColor> currentTeams = new ArrayList<TeamColor>();

    public static File wrenchedDir, easyDir, mediumDir, hardDir;
    public static File easyMachine, easyRedstone, easyTransport, easyMisc;
    public static File mediumMachine, mediumRedstone, mediumTransport, mediumMisc;
    public static File hardMachine, hardRedstone, hardTransport, hardMisc;

    private static GameMode currentMode;

    public static Mode easyMode, normalMode, hardMode;

    public static boolean lockedChests;

    public static CreativeTabs tabWrenched = new CreativeTabs("tabWrenched") {
        @Override
        public Item getTabIconItem() {
            return ModItems.judgesHat;
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        currentTeams.add(TeamColor.PURPLE);
        currentTeams.add(TeamColor.BLUE);
        currentTeams.add(TeamColor.GREEN);
        currentTeams.add(TeamColor.RED);
        ModItems.preInit();
        ModBlocks.preInit();

        ModItems.init();
        ModBlocks.init();

        registerFiles(event);
        loadFiles();

        easyMode = new Mode(easyMachine, easyRedstone, easyTransport, easyMisc);
        normalMode = new Mode(mediumMachine, mediumRedstone, mediumTransport, mediumMisc);
        hardMode = new Mode(hardMachine, hardRedstone, hardTransport, hardMisc);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if(event.getSide() == Side.CLIENT)
            MinecraftForge.EVENT_BUS.register(new EventListener());
        MinecraftForge.EVENT_BUS.register(new ChestOpenListener());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        try {
            JSONUtils.JsonToJava.convert(easyDir, easyMachine, 0);
            JSONUtils.JsonToJava.convert(easyDir, easyRedstone, 1);
            JSONUtils.JsonToJava.convert(easyDir, easyTransport, 2);
            JSONUtils.JsonToJava.convert(easyDir, easyMisc, 3);

            JSONUtils.JsonToJava.convert(mediumDir, mediumMachine, 0);
            JSONUtils.JsonToJava.convert(mediumDir, mediumRedstone, 1);
            JSONUtils.JsonToJava.convert(mediumDir, mediumTransport, 2);
            JSONUtils.JsonToJava.convert(mediumDir, mediumMisc, 3);

            JSONUtils.JsonToJava.convert(hardDir, hardMachine, 0);
            JSONUtils.JsonToJava.convert(hardDir, hardRedstone, 1);
            JSONUtils.JsonToJava.convert(hardDir, hardTransport, 2);
            JSONUtils.JsonToJava.convert(hardDir, hardMisc, 3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameMode getCurrentGamemode() {
        return currentMode;
    }

    public static void setCurrentGamemode(GameMode mode) {
        currentMode = mode;
    }

    private void registerFiles(FMLPreInitializationEvent event) {
        wrenchedDir = new File(event.getModConfigurationDirectory() + "/Wrenched");
        easyDir = registerFile(wrenchedDir, "/Easy Mode");
        mediumDir = registerFile(wrenchedDir, "/Medium Mode");
        hardDir = registerFile(wrenchedDir, "/Hard Mode");

        easyMachine = registerFile(easyDir, "/machines.json");
        easyRedstone = registerFile(easyDir, "/redstone.json");
        easyTransport = registerFile(easyDir, "/transport.json");
        easyMisc = registerFile(easyDir, "/misc.json");

        mediumMachine = registerFile(mediumDir, "/machines.json");
        mediumRedstone = registerFile(mediumDir, "/redstone.json");
        mediumTransport = registerFile(mediumDir, "/transport.json");
        mediumMisc = registerFile(mediumDir, "/misc.json");

        hardMachine = registerFile(hardDir, "/machines.json");
        hardRedstone = registerFile(hardDir, "/redstone.json");
        hardTransport = registerFile(hardDir, "/transport.json");
        hardMisc = registerFile(hardDir, "/misc.json");
    }

    private File registerFile(File dir, String name) {
        return new File(dir.getAbsolutePath() + name);
    }

    private void loadFiles() {
        if(!wrenchedDir.exists())
            wrenchedDir.mkdirs();
        if(!easyDir.exists())
            easyDir.mkdir();
        if(!mediumDir.exists())
            mediumDir.mkdir();
        if(!hardDir.exists())
            hardDir.mkdir();

        loadFile(easyMachine);
        loadFile(easyRedstone);
        loadFile(easyTransport);
        loadFile(easyMisc);

        loadFile(mediumMachine);
        loadFile(mediumRedstone);
        loadFile(mediumTransport);
        loadFile(mediumMisc);

        loadFile(hardMachine);
        loadFile(hardRedstone);
        loadFile(hardTransport);
        loadFile(hardMisc);
    }

    private void loadFile(File file) {
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
