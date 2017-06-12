package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import lesson2.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().isEmpty()) {
            app.group().create(new GroupData()
                    .withName("Group Name"));
        }
    }

    @Test
    public void testGroupModification() {

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("Group name (edited)")
                .withHeader("Group header (edited)")
                .withFooter("Group footer (edited)");
        app.group().modify(group);

        Groups after = app.group().all();

        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.withoutAdded(modifiedGroup).withAdded(group)));
    }

}