package lesson2.addressbook.tests;

import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {

        //Проверяем существует ли группа для добавления контакта в следующем шаге
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("Group name", "Group header", "Group footer"));
        }

        //Проверяем есть ли контакт для удаления
        app.getContactHelper().gotoAddContactFormPage();

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anton", "Alekseev", "SPb", "+79119004004", "anton.v.alekseev@yandex.ru", "Group name"));
        }

        //Удаляем контакт
        //app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().acceptAlert();

    }

}
