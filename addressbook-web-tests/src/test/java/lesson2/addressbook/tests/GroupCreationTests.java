package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();

        app.group().initGroupCreation();

        GroupData group = new GroupData("Group name", "Group header", "Group footer");

        app.group().fillGroupForm(group);
        app.group().submitGroupCreation();
        app.group().returnToGroupPage();

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(group);

        Comparator<? super GroupData> byId = (group1, group2) -> Integer.compare(group1.getId(), group2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}