package test.ui.pixabaypages;

import org.openqa.selenium.WebElement;
import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.helpers.FileHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;
import settings.utils.LogUtils;

import java.util.List;

public class LibraryPage {

    private By tabLikes = By.xpath("//span[normalize-space()='Likes']");
    private By image1 = By.xpath("//div[@class='column--HhhwH'][1]");
    private By image2 = By.xpath("//div[@class='column--HhhwH'][2]");
    private By image3 = By.xpath("//div[@class='column--HhhwH'][3]");
    private By buttonDislike = By.xpath("//button[contains(@class,'selected--Bz9-S')]");
    private By inputCollectionName = By.xpath("//div[@class='content--AsYbE']//input[@class='interactiveInputStyle--JrJMF textInput--yG-0W']");
    private By linkCreate = By.xpath("//span[contains(text(),'Create')]");
    private By createdCollection = By.xpath("//a[normalize-space()='Bin Create New Collection']");
    private By buttonFindMedia = By.xpath("//span[normalize-space()='Find media']");
    private By buttonCreate = By.xpath("//span[@class='label--9n8oV']");
    private By removeFromCollection = By.xpath("//div[@class='verticalMasonry--xIvQk multiColumn--qWb9Y lgColumnGap--ICoun']//div[2]//div[1]//div[1]//div[1]//div[1]//div[1]//button[1]//span[1]");
    private By buttonRemove = By.xpath("//div[@class='rightButtons--wQ+Wt']//button[@type='button']");
    private By downloadHistoryPage = By.xpath("//span[normalize-space()='Download history']");
    private By totalImageOnCollection = By.xpath("//span[normalize-space()='1 item']");
    private By iconRemove = By.xpath("//span[@class='icon--L+lBh trash--VuWn-']");
    private By buttonEditCollection = By.xpath("//button[@class='edit--4w63P buttonBase--r4opq tertiaryButton--+4ehJ base--jzyee light--uBcBI']");
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
            WebUI.moveToElement(likedButtons.get(i));
            likedButtons.get(i).click();
            LogUtils.info("Disliked image " + (i + 1));
        }
    }

    public void verifyLikedImages() {
        String[] expectedSrcs = {
                "pumpkins",
                "nature-wallpaper",
                "autumn"
        };

        for (int i = 0; i < expectedSrcs.length; i++) {
            int columnIndex = i + 1;
            By imageContainer = By.xpath("(//div[@class='column--HhhwH'])[" + columnIndex + "]");
            By imageElement = By.xpath("(//div[@class='column--HhhwH'])[" + columnIndex + "]//img");

            WebUI.moveToElement(imageContainer);

            String actualSrc = WebUI.getAttributeElement(imageElement, "src");
            String expectedSrc = expectedSrcs[i];

            LogUtils.info("Column " + columnIndex + ": " + actualSrc);

            AssertHelper.assertTrue(
                    actualSrc.contains(expectedSrc),
                    "❌ Image at column " + columnIndex + " mismatch! Expected keyword: "
                            + expectedSrcs + " but got src: " + actualSrc
            );
        }
        LogUtils.info("✅ All liked images verified successfully!");
    }


    public void verifyImagesOnCollection() {
        LogUtils.info("🔍 Verifying remaining images in the collection...");

        String[] expectedSrcs = {
                "nature-wallpaper"
        };

        for (int i = 0; i < expectedSrcs.length; i++) {
            int columnIndex = i + 1;
            By imageContainer = By.xpath("(//div[@class='column--HhhwH'])[" + columnIndex + "]");
            By imageElement = By.xpath("(//div[@class='column--HhhwH'])[" + columnIndex + "]//img");

            WebUI.moveToElement(imageContainer);

            String actualSrc = WebUI.getAttributeElement(imageElement, "src");
            String expectedSrc = expectedSrcs[i];

            LogUtils.info("🖼️ Column " + columnIndex + " src: " + actualSrc);

            AssertHelper.assertTrue(
                    actualSrc.contains(expectedSrc),
                    "❌ Image at column " + columnIndex + " mismatch! Expected keyword: "
                            + expectedSrc + " but got src: " + actualSrc
            );
        }

        LogUtils.info("✅ Remaining image(s) verified successfully.");

        AssertHelper.assertTrue(WebUI.checkElementExist(totalImageOnCollection), "❌ Total image label not exist.");
        AssertHelper.assertTrue(WebUI.checkElementDisplayed(totalImageOnCollection), "❌ Total image label not displayed.");
        AssertHelper.assertEquals(
                WebUI.getTextElement(totalImageOnCollection),
                "1 item",
                "❌ Total item count text mismatch."
        );
        LogUtils.info("📸 Total item count verified: 1 item");

        WebUI.clickElement(buttonEditCollection);
        WebUI.clickElement(buttonDelete);
        WebUI.clickElement(buttonYesDelete);

        LogUtils.info("🗑️ Collection deleted successfully after verification!");
    }


    public void verifyImagesOnDownloadHistory() {
        AssertHelper.assertTrue(WebUI.checkElementExist(image1), "The image1 not exist.");
        AssertHelper.assertTrue(WebUI.checkElementDisplayed(image1), "The image1 not display");

        FileHelper.verifyAndCleanDownloadedFile(
                DriverManager.getDownloadPath(),
                "autumn-9875155_1280.jpg"
        );
        WebUI.moveToElement(image1);
        WebUI.clickElement(iconRemove);
        WebUI.clickElement(buttonRemove);
    }

    public void createNewCollection()
    {
        WebUI.moveToElement(linkCreate);
        WebUI.clickElement(linkCreate);
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
    }

    public void removeImage2FromCollection()
    {
        WebUI.moveToElement(image2);
        WebUI.clickElement(removeFromCollection);
        WebUI.clickElement(buttonRemove);
    }

    public void goToDownloadHistoryPage()
    {
        WebUI.openURL("https://pixabay.com/accounts/collections/");
        WebUI.clickElement(downloadHistoryPage);
    }


}
