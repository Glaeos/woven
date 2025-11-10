package woven.commands;

import carpet.commands.CommandCarpetBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import woven.Woven;
import woven.state.AsyncLog;

public class CommandAsyncLog extends CommandCarpetBase {

    private static final String USAGE = "asyncLog <start|stop|status> <x y z|x z>";

    @Override
    public String getName() {
        return "asyncLog";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return USAGE;
    }

    private void executeChunk(MinecraftServer server, ICommandSender sender, String operation, ChunkPos pos) {
        switch (operation) {
            case "start":
                if (AsyncLog.start(pos)) {
                    notifyCommandListener(sender, this, "Async log started for chunk "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Async log already running for chunk "
                            + Woven.toString(pos));
                }

                break;

            case "stop":
                if (AsyncLog.stop(pos)) {
                    notifyCommandListener(sender, this, "Async log stopped for chunk "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Async log is not running for chunk "
                            + Woven.toString(pos));
                }

                break;

            case "status":
                if (AsyncLog.status(pos)) {
                    notifyCommandListener(sender, this, "Async log is running for chunk "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Async log is not running for chunk "
                            + Woven.toString(pos));
                }

                break;
        }
    }

    private void executeBlock(MinecraftServer server, ICommandSender sender, String operation, BlockPos pos) {
        switch (operation) {
            case "start":
                if (AsyncLog.start(pos)) {
                    notifyCommandListener(sender, this, "Async log started for block "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Async log already running for block "
                            + Woven.toString(pos));
                }

                break;

            case "stop":
                if (AsyncLog.stop(pos)) {
                    notifyCommandListener(sender, this, "Async log stopped for block "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Async log is not running for block "
                            + Woven.toString(pos));
                }

                break;

            case "status":
                if (AsyncLog.status(pos)) {
                    notifyCommandListener(sender, this, "Async log is running for block "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Async log is not running for block "
                            + Woven.toString(pos));
                }

                break;
        }
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 3) {
            throw new WrongUsageException(USAGE);
        }

        if (!args[0].equalsIgnoreCase("start") && !args[0].equalsIgnoreCase("stop")
                && !args[0].equalsIgnoreCase("status")) {
            throw new WrongUsageException(USAGE);
        }

        switch (args.length) {
            case 4:
                int bx = parseInt(args[1]);
                int by = parseInt(args[2]);
                int bz = parseInt(args[3]);

                executeBlock(server, sender, args[0].toLowerCase(), new BlockPos(bx, by, bz));
                break;

            case 3:
                int cx = parseInt(args[1]);
                int cz = parseInt(args[2]);

                executeChunk(server, sender, args[0].toLowerCase(), new ChunkPos(cx, cz));
                break;

            default:
                throw new WrongUsageException(USAGE);
        }
    }

}
