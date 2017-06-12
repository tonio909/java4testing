package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import lesson2.addressbook.model.Groups;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();

        GroupData group = new GroupData()
                .withName("Group Name")
                .withHeader("Group Header")
                .withFooter("Group Footer");


        app.group().create(group);

        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((gr) -> gr.getId()).max().getAsInt()))));
    }


    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();

        GroupData group = new GroupData()
                .withName("Group Name ' Bad")
                .withHeader("Group Header")
                .withFooter("Group Footer");


        app.group().create(group);

        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();

        assertThat(after, equalTo(before));
    }
}