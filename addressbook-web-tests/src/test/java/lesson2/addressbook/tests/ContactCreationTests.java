package lesson2.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.Contacts;
import lesson2.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @BeforeMethod
    //Проверяем существует ли группа для добавления контакта в следующем шаге
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData()
                    .withName("Group Name"));
        }
    }


    @Test
    //Добавляем контакт
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Anton")
                .withLastname("Alekseev")
                .withAddress("SPb")
                .withMobile("+79119004004")
                .withEmail("anton.v.alekseev@yandex.ru")
                .withGroup("Group Name");
        app.contact().create(contact);
        app.goTo().gotoHomePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((cont) -> cont.getId()).max().getAsInt()))));
    }

}