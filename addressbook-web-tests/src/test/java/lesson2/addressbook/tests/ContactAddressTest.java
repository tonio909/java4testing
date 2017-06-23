package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import lesson2.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().isEmpty()) {
            app.group().create(new GroupData()
                    .withName("Group Name"));
        }
        app.goTo().gotoHomePage();
        if (app.contact().all().isEmpty()) {
            app.contact().create(new ContactData()
                    .withFirstname("Anton")
                    .withLastname("Alekseev")
                    .withAddress("SPb")
                    .withMobilephone("+79119004004")
                    .withHomephone("+78121112233")
                    .withWorkphone("+78129998877")
                    .withEmail("anton.v.alekseev@yandex.ru")
                    .withEmail2("anton-v-alekseev@yandex.ru")
                    .withEmail3("anton.v.alekseev@ya.ru")
                    .withGroup("Group Name"));
        }
    }

    @Test
    public void testContactAddess() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

}
