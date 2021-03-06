package com.cricketcraft.wrenched;

import com.cricketcraft.wrenched.blocks.BlockLocker;
import com.cricketcraft.wrenched.event.WrenchedEventListener;
import com.cricketcraft.wrenched.init.ModBlocks;
import com.cricketcraft.wrenched.init.ModItems;
import com.cricketcraft.wrenched.net.NetHandler;
import com.cricketcraft.wrenched.proxy.CommonProxy;
import com.cricketcraft.wrenched.util.GameMode;
import com.cricketcraft.wrenched.util.JSONUtils;
import com.cricketcraft.wrenched.util.Mode;
import com.cricketcraft.wrenched.util.TeamColor;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = Wrenched.MODID, name = Wrenched.NAME, version = Wrenched.VERSION, dependencies = "required-after:FTBL")
public class Wrenched {
    public static final String MODID = "wrenched";
    public static final String NAME = "Wrenched";
    public static final String VERSION = "1.0";

    public static List<TeamColor> currentTeams = new ArrayList<TeamColor>();
    public static List<TeamColor> wrenchedColors = new ArrayList<>();

    public static File wrenchedDir, easyDir, mediumDir, hardDir;
    public static File easyMachine, easyRedstone, easyTransport, easyMisc;
    public static File mediumMachine, mediumRedstone, mediumTransport, mediumMisc;
    public static File hardMachine, hardRedstone, hardTransport, hardMisc;
    public static Mode easyMode, normalMode, hardMode;
    public static boolean lockedChests;
    public static CreativeTabs tabWrenched = new CreativeTabs("tabWrenched") {
        @Override
        public Item getTabIconItem() {
            return ModItems.judgesHat;
        }
    };
    @SidedProxy(clientSide = "com.cricketcraft.wrenched.proxy.ClientProxy", serverSide = "com.cricketcraft.wrenched.proxy.CommonProxy")
    public static CommonProxy proxy;
    private static GameMode currentMode;

    public static GameMode getCurrentGamemode() {
        return currentMode;
    }

    public static void setCurrentGamemode(GameMode mode) {
        currentMode = mode;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        currentTeams.add(TeamColor.YELLOW);
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

        currentMode = GameMode.EASY;

        if(event.getSide() == Side.CLIENT)
            MinecraftForge.EVENT_BUS.register(new WrenchedEventListener());
        MinecraftForge.EVENT_BUS.register(this);
        NetHandler.init();
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

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandSwitchMode());
    }

    @SubscribeEvent
    public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
        if(event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            player.speedOnGround *= 2.0F;
            player.speedInAir *= 2.0F;
            player.addPotionEffect(new PotionEffect(1, Integer.MAX_VALUE, 4));
            player.setAIMoveSpeed(player.landMovementFactor * 500.0F);
        }
    }

    @SubscribeEvent
    public void onEntityTryToOpenContainer(PlayerInteractEvent event) {
        if(BlockLocker.areLocked && !event.entityPlayer.capabilities.isCreativeMode) {
            event.setCanceled(true);
        }
    }

    private void registerFiles(FMLPreInitializationEvent event) {
        wrenchedDir = new File(event.getModConfigurationDirectory(), "/Wrenched");
        easyDir = new File(wrenchedDir, "/Easy Mode");
        mediumDir = new File(wrenchedDir, "/Medium Mode");
        hardDir = new File(wrenchedDir, "/Hard Mode");

        easyMachine = new File(easyDir, "/machines.json");
        easyRedstone = new File(easyDir, "/redstone.json");
        easyTransport = new File(easyDir, "/transport.json");
        easyMisc = new File(easyDir, "/misc.json");

        mediumMachine = new File(mediumDir, "/machines.json");
        mediumRedstone = new File(mediumDir, "/redstone.json");
        mediumTransport = new File(mediumDir, "/transport.json");
        mediumMisc = new File(mediumDir, "/misc.json");

        hardMachine = new File(hardDir, "/machines.json");
        hardRedstone = new File(hardDir, "/redstone.json");
        hardTransport = new File(hardDir, "/transport.json");
        hardMisc = new File(hardDir, "/misc.json");
    }

    private void loadFiles() {
        if (!wrenchedDir.exists())
            wrenchedDir.mkdirs();
        if (!easyDir.exists())
            easyDir.mkdir();
        if (!mediumDir.exists())
            mediumDir.mkdir();
        if (!hardDir.exists())
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
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
