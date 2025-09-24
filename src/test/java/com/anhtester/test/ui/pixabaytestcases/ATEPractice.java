package com.anhtester.test.ui.pixabaytestcases;

import com.anhtester.test.ui.common.BaseTest;
import org.testng.annotations.Test;

public class ATEPractice extends BaseTest {

    @Test
    public void testScenario1() {
        loginPixabayPage().login();
        homePage().clickOnFirstImage();
        imageDetailsPage().clickOnButtonFollow();
        imageDetailsPage().verifyButtonFollowChangeToFollowing();
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
        homePage().clickOnHeartImage1();
        homePage().clickOnHeartImage2();
        homePage().clickOnHeartImage3();
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().clickOnTabLikes();
        libraryPage().verifyLikedImages();
    }

    @Test
    public void testScenario4() {
        loginPixabayPage().login();
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().createNewCollection();
        libraryPage().clickOnCreatedCollection();
        libraryPage().clickOnButtonFindMedia();
        homePage().addImage1ToCollection();
        homePage().addImage2ToCollection();
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
