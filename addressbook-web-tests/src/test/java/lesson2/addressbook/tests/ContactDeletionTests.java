package lesson2.addressbook.tests;

import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {

        //Проверяем существует ли группа для добавления контакта в следующем шаге
        app.goTo().groupPage();

        if (! app.group().isThereAGroup()) {
            app.group().create(new GroupData("Group name", "Group header", "Group footer"));
        }

        //Проверяем есть ли контакт для удаления
        app.goTo().gotoHomePage();


        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anton", "Alekseev", "SPb", "+79119004004", "anton.v.alekseev@yandex.ru", "Group name"));
        }

        //Удаляем контакт
        app.goTo().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().acceptAlert();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);

        Comparator<? super ContactData> byId = (contact1, contact2) -> Integer.compare(contact1.getId(), contact2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
