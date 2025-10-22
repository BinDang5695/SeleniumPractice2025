package test.ui.pixabaypages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;
import settings.utils.LogUtils;

import java.time.Duration;
import java.util.*;

public class HomePage {

    private List<Integer> clickedImageIndexes = new ArrayList<>();
    private List<String> clickedImageSrcs = new ArrayList<>();

    private By imageProfile = By.xpath("(//div[.//img[@alt='Bin Dang']])[5]");
    private By optionSettings = By.xpath("//label[normalize-space()='Settings']");
    private By optionLibrary = By.xpath("//label[normalize-space()='Library']");
    private By buttonAdd = By.xpath("//button[contains(@class, 'addRemoveButton')]");
    private By buttonX = By.xpath("(//button[contains(@class, 'closeButton')])[6]");

    public List<Integer> getClickedImageIndexes() {
        return clickedImageIndexes;
    }

    public List<String> getClickedImageSrcs() {
        return clickedImageSrcs;
    }

//    public void clickImagesByAction(int[] imageIndexes, String actionType) {
//        clickedImageIndexes.clear();
//        clickedImageSrcs.clear();
//        for (int index : imageIndexes) {
//            clickOnImageAction(index, actionType);
//        }
//    }
//
//    public void clickOnImageAction(int imageIndex, String actionType) {
//        By image = By.xpath("(//img)[" + imageIndex + "]");
//
//        WebUI.scrollToElement(image);
//        WebUI.moveToElement(image);
//        WebUI.waitForElementVisible(image);
//
//        String actualSrc = WebUI.getAttributeElement(image, "src");
//        clickedImageSrcs.add(actualSrc);
//        clickedImageIndexes.add(imageIndex);
//
//        String ariaLabel = actionType.equalsIgnoreCase("like") ? "HeartOutline" : "BookmarkOutline";
//        By actionButton = By.xpath("(//img)[" + imageIndex + "]/ancestor::div[contains(@class,'cell--') or contains(@class,'card--')][1]"
//                + "//button[.//div[@role='img' and @aria-label='" + ariaLabel + "']]");
//
//        WebUI.waitForElementVisible(actionButton);
//        WebUI.moveToElement(actionButton);
//        WebUI.clickElement(actionButton);
//
//        if (actionType.equalsIgnoreCase("bookmark")) {
//            WebUI.clickElement(buttonAdd);
//            WebUI.clickElement(buttonX);
//            LogUtils.info("✅ Added image index " + imageIndex + " to collection successfully!");
//        } else {
//            LogUtils.info("❤️ Liked image index " + imageIndex + " successfully!");
//        }
//    }

    public void clickImagesByActionRandomly(int numberOfImages, String actionType) {

        String ariaLabel;
        boolean requiresPopupAction = false;

        switch (actionType.toLowerCase()) {
            case "like":
                ariaLabel = "HeartOutline";
                break;
            case "collection":
            case "bookmark":
            case "addtocollection":
                ariaLabel = "BookmarkOutline";
                requiresPopupAction = true;
                break;
            default:
                LogUtils.error("❌ Loại hành động không hợp lệ: " + actionType);
                return;
        }

        By imageLocator = By.xpath("//img[contains(@title, 'Download free HD stock image of')]");
        List<WebElement> allImages = WebUI.getWebElements(imageLocator);

        int totalImages = allImages.size();
        LogUtils.info("🖼️ Tổng số ảnh hợp lệ tìm thấy: " + totalImages);

        if (totalImages == 0) {
            LogUtils.error("❌ Không tìm thấy ảnh nào để thao tác.");
            return;
        }

        numberOfImages = Math.min(numberOfImages, totalImages);

        List<Integer> randomIndexes = new ArrayList<>();
        Random random = new Random();
        while (randomIndexes.size() < numberOfImages) {
            int randomIndex = random.nextInt(totalImages);
            if (!randomIndexes.contains(randomIndex)) {
                randomIndexes.add(randomIndex);
            }
        }

        LogUtils.info("🎯 Danh sách chỉ số ảnh được chọn để click: " + randomIndexes);

        clickedImageIndexes.clear();
        clickedImageSrcs.clear();

        for (int index : randomIndexes) {
            try {
                WebElement imageElement = allImages.get(index);

                WebUI.sleep(2);
                WebUI.scrollToElement(imageElement);
                WebUI.sleep(2);
                WebUI.moveToElement(imageElement);

                WebElement actionButton = imageElement.findElement(
                        By.xpath("./ancestor::div[contains(@class,'cell--') or contains(@class,'card--')][1]" +
                                "//button[.//div[@role='img' and @aria-label='" + ariaLabel + "']]")
                );

                WebUI.sleep(2);
                WebUI.moveToElement(actionButton);
                WebUI.sleep(2);
                WebUI.clickElement(actionButton);

                if (requiresPopupAction) {
                    WebUI.clickElement(buttonAdd);
                    WebUI.clickElement(buttonX);
                }

                String actualSrc = imageElement.getAttribute("src");
                clickedImageSrcs.add(actualSrc);
                clickedImageIndexes.add(index);

                LogUtils.info("✅ Click " + actionType + " thành công ảnh index=" + index +
                        " | src=" + actualSrc);

            } catch (Exception e) {
                LogUtils.error("⚠️ Lỗi khi click ảnh index " + index + ": " + e.getMessage());
            }
        }

        LogUtils.info("🎉 Hoàn tất click " + randomIndexes.size() + " ảnh ngẫu nhiên cho hành động: " + actionType);
    }

    public void clickOnImageByIndex(int index) {
        By image = By.xpath("(//img)[" + index + "]");
        WebUI.clickElement(image);
    }

    public void clickOnImageProfile() {
        WebUI.clickUntilVisible(imageProfile, optionSettings);
    }

    public void clickOnOptionSettings() {
        WebUI.moveToElement(optionSettings);
        WebUI.clickElement(optionSettings);
    }

    public void clickOnOptionLibrary() {
        WebUI.clickElement(optionLibrary);
    }


}
