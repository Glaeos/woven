package woven.state;

import net.minecraft.util.math.BlockPos;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public final class RedstoneLog {

    private static final AtomicBoolean LOG_DUST = new AtomicBoolean(true);

    private static final Set<BlockPos> LOGGED_POSITIONS = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public static boolean logDust() {
        return LOG_DUST.get();
    }

    public static boolean enableDustLogging() {
        return LOG_DUST.getAndSet(true);
    }

    public static boolean disableDustLogging() {
        return LOG_DUST.getAndSet(false);
    }

    public static boolean status(BlockPos pos) {
        return LOGGED_POSITIONS.contains(pos);
    }

    public static boolean start(BlockPos pos) {
        return LOGGED_POSITIONS.add(pos);
    }

    public static boolean stop(BlockPos pos) {
        return LOGGED_POSITIONS.remove(pos);
    }

    public static boolean log(BlockPos pos) {
        return LOG_DUST.get() && LOGGED_POSITIONS.contains(pos);
    }

}
