package lesson2.addressbook.tests;

import lesson2.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("Group name", "Group header", "Group footer"));
        }
    }

    @Test
    public void testGroupDeletion() {

        List<GroupData> before = app.group().list();

        int index = before.size() - 1;

        app.group().delete(index);


        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);

        Comparator<? super GroupData> byId = (group1, group2) -> Integer.compare(group1.getId(), group2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);

    }
}