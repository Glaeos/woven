package woven.state;

import net.minecraft.util.math.ChunkPos;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChunkLog {

    private static final AtomicBoolean LOG_AUTOSAVE = new AtomicBoolean(true);
    private static final Set<ChunkPos> LOG_CHUNKS = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public static boolean logAutosave() {
        return LOG_AUTOSAVE.get();
    }

    public static boolean enableAutosaveLogging() {
        return LOG_AUTOSAVE.getAndSet(true);
    }

    public static boolean disableAutosaveLogging() {
        return LOG_AUTOSAVE.getAndSet(false);
    }

    public static boolean status(ChunkPos pos) {
        return LOG_CHUNKS.contains(pos);
    }

    public static boolean start(ChunkPos pos) {
        return LOG_CHUNKS.add(pos);
    }

    public static boolean stop(ChunkPos pos) {
        return LOG_CHUNKS.remove(pos);
    }

    public static boolean log(ChunkPos pos) {
        return LOG_CHUNKS.contains(pos);
    }

}
