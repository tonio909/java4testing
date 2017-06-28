package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import lesson2.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {

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
                    .withLastname("Alekseev"));
        }

        if (app.db().contactsAreNotInGroup().isEmpty()) {
            app.contact().create(new ContactData()
                    .withFirstname("Anton")
                    .withLastname("Alekseev"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        Contacts before = app.db().contacts();
        app.goTo().gotoHomePage();
        Groups group = app.db().groups();
        ContactData modifiedContact = app.db().contactsAreNotInGroup().iterator().next();
        GroupData addedGroup = group.iterator().next();
        app.contact().clickOnContactCheckbox(modifiedContact.getId());
        app.contact().addContactToGroup(addedGroup.getId());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(modifiedContact.inGroup(addedGroup))));
    }
}