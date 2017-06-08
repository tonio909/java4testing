package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("Group name", "Group header", "Group footer"));
        }
    }

    @Test
    public void testGroupModification() {

        List<GroupData> before = app.group().list();

        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(), "Group name (edited)", "Group header (edited)", "Group footer (edited)");
        app.group().modify(index, group);

        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);

        Comparator<? super GroupData> byId = (group1, group2) -> Integer.compare(group1.getId(), group2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}