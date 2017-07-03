package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangeUserPasswordByAdmin() throws IOException, MessagingException {
        app.registration().goToLoginPage();
        app.getUserHelper().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"));
        app.registration().goToManageUserPage();
        String newPass = app.getProperty("web.userNewPass");
        UserData UserToChangePassword = app.getUserHelper().getUserWithSpecificLogin();
        app.registration().goToUserPage(UserToChangePassword.getId());
        app.getUserHelper().startResetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, UserToChangePassword.getEmail());
        app.registration().finish(confirmationLink, newPass);
        UserData user = app.getUserHelper().getUserById(UserToChangePassword.getId());
        HttpSession sessionUser = app.newSession();
        assertTrue(sessionUser.login(user.getUsername(), newPass));
        assertTrue(sessionUser.isLoggedInAs(user.getUsername()));
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}