package test.ui.pexelspages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
<<<<<<< HEAD
import org.testng.Assert;
=======
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.helpers.FileHelper;
import settings.keywords.WebUI;
import settings.utils.LogUtils;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionsPage {

<<<<<<< HEAD
    By image1 = By.xpath("(//img[contains(@src, 'pexels.com/photos')])[1]");
    By image2 = By.xpath("(//img[contains(@src, 'pexels.com/photos')])[2]");
=======
    By image1 = By.xpath("(//article[contains(@class,'MediaCard_card')]//img)[1]");
    By image2 = By.xpath("(//article[contains(@class,'MediaCard_card')]//img)[2]");
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
    By buttonCollectionOfImage1 = By.xpath("(//button[contains(@title, 'Collect')])[1]");
    By buttonCollectionOfImage2 = By.xpath("(//button[contains(@title, 'Collect')])[2]");
    By optionMyCollection = By.xpath("//button[contains(.,'My collection')]");
    By buttonClose = By.xpath("//button[contains(@class, 'Modal_close')]");

    // Scenario 3

    public void verifyLikedRandomImages() {
<<<<<<< HEAD
        By imageLocator = By.xpath("//img[contains(@src, 'pexels.com/photos')]");
=======

        By imageLocator = By.xpath("//article[contains(@class,'MediaCard_card')]//img");

        WebUI.waitForElementVisible(imageLocator);
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0

        LogUtils.info("🔍 Verifying liked random images...");

        List<WebElement> allImages = WebUI.getWebElements(imageLocator);
        int total = allImages.size();
        LogUtils.info("🖼️ Total images loaded for verification: " + total);

        if (total == 0) {
            LogUtils.error("❌ No images found for verification!");
            return;
        }

        List<String> clickedImageSrcs = ImagePage.getClickedImageSrcs();
        List<Integer> clickedImageIndexes = ImagePage.getClickedImageIndexes();

        LogUtils.info("📸 Clicked indexes: " + clickedImageIndexes);
        LogUtils.info("📸 Clicked srcs: " + clickedImageSrcs);

        List<String> allLikedSrcs = allImages.stream()
                .map(img -> img.getAttribute("src"))
                .filter(src -> src != null && !src.isEmpty())
                .collect(Collectors.toList());

        for (String expectedSrc : clickedImageSrcs) {
            String cleanExpected = expectedSrc.split("\\?")[0];
            boolean found = allLikedSrcs.stream().anyMatch(src -> src.startsWith(cleanExpected));
            LogUtils.info(String.format("🧾 Verify liked image: %s | ✅ Found=%s", expectedSrc, found));
            AssertHelper.assertTrue(found, "Liked image not found in verification list!");
        }
        LogUtils.info("🎉 Verification completed for " + clickedImageSrcs.size() + " images");
    }

    public void clickToDisliked() {

        By likedButtonLocator = By.xpath("//button[contains(@title, 'Unlike')]");
        WebUI.waitForElementVisible(likedButtonLocator);
        List<WebElement> likedButtons = WebUI.getWebElements((likedButtonLocator));
        LogUtils.info("💔 Found " + likedButtons.size() + " liked images to dislike.");

        for (int i = 0; i < likedButtons.size(); i++) {
            int imageIndex = i + 1;
<<<<<<< HEAD
            By imageContainer = By.xpath("(//img[contains(@src, 'pexels.com/photos')])[" + imageIndex + "]");

            WebUI.scrollToElement(imageContainer);
=======
            By imageContainer = By.xpath("(//article[contains(@class,'MediaCard_card')]//img)[" + imageIndex + "]");

>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
            WebUI.moveToElement(imageContainer);
            WebUI.moveToElement(likedButtons.get(i));
            WebUI.clickElement(likedButtons.get(i));

            LogUtils.info("💔 Disliked image " + (i + 1) + " at column " + imageIndex);
        }

        LogUtils.info("✅ Finished disliking all visible liked images.");
    }

    // Scenario 4

    public void removeImage2FromCollection()
    {
<<<<<<< HEAD
        WebUI.scrollToElement(image2);
=======
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
        WebUI.moveToElement(image2);
        WebUI.clickUntilVisible(buttonCollectionOfImage2);
        WebUI.clickUntilVisible(optionMyCollection);
        WebUI.clickUntilVisible(buttonClose);
        WebUI.waitForPageRefresh(image1);
        LogUtils.info("🗑️ Image 2 removed successfully from Collection!");
    }

    public void verifyRemainingRandomImagesInCollection() {

        WebUI.moveToElement(image1);
        AssertHelper.assertTrue(WebUI.checkElementExist(image1), "Collected image not found in verification list!");
    }

    public void removeImage1FromCollectionAfterVerified()
    {
<<<<<<< HEAD
        WebUI.moveToElement(image1);
=======
        //WebUI.moveToElement(image1);
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
        WebUI.clickElement(buttonCollectionOfImage1);
        WebUI.clickElement(optionMyCollection);
        WebUI.clickUntilVisible(buttonClose);
        LogUtils.info("🗑️ All image removed successfully from Collection!");
    }

    // Scenario 5

    public void verifyDownloadImages(String expectedFileName) {

<<<<<<< HEAD
=======
        WebUI.waitForElementVisible(image1);
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
        AssertHelper.assertTrue(WebUI.checkElementExist(image1), "❌ Downloaded image not exist.");
        AssertHelper.assertTrue(WebUI.checkElementDisplayed(image1), "❌ Downloaded image not displayed.");

        String fileName = (expectedFileName != null && !expectedFileName.isEmpty())
<<<<<<< HEAD
                ? expectedFileName : "pexels-cileklipalet-34299175.jpg";
=======
                ? expectedFileName : "pexels-gaborbalazs97-33640991.jpg";
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0

        LogUtils.info("🔍 Verifying downloaded file: " + fileName);

        FileHelper.verifyAndCleanDownloadedFile(
                DriverManager.getDownloadPath(),
                fileName
        );

        LogUtils.info("✅ Downloaded image successfully!");
    }




<<<<<<< HEAD
}
=======
}
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
