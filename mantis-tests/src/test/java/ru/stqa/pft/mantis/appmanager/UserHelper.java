package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;


public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void startResetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public UserData getUserWithSpecificLogin() {
        Users users = app.db().getAllUsersList();
        return users.stream().filter((user) -> user.getUsername().startsWith("user")).iterator().next();
    }

    public UserData getUserById(int id) {
        Users users = app.db().getAllUsersList();
        return users.stream().filter((user) -> user.getId() == id).findAny().get();
    }
}