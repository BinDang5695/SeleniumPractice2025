package test.ui.pixabaytestcases;

import settings.keywords.WebUI;
import test.ui.common.BaseTest;
import org.testng.annotations.Test;
import test.ui.pixabaypages.HomePage;
import test.ui.pixabaypages.LibraryPage;

public class ATEPractice extends BaseTest {

    @Test
    public void testScenario1() {
        loginPixabay().login();
        homePage().clickOnImageByIndex(3);
        imageDetailsPage().clickOnButtonFollow();
        imageDetailsPage().verifyButtonFollowChangeToFollowing();
        imageDetailsPage().clickFollowingToUnfollow();
    }

    @Test
    public void testScenario2() {
        loginPixabay().login();
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
        loginPixabay().login();
        int[] imageIndexes = {3, 5, 7};
        homePage().clickImagesByAction(imageIndexes, "like");
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().clickOnTabLikes();
        libraryPage().verifyImages(imageIndexes, "liked");
        libraryPage().clickToDisliked();
    }

    @Test
    public void testScenario4() {
        loginPixabay().login();
        homePage().clickImagesByActionRandomly(3, "like");
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().clickOnTabLikes();
        libraryPage().verifyImagesAfterRandomLiked(homePage(), "like");
        libraryPage().clickToDisliked();
    }

    @Test
    public void testScenario5() {
        loginPixabay().login();
        int[] imageIndexes = {3, 5};
        int[] imageIndexesAfterRemovedImage = {3};
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().createNewCollection();
        libraryPage().clickOnCreatedCollection();
        libraryPage().clickOnButtonFindMedia();
        homePage().clickImagesByAction(imageIndexes, "bookmark");
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().clickOnCreatedCollection();
        libraryPage().removeImage2FromCollection();
        libraryPage().verifyImages(imageIndexesAfterRemovedImage, "collection");
        libraryPage().deleteCollectionAfterVerified();
    }

    @Test
    public void testScenario6() {
        loginPixabay().login();
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().createNewCollection();
        libraryPage().clickOnCreatedCollection();
        libraryPage().clickOnButtonFindMedia();
        homePage().clickImagesByActionRandomly(2, "addtocollection");
        homePage().clickOnImageProfile();
        homePage().clickOnOptionLibrary();
        libraryPage().clickOnCreatedCollection();
        libraryPage().removeImage2FromCollection();
        libraryPage().verifyRemainingImageByColumnAfterRemove(1);
        libraryPage().deleteCollectionAfterVerified();
    }

    @Test
    public void testScenario7() {
        loginPixabay().login();
        homePage().clickOnImageByIndex(3);
        imageDetailsPage().downloadImage();
        libraryPage().goToDownloadHistoryPage();
        libraryPage().verifyDownloadImages("sparrow-9617024_1280.jpg");
    }
}
