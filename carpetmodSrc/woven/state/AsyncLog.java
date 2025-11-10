package woven.state;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class AsyncLog {

    private static final AtomicInteger GLASS_THREADS = new AtomicInteger();
    private static final AtomicInteger ASYNC_LINES = new AtomicInteger();

    private static final AtomicBoolean LOG_LOADS = new AtomicBoolean(true);
    private static final AtomicBoolean LOG_PLAYER_PHASE = new AtomicBoolean(true);

    private static final Set<ChunkPos> ASYNC_LOG_CHUNKS = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static final Set<BlockPos> ASYNC_LOG_BLOCKS = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private AsyncLog() {}

    public static int getGlassThreadCount() {
        return GLASS_THREADS.get();
    }

    public static void glassThreadAlive() {
        GLASS_THREADS.incrementAndGet();
    }

    public static void glassThreadDying() {
        GLASS_THREADS.decrementAndGet();
    }

    public static int getAsyncLineCount() {
        return ASYNC_LINES.get();
    }

    public static void asyncLineAlive() {
        ASYNC_LINES.incrementAndGet();
    }

    public static void asyncLineDying() {
        ASYNC_LINES.decrementAndGet();
    }

    public static boolean logLoads() {
        return LOG_LOADS.get();
    }

    public static boolean enableLoadLogging() {
        return LOG_LOADS.getAndSet(true);
    }

    public static boolean disableLoadLogging() {
        return LOG_LOADS.getAndSet(false);
    }

    public static boolean logPlayerPhase() {
        return LOG_PLAYER_PHASE.get();
    }

    public static boolean enablePlayerPhaseLogging() {
        return LOG_PLAYER_PHASE.getAndSet(true);
    }

    public static boolean disablePlayerPhaseLogging() {
        return LOG_PLAYER_PHASE.getAndSet(false);
    }

    public static boolean status(ChunkPos pos) {
        return ASYNC_LOG_CHUNKS.contains(pos);
    }

    public static boolean status(BlockPos pos) {
        return ASYNC_LOG_BLOCKS.contains(pos);
    }

    public static boolean start(ChunkPos pos) {
        return ASYNC_LOG_CHUNKS.add(pos);
    }

    public static boolean stop(ChunkPos pos) {
        return ASYNC_LOG_CHUNKS.remove(pos);
    }

    public static boolean start(BlockPos pos) {
        return ASYNC_LOG_BLOCKS.add(pos);
    }

    public static boolean stop(BlockPos pos) {
        return ASYNC_LOG_BLOCKS.remove(pos);
    }

    public static boolean log(BlockPos pos) {
        return ASYNC_LOG_BLOCKS.contains(pos) || ASYNC_LOG_CHUNKS.contains(new ChunkPos(pos));
    }

}
