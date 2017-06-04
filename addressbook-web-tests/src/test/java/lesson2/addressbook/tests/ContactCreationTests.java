package lesson2.addressbook.tests;

import lesson2.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test

    public void testsContactCreation() {

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().gotoContactPage();

        ContactData contact = new ContactData("Anton", "Alekseev", "SPb", "+79119004004", "anton.v.alekseev@yandex.ru", "Group name");
        app.getContactHelper().fillContactForm(contact,true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomepage();

        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);

        Comparator<? super ContactData> byId = (contact1, contact2) -> Integer.compare(contact1.getId(), contact2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}