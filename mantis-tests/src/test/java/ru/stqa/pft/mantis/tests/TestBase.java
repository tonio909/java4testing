package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws IOException, ServiceException {
        String status = app.soap().getStatusById(issueId);
        if(status.equals("resolved") || status.equals("closed")) {
            return false;
        } else
            return true;
    }

    public void skipIfNotFixed(int issueId) throws IOException, ServiceException, SkipException {
        if (isIssueOpen(issueId)) {
            System.out.println("Ignored because of issue http://localhost/mantis/view.php?id=" + issueId +
                    " status is not Resolved or Closed." + System.getProperty("line.separator") +
                    "Please change status of issue and run test again.");
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}