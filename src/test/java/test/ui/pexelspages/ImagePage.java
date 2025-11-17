package test.ui.pexelspages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import settings.helpers.AssertHelper;
import settings.keywords.WebUI;
import settings.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImagePage {

    By buttonFollow = By.xpath("//button[contains(text(),'Follow')]");
    By buttonFollowing = By.xpath("//button[contains(text(),'Following')]");
    By imageProfile = By.xpath("//img[@alt='Bin Dang']");
    By optionYourCollections = By.xpath("//a[normalize-space()='Your Collections']");
    By optionMyCollection = By.xpath("//button[contains(.,'My collection')]");
    By buttonClose = By.xpath("//button[@class='Modal_close__Pf0IY']");
    By buttonFreeDownload = By.xpath("(//a[.='Free download'])[2]");

    // Scenario 1

    public void clickOnImageByIndex(int index) {
        By image = By.xpath("//article[contains(@class,'MediaCard_card')]");
        WebUI.scrollToElementTest(image);
        WebUI.moveToElement(image);
        WebUI.clickUntilVisible(image, buttonFollow);
    }

    public void clickOnButtonFollow()
    {
        WebUI.clickUntilVisible(buttonFollow, buttonFollowing);
    }

    public void verifyButtonFollowChangeToFollowing()
    {
        AssertHelper.assertTrue(WebUI.checkElementExist(buttonFollowing), "The buttonFollowing not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(buttonFollowing), "Following", "The buttonFollowing text not match.");
    }

    public void clickFollowingToUnfollow()
    {
        WebUI.clickUntilVisible(buttonFollowing);
    }

    // Scenario 3

    private static List<Integer> clickedImageIndexes = new ArrayList<>();
    private static List<String> clickedImageSrcs = new ArrayList<>();

    public static List<Integer> getClickedImageIndexes() {
        return clickedImageIndexes;
    }

    public static List<String> getClickedImageSrcs() {
        return clickedImageSrcs;
    }

    public void clickLikeRandomImages(int numberOfImages) {
        By imageLocator = By.xpath("//article//img[contains(@src,'pexels.com')]");

        LogUtils.info("🚀 Loading images on Pexels...");

        int prevCount = 0;
        int tries = 0;
        while (tries < 1) {
            WebUI.scrollToBottom();
            WebUI.sleep(1);
            List<WebElement> imgs = WebUI.getWebElements(imageLocator);
            if (imgs.size() > prevCount) {
                prevCount = imgs.size();
            } else {
                break;
            }
            tries++;
        }

        List<WebElement> allImages = WebUI.getWebElements(imageLocator);
        int totalImages = allImages.size();
        LogUtils.info("🖼️ Total loaded images: " + totalImages);

        if (totalImages == 0) {
            LogUtils.error("❌ No images found on Pexels page.");
            return;
        }

        numberOfImages = Math.min(numberOfImages, totalImages);
        List<Integer> randomIndexes = new ArrayList<>();
        Random random = new Random();
        while (randomIndexes.size() < numberOfImages) {
            int idx = random.nextInt(totalImages);
            if (!randomIndexes.contains(idx)) randomIndexes.add(idx);
        }
        LogUtils.info("🎯 Selected image indexes: " + randomIndexes);

        for (int index : randomIndexes) {
            try {
                WebElement img = allImages.get(index);
                WebUI.scrollToElement(img);
                WebUI.moveToElement(img);

                WebElement article = img.findElement(By.xpath("./ancestor::article"));
                WebElement likeBtn = article.findElement(By.xpath(".//button[@title='Like']"));

                WebUI.moveToElement(likeBtn);
                WebUI.clickElement(likeBtn);

                String src = img.getAttribute("src");
                clickedImageSrcs.add(src);
                clickedImageIndexes.add(index);

                LogUtils.info("💖 Liked image index=" + index + " | src=" + src);

            } catch (Exception e) {
                LogUtils.error("⚠️ Error clicking image index " + index + ": " + e.getMessage());
            }
        }

        LogUtils.info("🎉 Done clicking " + clickedImageIndexes.size() + " random images");
    }

    public void clickOnImageProfile()
    {
        WebUI.clickElement(imageProfile);
    }

    // Scenario 4

    public void clickOnYourCollections() {
        WebUI.clickElement(optionYourCollections);
    }


    public void clickRandomCollections(int numberOfImages) {

        By imageLocator = By.xpath("//article//img[contains(@src,'pexels.com')]");

        LogUtils.info("🚀 Loading images on Pexels...");

        int prevCount = 0;
        int tries = 0;
        while (tries < 1) {
            WebUI.scrollToBottom();
            WebUI.sleep(1);
            List<WebElement> imgs = WebUI.getWebElements(imageLocator);
            if (imgs.size() > prevCount) {
                prevCount = imgs.size();
            } else {
                break;
            }
            tries++;
        }

        List<WebElement> initialImages = WebUI.getWebElements(imageLocator);
        int totalImages = initialImages.size();
        LogUtils.info("🖼️ Total loaded images: " + totalImages);

        if (totalImages == 0) {
            LogUtils.error("❌ No images found on Pexels page.");
            return;
        }

        numberOfImages = Math.min(numberOfImages, totalImages);
        List<Integer> randomIndexes = new ArrayList<>();
        Random random = new Random();
        while (randomIndexes.size() < numberOfImages) {
            int idx = random.nextInt(totalImages);
            if (!randomIndexes.contains(idx)) randomIndexes.add(idx);
        }
        LogUtils.info("🎯 Selected image indexes (List Index): " + randomIndexes + 1);

        for (int listIndex : randomIndexes) {
            int xpathIndex = listIndex + 1;

            By specificImageLocator = By.xpath("(//article//img[contains(@src,'pexels.com')])[" + xpathIndex + "]");

            try {
                WebElement img = WebUI.getWebElement(specificImageLocator);

                WebUI.scrollToElement(img);
                WebUI.moveToElement(img);

                WebElement article = img.findElement(By.xpath("./ancestor::article"));
                WebElement collectionBtn = article.findElement(By.xpath(".//button[@title='Collect']"));

                WebUI.scrollToElement(collectionBtn);
                WebUI.moveToElement(collectionBtn);
                WebUI.clickElement(collectionBtn);

                WebUI.clickElement(optionMyCollection);
                WebUI.clickElement(buttonClose);

                WebUI.sleep(0.5);

                String src = img.getAttribute("src");
                clickedImageSrcs.add(src);
                clickedImageIndexes.add(listIndex);

                LogUtils.info("🖼️ Collection image index=" + listIndex + " | src=" + src);

            } catch (Exception e) {
                LogUtils.error("⚠️ Error clicking image index " + listIndex + ": " + e.getMessage());
            }
        }

        LogUtils.info("🎉 Done clicking " + clickedImageIndexes.size() + " random images");
    }

    // Scenario 5

    public void clickOnFreeDownload()
    {
        WebUI.clickElement(buttonFreeDownload);
    }























}
