package test.ui.pixabaypages;

import org.openqa.selenium.WebElement;
import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.helpers.FileHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;
import settings.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class LibraryPage {

    private By tabLikes = By.xpath("//span[normalize-space()='Likes']");
    private By image1 = By.xpath("//div[@class='column--HhhwH'][1]");
    private By image2 = By.xpath("//div[@class='column--HhhwH'][2]");
    private By inputCollectionName = By.xpath("(//input[contains(@class, 'interactiveInputStyle')])[2]");
    private By linkCreate = By.xpath("(//button[(.//span[normalize-space()='Create'])])[1]");
    private By createdCollection = By.xpath("//a[normalize-space()='Bin Create New Collection']");
    private By buttonFindMedia = By.xpath("//a[contains(@class,'buttonBase') and normalize-space(.)='Find media']");
    private By buttonCreate = By.xpath("(//button[(.//span[normalize-space()='Create'])])[2]");
    private By removeFromCollection = By.xpath("(//button[@aria-label='Remove from collection'])[2]");
    private By buttonRemove = By.xpath("//button[.//span[normalize-space()='Yes, remove']]");
    private By downloadHistoryPage = By.xpath("//span[normalize-space()='Download history']");
    private By removeFromDownloadHistory = By.xpath("//button[@aria-label='Remove from download history']");
    private By buttonEditCollection = By.xpath("//button[.//span[normalize-space()='Edit collection']]");
    private By buttonDelete = By.xpath("//span[normalize-space()='Delete']");
    private By buttonYesDelete = By.xpath("//span[normalize-space()='Yes, delete']");

    public void clickOnTabLikes()
    {
        WebUI.clickElement(tabLikes);
    }

    public void clickToDisliked() {
        String xpathButton = "//button[contains(@class,'selected--Bz9-S')]";
        List<WebElement> likedButtons = WebUI.getWebElements(By.xpath(xpathButton));

        for (int i = 0; i < likedButtons.size(); i++) {
            int columnIndex = 2 * i + 1;

            By imageContainer = By.xpath("(//div[contains(@class,'column--')])[" + columnIndex + "]");

            WebUI.scrollToElement(imageContainer);
            WebUI.moveToElement(imageContainer);

            WebUI.moveToElement(likedButtons.get(i));
            WebUI.clickElement(likedButtons.get(i));

            LogUtils.info("💔 Disliked image " + (i + 1) + " at column " + columnIndex);
        }

        LogUtils.info("✅ Finished disliking all visible liked images.");
    }

    public void verifyImages(int[] imageIndexes, String actionType) {
        LogUtils.info("🔍 Verifying images for action: " + actionType);

        for (int i = 0; i < imageIndexes.length; i++) {
            int index = imageIndexes[i];
            int columnIndex = 2 * i + 1;
            int imageIndex = i + 1;

            By imageContainer = By.xpath("(//div[contains(@class,'column--')])[" + columnIndex + "]");
            By imageElement = By.xpath("(//img[contains(@title,'Download free HD stock')])[" + imageIndex + "]");

            WebUI.moveToElement(imageContainer);
            WebUI.scrollToElement(imageContainer);

            String actualSrc = WebUI.getAttributeElement(imageElement, "src");

            LogUtils.info("🖼️ Column index" + columnIndex + ": actual src = " + actualSrc + ", index = " + index);

            AssertHelper.assertTrue(actualSrc != null && !actualSrc.isEmpty(),
                    "❌ Image at column " + columnIndex + " (index " + index + ") not loaded properly!");
        }

        LogUtils.info("✅ Verified " + imageIndexes.length + " images for action: " + actionType);

    }

    public void verifyImagesAfterRandomLiked(HomePage homePage, String actionType) {
        List<String> clickedSrcs = homePage.getClickedImageSrcs();

        LogUtils.info("🔍 Verifying clicked images for action: " + actionType);

        if (clickedSrcs == null || clickedSrcs.isEmpty()) {
            LogUtils.error("⚠️ No images saved for verification. Make sure to click first!");
            return;
        }

        for (int i = 0; i < clickedSrcs.size(); i++) {
            String expectedSrc = clickedSrcs.get(i);

            String imageId = expectedSrc.replaceAll(".*/photo/\\d{4}/\\d{2}/\\d{2}/\\d{2}/\\d{2}/(.*?)_+\\d+\\.jpg", "$1");

            LogUtils.info("🔎 Looking for image ID: " + imageId);

            By imageLocator = By.xpath("//img[contains(@src,'" + imageId + "') or contains(@srcset,'" + imageId + "')]");
            WebUI.waitForElementVisible(imageLocator);

            String actualSrc = WebUI.getAttributeElement(imageLocator, "src");
            String actualSrcSet = "";
            try {
                actualSrcSet = WebUI.getAttributeElement(imageLocator, "srcset");
            } catch (Exception ignore) {}

            LogUtils.info(String.format(
                    "🖼️ Image [%d] ID=%s\n  expected=%s\n  actual src=%s\n  actual srcset=%s",
                    i + 1, imageId, expectedSrc, actualSrc, actualSrcSet));

            AssertHelper.assertTrue(actualSrc != null && !actualSrc.isEmpty(),
                    "❌ Image " + (i + 1) + " not loaded properly!");

            boolean matched = (actualSrc.contains(imageId) || (actualSrcSet != null && actualSrcSet.contains(imageId)));
            AssertHelper.assertTrue(matched,
                    "❌ Image mismatch! Expected ID: " + imageId + " | Actual src/srcset: " + actualSrc + " / " + actualSrcSet);
        }

        LogUtils.info("✅ Verified " + clickedSrcs.size() + " clicked images for action: " + actionType);
    }

    public void verifyRemainingImageByColumnAfterRemove(int expectedRemainingCount) {
        LogUtils.info("🔍 Verifying remaining images by column after remove...");

        List<WebElement> imageElements = WebUI.getWebElements(
                By.xpath("//div[contains(@class,'column--')]//img[contains(@title,'Download free HD stock')]")
        );

        int actualCount = imageElements.size();
        LogUtils.info("📊 Found " + actualCount + " image(s) in collection (expected " + expectedRemainingCount + ")");

        for (int i = 0; i < actualCount; i++) {
            WebElement imageElement = imageElements.get(i);
            int columnIndex = 2 * i + 1;
            int imageIndex = i + 1;

            try {
                WebUI.scrollToElement(imageElement);
                WebUI.moveToElement(imageElement);

                String actualSrc = imageElement.getAttribute("src");

                LogUtils.info("🖼️ Column index " + columnIndex + " | image index " + imageIndex + " | src = " + actualSrc);

                AssertHelper.assertTrue(
                        actualSrc != null && !actualSrc.isEmpty(),
                        "❌ Image at column " + columnIndex + " (index " + imageIndex + ") not loaded properly!"
                );

            } catch (Exception e) {
                LogUtils.error("❌ Failed to verify image at index " + imageIndex + ": " + e.getMessage());
            }
        }

        AssertHelper.assertEquals(
                actualCount, expectedRemainingCount,
                "❌ The number of photos remaining in the collection does not match!"
        );

        LogUtils.info("✅ Verified " + actualCount + " remaining image(s) after remove successfully!");
    }

    public void deleteCollectionAfterVerified() {

        WebUI.clickElement(buttonEditCollection);
        WebUI.clickElement(buttonDelete);
        WebUI.clickElement(buttonYesDelete);

        LogUtils.info("🗑️ Collection deleted successfully after verification!");
    }

    public void verifyDownloadImages(String expectedFileName) {

        AssertHelper.assertTrue(WebUI.checkElementExist(image1), "❌ Downloaded image not exist.");
        AssertHelper.assertTrue(WebUI.checkElementDisplayed(image1), "❌ Downloaded image not displayed.");

        String fileName = (expectedFileName != null && !expectedFileName.isEmpty())
                ? expectedFileName : "karlsbad-9718003_1280.jpg";

        LogUtils.info("🔍 Verifying downloaded file: " + fileName);

        FileHelper.verifyAndCleanDownloadedFile(
                DriverManager.getDownloadPath(),
                fileName
        );

        WebUI.moveToElement(image1);
        WebUI.clickElement(removeFromDownloadHistory);
        WebUI.clickElement(buttonRemove);

        LogUtils.info("🗑️ Downloaded image removed successfully!");
    }

    public void createNewCollection()
    {
        WebUI.moveToElement(linkCreate);
        WebUI.clickUntilVisible(linkCreate, inputCollectionName);
        WebUI.setTextElement(inputCollectionName, "Bin Create New Collection");
        WebUI.clickElement(buttonCreate);
    }

    public void clickOnCreatedCollection()
    {
        WebUI.clickElement(createdCollection);
    }

    public void clickOnButtonFindMedia()
    {
        WebUI.clickElement(buttonFindMedia);
        WebUI.sleep(3);
    }

    public void removeImage2FromCollection()
    {
        WebUI.moveToElement(image2);
        WebUI.clickElement(removeFromCollection);
        WebUI.clickElement(buttonRemove);
        LogUtils.info("🗑️ Collection image removed successfully!");
    }

    public void goToDownloadHistoryPage()
    {
        WebUI.openURL("https://pixabay.com/accounts/collections/");
        WebUI.clickElement(downloadHistoryPage);
    }

}
