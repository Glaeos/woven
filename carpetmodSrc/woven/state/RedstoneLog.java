package woven.state;

import java.util.concurrent.atomic.AtomicBoolean;

public final class RedstoneLog {

    private static final AtomicBoolean LOG_DUST = new AtomicBoolean(false);

    public static boolean logDust() {
        return LOG_DUST.get();
    }

    public static boolean enableDustLogging() {
        return LOG_DUST.getAndSet(true);
    }

    public static boolean disableDustLogging() {
        return LOG_DUST.getAndSet(false);
    }

}
