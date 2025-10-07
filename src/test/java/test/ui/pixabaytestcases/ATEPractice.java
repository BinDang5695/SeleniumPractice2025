package test.ui.pixabaytestcases;

import test.ui.common.BaseTest;
import org.testng.annotations.Test;

public class ATEPractice extends BaseTest {

    @Test
    public void testScenario1() {
        loginPixabayPage().login();
        homePage().clickOnFirstImage();
        imageDetailsPage().clickOnButtonFollow();
        imageDetailsPage().verifyButtonFollowChangeToFollowing();
        imageDetailsPage().clickFollowingToUnfollow();
    }

    @Test
    public void testScenario2() {
        loginPixabayPage().login();
        homePage().clickOnImageProfile();
        homePage().clickOnOptionSettings();
        settingsPage().clickOnButtonEdit();
        settingsPage().uploadNewAvatar();
        settingsPage().clickOnButtonApply();
        settingsPage().clickOnButtonClose();
        settingsPage().inputInformationAndSave();
        settingsPage().verifyNewAvatarUploaded();
    }

    @Test
    public void testScenario3() {
        loginPixabayPage().login();
        homePage().clickImagesByAction(new int[]{3, 7, 11}, "like");
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().clickOnTabLikes();
        libraryPage().verifyLikedImages();
        libraryPage().clickToDisliked();
    }

    @Test
    public void testScenario4() {
        loginPixabayPage().login();
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().createNewCollection();
        libraryPage().clickOnCreatedCollection();
        libraryPage().clickOnButtonFindMedia();
        homePage().clickImagesByAction(new int[]{3, 7}, "bookmark");
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().clickOnCreatedCollection();
        libraryPage().removeImage2FromCollection();
        libraryPage().verifyImagesOnCollection();
    }

    @Test
    public void testScenario5() {
        loginPixabayPage().login();
        homePage().clickOnFirstImage();
        imageDetailsPage().downloadImage();
        libraryPage().goToDownloadHistoryPage();
        libraryPage().verifyImagesOnDownloadHistory();
    }
}
