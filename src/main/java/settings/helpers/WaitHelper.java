package settings.helpers;

import settings.utils.LogUtils;

public class WaitHelper {

    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
            LogUtils.info("Waited for " + seconds + " second(s)");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Sleep was interrupted", e);
        }
    }
}