package woven.commands;

import carpet.commands.CommandCarpetBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import woven.Woven;
import woven.state.RedstoneLog;

public class CommandRedstoneLog extends CommandCarpetBase {

    private static final String USAGE = "redstoneLog <start|stop|status> <x y z>";

    @Override
    public String getName() {
        return "redstoneLog";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return USAGE;
    }

    private void executeBlock(MinecraftServer server, ICommandSender sender, String operation, BlockPos pos) {
        switch (operation) {
            case "start":
                if (RedstoneLog.start(pos)) {
                    notifyCommandListener(sender, this, "Redstone log started for "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Redstone log already running for "
                            + Woven.toString(pos));
                }

                break;

            case "stop":
                if (RedstoneLog.stop(pos)) {
                    notifyCommandListener(sender, this, "Redstone log stopped for "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Redstone log is not running for "
                            + Woven.toString(pos));
                }

                break;

            case "status":
                if (RedstoneLog.status(pos)) {
                    notifyCommandListener(sender, this, "Redstone log is running for "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Redstone log is not running for "
                            + Woven.toString(pos));
                }

                break;
        }
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 4) {
            throw new WrongUsageException(USAGE);
        }

        if (!args[0].equalsIgnoreCase("start") && !args[0].equalsIgnoreCase("stop")
                && !args[0].equalsIgnoreCase("status")) {
            throw new WrongUsageException(USAGE);
        }

        int bx = parseInt(args[1]);
        int by = parseInt(args[2]);
        int bz = parseInt(args[3]);

        executeBlock(server, sender, args[0].toLowerCase(), new BlockPos(bx, by, bz));
    }

}
