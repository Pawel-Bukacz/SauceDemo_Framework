package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.SidebarPage;


@Log4j2
public class SidebarStep {

    WebDriver driver;

    public SidebarStep(WebDriver driver) {
        this.driver = driver;
    }

    public void openAllItems(SidebarPage sidebarPage) {
        log.info("Click 'All Items' button");
        sidebarPage
                .openMenu()
                .clickAllItems();
    }

    public void openAbout(SidebarPage sidebarPage) {
        log.info("Click 'About' button");
        sidebarPage
                .openMenu()
                .clickAbout();
    }

    public void logout(SidebarPage sidebarPage) {
        log.info("Click 'Logout' button");
        sidebarPage
                .openMenu()
                .clickLogout();
    }

    public void resetAppState(SidebarPage sidebarPage) {
        log.info("Click 'Reset' button");
        sidebarPage
                .openMenu()
                .clickResetAppState();
    }
}