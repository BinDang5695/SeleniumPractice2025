package test.ui.pixabaypages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;
import settings.utils.LogUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage {

    private static final Map<Integer, String> imageSrcs = new HashMap<>();
    static {
        imageSrcs.put(3,  "autumn");
        imageSrcs.put(5,  "nature-wallpaper");
        imageSrcs.put(7, "pumpkins");
    }
    private By firstImage = By.xpath("(//img)[3]");
    private By secondImage = By.xpath("(//img)[7]");
    private By thirdImage = By.xpath("(//img)[11]");

    private By imageProfile = By.xpath("//span[@class='label--Ngqjq']//div[@class='container--eBEkE md--vF-bE']");
    private By optionMyProfile = By.xpath("//label[normalize-space()='My profile']");
    private By optionSettings = By.xpath("//label[normalize-space()='Settings']");
    private By optionLibrary = By.xpath("//label[normalize-space()='Library']");

    private By addImage1ToCollection = By.xpath("(//button[.//div[@role='img' and @aria-label='BookmarkOutline']])[1]");
    private By addImage2ToCollection = By.xpath("(//button[.//div[@role='img' and @aria-label='BookmarkOutline']])[2]");
    private By buttonAdd = By.xpath("//div[@class='collectionsContainer--ddRnN']//div[1]//button[1]");
    private By buttonX = By.xpath("//div[@class='container--oGFZI dialogContainer--H+0sJ dialogContainer--DZr5T lg--CQTj3']//button[@class='closeButton--iH9l-']");

    public void clickImagesByAction(int[] imageIndexes, String actionType) {
        for (int imageIndex : imageIndexes) {
            clickOnImageAction(imageIndex, actionType);
        }
    }

    public void clickOnImageAction(int imageIndex, String actionType) {
        By image = By.xpath("(//img)[" + imageIndex + "]");
        String ariaLabel = actionType.equalsIgnoreCase("like") ? "HeartOutline" : "BookmarkOutline";

        By actionButton = By.xpath("(//img)[" + imageIndex + "]/ancestor::div[contains(@class,'cell--') or contains(@class,'card--')][1]"
                + "//button[.//div[@role='img' and @aria-label='" + ariaLabel + "']]");

        String actualSrc = WebUI.getAttributeElement(image, "src");
        String expectedSrc = imageSrcs.get(imageIndex);
        AssertHelper.assertTrue(
                actualSrc.contains(expectedSrc),
                "❌ Image at index " + imageIndex + " mismatch! Expected keyword: " + expectedSrc + " but got: " + actualSrc
        );
        LogUtils.info("✅ Image " + imageIndex + " src contains '" + expectedSrc + "'");

        WebUI.scrollToElement(image);
        WebUI.moveToElement(image);
        WebUI.waitForElementVisible(actionButton);
        WebUI.moveToElement(actionButton);
        WebUI.clickElement(actionButton);

        if (actionType.equalsIgnoreCase("bookmark")) {
            WebUI.clickElement(buttonAdd);
            WebUI.clickElement(buttonX);
            LogUtils.info("✅ Added image " + actualSrc + " to collection successfully!");
        } else {
            LogUtils.info("❤️ Liked image " + actualSrc + " successfully!");
        }
    }

    public void clickOnImageByIndex(int index) {
        By image = By.xpath("(//img)[" + index + "]");
        WebUI.clickElement(image);
    }

    public void clickOnImageProfile()
    {
        WebUI.clickElement(imageProfile);
    }

    public void clickOnOptionSettings()
    {
        WebUI.moveToElement(optionSettings);
        WebUI.clickElement(optionSettings);
    }

    public void clickOnOptionLibrary()
    {
        WebUI.clickElement(optionLibrary);
    }


}
