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
import woven.state.ChunkLog;

public class CommandChunkLog extends CommandCarpetBase {

    private static final String USAGE = "chunkLog <start|stop|status> <x z>";

    @Override
    public String getName() {
        return "chunkLog";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return USAGE;
    }

    private void executeChunk(MinecraftServer server, ICommandSender sender, String operation, ChunkPos pos) {
        switch (operation) {
            case "start":
                if (ChunkLog.start(pos)) {
                    notifyCommandListener(sender, this, "Chunk log started for "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Chunk log already running for "
                            + Woven.toString(pos));
                }

                break;

            case "stop":
                if (ChunkLog.stop(pos)) {
                    notifyCommandListener(sender, this, "Chunk log stopped for "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Chunk log is not running for "
                            + Woven.toString(pos));
                }

                break;

            case "status":
                if (ChunkLog.status(pos)) {
                    notifyCommandListener(sender, this, "Chunk log is running for "
                            + Woven.toString(pos));
                } else {
                    notifyCommandListener(sender, this, "Chunk log is not running for "
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

        int cx = parseInt(args[1]);
        int cz = parseInt(args[2]);

        executeChunk(server, sender, args[0].toLowerCase(), new ChunkPos(cx, cz));
    }

}
