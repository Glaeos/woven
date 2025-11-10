package woven.commands;

import carpet.commands.CommandCarpetBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import woven.state.AsyncLog;
import woven.state.ChunkLog;
import woven.state.RedstoneLog;

public class CommandWoven extends CommandCarpetBase {

    private static final String USAGE = "woven <rule> [value]";

    @Override
    public String getName() {
        return "woven";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return USAGE;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(this.getRequiredPermissionLevel(), this.getName());
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            throw new WrongUsageException(USAGE);
        }

        switch (args[0].toLowerCase()) {
            case "logasyncload":
                if (args.length < 2) {
                    notifyCommandListener(sender, this, "Async load logging is currently "
                            + (AsyncLog.logLoads() ? "enabled" : "disabled"));
                    return;
                }

                if (args[1].equalsIgnoreCase("true")) {
                    if (AsyncLog.enableLoadLogging()) {
                        notifyCommandListener(sender, this, "Async load logging enabled");
                    } else {
                        notifyCommandListener(sender, this, "Async load logging already enabled");
                    }

                } else if (args[1].equalsIgnoreCase("false")) {
                    if (AsyncLog.disableLoadLogging()) {
                        notifyCommandListener(sender, this, "Async load logging disabled");
                    } else {
                        notifyCommandListener(sender, this, "Async load logging already disabled");
                    }
                } else {
                    throw new WrongUsageException(USAGE);
                }

                break;

            case "logplayerphase":
                if (args.length < 2) {
                    notifyCommandListener(sender, this, "Player phase logging is currently "
                            + (AsyncLog.logPlayerPhase() ? "enabled" : "disabled"));
                    return;
                }

                if (args[1].equalsIgnoreCase("true")) {
                    if (AsyncLog.enablePlayerPhaseLogging()) {
                        notifyCommandListener(sender, this, "Player phase logging enabled");
                    } else {
                        notifyCommandListener(sender, this, "Player phase logging already enabled");
                    }

                } else if (args[1].equalsIgnoreCase("false")) {
                    if (AsyncLog.disablePlayerPhaseLogging()) {
                        notifyCommandListener(sender, this, "Player phase logging disabled");
                    } else {
                        notifyCommandListener(sender, this, "Player phase logging already disabled");
                    }
                } else {
                    throw new WrongUsageException(USAGE);
                }

                break;

            case "logautosave":
                if (args.length < 2) {
                    notifyCommandListener(sender, this, "Autosave logging is currently "
                            + (ChunkLog.logAutosave() ? "enabled" : "disabled"));
                    return;
                }

                if (args[1].equalsIgnoreCase("true")) {
                    if (ChunkLog.enableAutosaveLogging()) {
                        notifyCommandListener(sender, this, "Autosave logging enabled");
                    } else {
                        notifyCommandListener(sender, this, "Autosave logging already enabled");
                    }

                } else if (args[1].equalsIgnoreCase("false")) {
                    if (ChunkLog.disableAutosaveLogging()) {
                        notifyCommandListener(sender, this, "Autosave logging disabled");
                    } else {
                        notifyCommandListener(sender, this, "Autosave logging already disabled");
                    }
                } else {
                    throw new WrongUsageException(USAGE);
                }

                break;

            case "logRedstoneDust":
                if (args.length < 2) {
                    notifyCommandListener(sender, this, "Redstone dust logging is currently "
                            + (RedstoneLog.logDust() ? "enabled" : "disabled"));
                    return;
                }

                if (args[1].equalsIgnoreCase("true")) {
                    if (RedstoneLog.enableDustLogging()) {
                        notifyCommandListener(sender, this, "Redstone dust logging enabled");
                    } else {
                        notifyCommandListener(sender, this, "Redstone dust logging already enabled");
                    }

                } else if (args[1].equalsIgnoreCase("false")) {
                    if (RedstoneLog.disableDustLogging()) {
                        notifyCommandListener(sender, this, "Redstone dust logging disabled");
                    } else {
                        notifyCommandListener(sender, this, "Redstone dust logging already disabled");
                    }
                } else {
                    throw new WrongUsageException(USAGE);
                }

                break;

            default:
                throw new WrongUsageException(USAGE);
        }
    }

}
