package woven.state;

import net.minecraft.util.math.ChunkPos;

import java.util.HashSet;
import java.util.Set;

public final class ChunkState {

    private static final Set<ChunkPos> UNGENERATE_CHUNKS = new HashSet<>();
    private static final Set<ChunkPos> UNPOP_CHUNKS = new HashSet<>();

    public static boolean willUngenerateChunk(ChunkPos pos) {
        return UNGENERATE_CHUNKS.contains(pos);
    }

    public static boolean willUnpopChunk(ChunkPos pos) {
        return UNPOP_CHUNKS.contains(pos);
    }

    public static boolean ungenerateChunk(ChunkPos pos) {
        return UNGENERATE_CHUNKS.add(pos);
    }

    public static boolean unpopChunk(ChunkPos pos) {
        return UNPOP_CHUNKS.add(pos);
    }

    public static boolean cancelUngenerateChunk(ChunkPos pos) {
        return UNGENERATE_CHUNKS.remove(pos);
    }

    public static boolean cancelUnpopChunk(ChunkPos pos) {
        return UNPOP_CHUNKS.remove(pos);
    }

    public static void dropChunk(ChunkPos pos) {
        UNGENERATE_CHUNKS.remove(pos);
        UNPOP_CHUNKS.remove(pos);
    }

}
