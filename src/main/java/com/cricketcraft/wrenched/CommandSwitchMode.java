package com.cricketcraft.wrenched;

import com.cricketcraft.wrenched.util.GameMode;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

public class CommandSwitchMode extends CommandBase {
    private List<String> aliases;
    private List<String> tabCompletion;

    public CommandSwitchMode() {
        aliases = new ArrayList<String>();
        tabCompletion = new ArrayList<String>();

        aliases.add("wrenched_mode");
        tabCompletion.add("easy");
        tabCompletion.add("medium");
        tabCompletion.add("hard");
    }

    @Override
    public List getCommandAliases() {
        return aliases;
    }

    @Override
    public String getCommandName() {
        return aliases.get(0);
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/wrenched_mode <easy/medium/hard>";
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return tabCompletion;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if(args.length < 1) {
            sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            return;
        } else {
            switch (args[0]) {
                case "easy":
                    Wrenched.setCurrentGamemode(GameMode.EASY);
                    break;
                case "medium":
                    Wrenched.setCurrentGamemode(GameMode.MEDIUM);
                    break;
                case "hard":
                    Wrenched.setCurrentGamemode(GameMode.HARD);
                    break;
                default:
                    Wrenched.setCurrentGamemode(GameMode.MEDIUM);
                    break;
            }
        }

        sender.addChatMessage(new ChatComponentText("Switched Game Mode to " + Wrenched.getCurrentGamemode().toString()));
    }
}
