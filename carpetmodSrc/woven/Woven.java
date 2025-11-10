package woven;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import woven.util.ChatBuilder;

public final class Woven {

    public static String toString(ChunkPos pos) {
        return pos.x + ", " + pos.z;
    }

    public static String toString(BlockPos pos) {
        return pos.getX() + ", " + pos.getY() + ", " + pos.getZ();
    }

    public static String getTick(World world) {
        if (world.isRemote) {
            return "?";
        }

        return String.valueOf(world.getMinecraftServer().getTickCounter());
    }

    public static ChatBuilder chatIf(boolean condition, String message) {
        if (condition) {
            return new ChatBuilder(message);
        } else {
            return ChatBuilder.IGNORE;
        }
    }

    public static ChatBuilder chat(String message) {
        return new ChatBuilder(message);
    }

    public static boolean broadcast(World world, ITextComponent message, boolean system) {
        if (world.isRemote) {
            return false;
        }

        world.getMinecraftServer().getPlayerList().sendMessage(message, system);
        return true;
    }

    public static boolean broadcast(World world, ITextComponent message) {
        return broadcast(world, message, false);
    }

}
