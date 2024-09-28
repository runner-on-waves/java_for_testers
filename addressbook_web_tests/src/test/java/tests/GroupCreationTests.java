package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {
    @Test
    public void canCreateGroup() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("new name", "new header", "new footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount+1, newGroupCount);
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("pokemon"));
    }

}
