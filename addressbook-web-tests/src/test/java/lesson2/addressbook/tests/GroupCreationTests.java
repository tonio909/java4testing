package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import lesson2.addressbook.model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<Object[]>();

        list.add(new Object[] {new GroupData().withName("Group Name1").withHeader("Group Header1").withFooter("Group Footer1")});
        list.add(new Object[] {new GroupData().withName("Group Name2").withHeader("Group Header2").withFooter("Group Footer2")});
        list.add(new Object[] {new GroupData().withName("Group Name3").withHeader("Group Header3").withFooter("Group Footer3")});

        return list.iterator();
    }

    @Test (dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);

        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((gr) -> gr.getId()).max().getAsInt()))));
    }


    @Test (enabled = false)
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