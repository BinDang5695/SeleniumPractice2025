package test.ui.crmpages;
import org.openqa.selenium.By;
import settings.keywords.WebUI;

public class HeaderPage extends BasePage {

        private By iconUser = By.xpath("//img[contains(@class,'staff-profile-image')]");
        private By buttonLogout = By.xpath("//ul[contains(@class,'dropdown')]//a[text()='Logout']");

        public void openUserMenu() {
            WebUI.clickElement(iconUser);
        }

        public void logout() {
            openUserMenu();
            WebUI.clickElement(buttonLogout);
        }

}
