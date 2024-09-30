package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{
    @Test
    void canModifyGroup(){
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "new name", "new header", "new footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
