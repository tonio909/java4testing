package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button/span"));
    }


    public void goToLoginPage() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }

    public void goToManageUserPage() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }

    public void goToUserPage(int id) {
        click(By.cssSelector("a[href=\"manage_user_edit_page.php?user_id=" + id +"\"]"));
    }


}