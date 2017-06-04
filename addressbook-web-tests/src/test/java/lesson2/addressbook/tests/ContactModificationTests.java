package lesson2.addressbook.tests;

import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        //Проверяем существует ли группа для добавления контакта в следующем шаге
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("Group name", "Group header", "Group footer"));
        }

        //Проверяем есть ли контакт для модификации
        app.getNavigationHelper().gotoHomePage();

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().gotoAddContactFormPage();
            app.getContactHelper().createContact(new ContactData("Anton", "Alekseev", "SPb", "+79119004004", "anton.v.alekseev@yandex.ru", "Group name"));
        }

        //Редактируем контакт
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Anton(edited)", "Alekseev(edited)", "SPb(edited)", "+79119004004(edited)", "anton.v.alekseev@yandex.ru(edited)", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();

        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        Comparator<? super ContactData> byId = (contact1, contact2) -> Integer.compare(contact1.getId(), contact2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
