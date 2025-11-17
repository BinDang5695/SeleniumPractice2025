package test.ui.pexelstestcases;

import org.testng.annotations.Test;
import test.ui.common.BaseTest;

public class ATEPractice extends BaseTest {

    @Test
    public void testScenario1() {
        loginPexelsPage().login();
        loginPexelsPage().clickOnPexelsIcon();
        imagePage().clickOnImageByIndex(1);
        imagePage().clickOnButtonFollow();
        imagePage().verifyButtonFollowChangeToFollowing();
        imagePage().clickFollowingToUnfollow();
    }

    @Test
    public void testScenario2() {
        loginPexelsPage().login();
        profilePage().uploadImageAndUpdateProfile();
        profilePage().verifyNewAvatarUploaded();
    }

    @Test
    public void testScenario3() {
        loginPexelsPage().login();
        loginPexelsPage().clickOnPexelsIcon();
        imagePage().clickLikeRandomImages(3);
        imagePage().clickOnImageProfile();
        imagePage().clickOnYourCollections();
        profilePage().clickOnYourLikes();
        collectionsPage().verifyLikedRandomImages();
        collectionsPage().clickToDisliked();
    }

    @Test
    public void testScenario4() {
        loginPexelsPage().login();
        loginPexelsPage().clickOnPexelsIcon();
        imagePage().clickRandomCollections(2);
        imagePage().clickOnImageProfile();
        imagePage().clickOnYourCollections();
        profilePage().clickOnMyCollection();
        collectionsPage().removeImage2FromCollection();
        collectionsPage().verifyRemainingRandomImagesInCollection();
        collectionsPage().removeImage1FromCollectionAfterVerified();
    }

    @Test
    public void testScenario5() {
        loginPexelsPage().login();
        loginPexelsPage().clickOnPexelsIcon();
        imagePage().clickOnImageByIndex(1);
        imagePage().clickOnFreeDownload();
        profilePage().goToDownloadHistoryPage();
        collectionsPage().verifyDownloadImages("pexels-cileklipalet-34299175.jpg");
    }



}
