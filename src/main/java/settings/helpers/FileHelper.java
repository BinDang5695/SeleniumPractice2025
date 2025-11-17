package settings.helpers;

import org.testng.Assert;
import settings.utils.LogUtils;

import java.io.File;

public class FileHelper {

    public static void verifyAndCleanDownloadedFile(String dirPath, String fileName) {
        File file = new File(dirPath + File.separator + fileName);

        int waitTime = 0;
        while (!file.exists() && waitTime < 10) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitTime++;
        }

        Assert.assertTrue(file.exists(), "❌ File not downloaded yet: " + fileName);
        Assert.assertTrue(file.length() > 0, "❌ File downloaded is empty: " + fileName);

        LogUtils.info("✅ File downloaded: " + fileName + " (" + file.length() + " bytes)");

        if (file.delete()) {
            LogUtils.info("🧹 Delete file after cheking: " + fileName);
        } else {
            LogUtils.info("⚠️ Can not delete file: " + fileName);
        }
    }
}
