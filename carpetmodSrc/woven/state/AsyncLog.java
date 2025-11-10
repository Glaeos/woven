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

    public static boolean start(ChunkPos chunkPos) {
        return ASYNC_LOG_CHUNKS.add(chunkPos);
    }

    public static boolean stop(ChunkPos chunkPos) {
        return ASYNC_LOG_CHUNKS.remove(chunkPos);
    }

    public static boolean start(BlockPos blockPos) {
        return ASYNC_LOG_BLOCKS.add(blockPos);
    }

    public static boolean stop(BlockPos blockPos) {
        return ASYNC_LOG_BLOCKS.remove(blockPos);
    }

    public static boolean log(BlockPos pos) {
        return ASYNC_LOG_BLOCKS.contains(pos) || ASYNC_LOG_CHUNKS.contains(new ChunkPos(pos));
    }

}
