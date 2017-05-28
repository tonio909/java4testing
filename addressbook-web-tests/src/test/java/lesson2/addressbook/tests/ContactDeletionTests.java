package lesson2.addressbook.tests;

import lesson2.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anton1", "Alekseev1", "SPb", "+79119004004", "anton.v.alekseev@yandex.ru", "test1"), true);
        }

        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().acceptAlert();

    }

}
