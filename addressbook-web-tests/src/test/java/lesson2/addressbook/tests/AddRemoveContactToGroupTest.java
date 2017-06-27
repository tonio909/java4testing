package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import lesson2.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.Contacts;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddRemoveContactToGroupTest extends TestBase {

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
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().gotoHomePage();
        Contacts before = app.db().contacts();
        ContactData rowWithContact = null;
        List<ContactData> list = new ArrayList<ContactData>(before);

        for (int i = 0; i < list.size(); i++) {
                rowWithContact = list.get(i);
        }

        app.contact().contactToGroup(rowWithContact);
        app.goTo().gotoHomePage();
        Contacts after = app.db().contacts();
        ContactData contactFromDb = app.db().contactById(rowWithContact.getId());
        assertThat(after, equalTo(before.withOut(rowWithContact).withAdded(contactFromDb)));
    }


    @Test
    public void testRemoveContactFromGroup(){
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.goTo().gotoHomePage();
        for (GroupData group: groups){
            app.contact().selectDeletedGroupFromList(group);
        }
        Contacts contacts = app.contact().all();
        ContactData initContact = app.db().contactById(contacts.iterator().next().getId());
        app.contact().deleteContactFromGroup(initContact);
        app.goTo().gotoHomePage();
        Contacts after = app.db().contacts();
        ContactData contactFromDb = app.db().contactById(initContact.getId());
        assertThat(after, equalTo(before.withOut(initContact).withAdded(contactFromDb)));
    }
}
