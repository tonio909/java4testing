package lesson2.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.Contacts;
import lesson2.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    //Проверяем существует ли группа для добавления контакта в следующем шаге
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().isEmpty()) {
            app.group().create(new GroupData()
                    .withName("Group Name"));
        }
    }

    @Test
    //Добавляем контакт
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/img0.jpg");
        ContactData contact = new ContactData()
                .withFirstname("Anton")
                .withLastname("Alekseev")
                .withAddress("SPb")
                .withMobilephone("+79119004004")
                .withEmail("anton.v.alekseev@yandex.ru")
                .withGroup("Group Name")
                .withPhoto(photo);
        app.contact().create(contact);
        app.goTo().gotoHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((cont) -> cont.getId()).max().getAsInt()))));
    }


    @Test (enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/img0.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}