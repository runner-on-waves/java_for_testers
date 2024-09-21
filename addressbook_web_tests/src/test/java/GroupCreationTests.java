import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {
    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup("new name", "new header","new footer");
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup("","","");
    }

}
