package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import lesson2.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class RemoveContactFromGroupsTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Contacts contacts = app.db().contacts();
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

        for (ContactData contact : contacts) {
            if (contact.getGroups().isEmpty()) {
                app.goTo().gotoHomePage();
                ContactData modifiedContact = contacts.iterator().next();
                app.contact().selectContactById(modifiedContact.getId());
                app.contact().addContactToGroup(modifiedContact.getId());
            }
        }

    }

    @Test
    public void testRemoveContactFromGroup() {
        app.goTo().gotoHomePage();
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.goTo().gotoHomePage();
        for (GroupData group : groups) {
            app.contact().selectDeletedGroupFromList(group);
        }
        Contacts contacts = app.db().contacts();
        ContactData initContact = app.db().contactById(contacts.iterator().next().getId());
        app.goTo().gotoHomePage();
        app.contact().deleteContactFromGroup(initContact);
        Contacts after = app.db().contacts();
        ContactData contactFromDb = app.db().contactById(initContact.getId());
        assertThat(after, equalTo(before.withOut(initContact).withAdded(contactFromDb)));
    }
}