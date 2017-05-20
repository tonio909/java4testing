package lesson2.addressbook.tests;

import lesson2.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification() {

        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Anton(edited)", "Alekseev(edited)", "SPb(edited)", "+79119004004(edited)", "anton.v.alekseev@yandex.ru(edited)"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
    }

}
