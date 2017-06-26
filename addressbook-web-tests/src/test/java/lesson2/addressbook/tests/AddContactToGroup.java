package lesson2.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.Contacts;
import lesson2.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {


    @Test
    public void testAddContactToCroup() {
        app.goTo().gotoHomePage();

        Contacts before = app.db().contacts();

        ContactData initContact = null;
        List<ContactData> list = new ArrayList<ContactData>(before);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGroups().size() != app.db().groups().size()) {
                initContact = list.get(i);
                break;
            }
        }

        app.contact().contactToGroup(initContact);
        app.goTo().gotoHomePage();

        Contacts after = app.db().contacts();

        ContactData contactFromDb = app.db().contactById(initContact.getId());
        assertThat(after, equalTo(before.without(initContact).withAdded(contactFromDb)));
    }
}
