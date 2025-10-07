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
        imageSrcs.put(3,  "truck");
        imageSrcs.put(7,  "mountains");
        imageSrcs.put(11, "bridge");
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
        for (int i = 0; i < imageIndexes.length; i++) {
            clickOnImageAction(imageIndexes[i], i + 1, actionType);
        }
    }

    private void clickOnImageAction(int imageIndex, int buttonIndex, String actionType) {
        By image = By.xpath("(//img)[" + imageIndex + "]");
        String ariaLabel = actionType.equalsIgnoreCase("like") ? "HeartOutline" : "BookmarkOutline";
        By actionButton = By.xpath("(//button[.//div[@role='img' and @aria-label='" + ariaLabel + "']])[" + buttonIndex + "]");

        waitForImageVisible(image);

        String actualSrc = WebUI.getAttributeElement(image, "src");
        String expectedSrc = imageSrcs.get(imageIndex);

        AssertHelper.assertTrue(
                actualSrc.contains(expectedSrc),
                "❌ Image at index " + imageIndex + " title mismatch! Expected to contain: " + expectedSrc + " but got: " + actualSrc
        );
        LogUtils.info("✅ ActualSrc is contains: " + "'" + expectedSrc + "'");

        WebUI.waitForPageLoaded();
        WebUI.clickElement(actionButton);

        if (actionType.equalsIgnoreCase("bookmark")) {
            WebUI.clickElement(buttonAdd);
            WebUI.clickElement(buttonX);
            LogUtils.info("✅ Added image " + actualSrc + " to collection successfully!");
        } else {
            LogUtils.info("❤️ Liked image " + actualSrc + " successfully!");
        }
    }

    private void waitForImageVisible(By image) {
        WebUI.scrollToElementAtBottom(image);
        WebUI.moveToElement(image);

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));
        wait.until(driver -> {
            String title = driver.findElement(image).getAttribute("title");
            return title != null && !title.isEmpty() && !title.contains("blank.gif");
        });
    }

    public void clickOnFirstImage()
    {
        WebUI.clickElement(firstImage);
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
