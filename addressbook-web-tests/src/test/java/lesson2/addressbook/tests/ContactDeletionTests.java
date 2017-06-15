package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        //Проверяем существует ли группа для добавления контакта в следующем шаге
        app.goTo().groupPage();
        if (app.group().all().isEmpty()) {
            app.group().create(new GroupData()
                    .withName("Group Name"));
        }
        //Проверяем есть ли контакт для удаления
        app.goTo().gotoHomePage();
        if (app.contact().all().isEmpty()) {
            app.contact().create(new ContactData()
                    .withFirstname("Anton")
                    .withLastname("Alekseev")
                    .withAddress("SPb")
                    .withMobilephone("+79119004004")
                    .withEmail("anton.v.alekseev@yandex.ru")
                    .withGroup("Group Name"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}