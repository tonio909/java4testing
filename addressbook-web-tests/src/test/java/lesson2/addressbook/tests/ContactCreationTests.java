package lesson2.addressbook.tests;

import lesson2.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testsContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Anton1", "Alekseev1", "SPb", "+79119004004", "anton.v.alekseev@yandex.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomepage();

    }

}