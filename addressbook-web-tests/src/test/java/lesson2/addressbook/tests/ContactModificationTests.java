package lesson2.addressbook.tests;

import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        //Проверяем существует ли группа для добавления контакта в следующем шаге
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("Group name", "Group header", "Group footer"));
        }

        //Проверяем есть ли контакт для модификации
        app.getContactHelper().gotoContactPage();

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anton", "Alekseev", "SPb", "+79119004004", "anton.v.alekseev@yandex.ru", "Group name"));
        }

        //Редактируем контакт
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Anton(edited)", "Alekseev(edited)", "SPb(edited)", "+79119004004(edited)", "anton.v.alekseev@yandex.ru(edited)", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
    }

}
