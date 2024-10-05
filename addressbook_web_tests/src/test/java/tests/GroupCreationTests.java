package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import common.CommonFunctions;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
//import org.junit.jupiter.params.provider.ValueSource;
import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
        /*for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName( name).withHeader( header).withFooter(footer));
                }
            }

        }*/
        // вариант с постройчным чтением для больших файлов, выборочные данные для сохранения
//        var json = "";
//        try (var reader = new FileReader("groups.json");
//             var breader = new BufferedReader(reader)) {
//            var line = breader.readLine();
//            while (line != null) {
//                json = json + line;
//                line = breader.readLine();
//            }
//        }
        //var json = Files.readString(Paths.get("groups.json")); // чтение файла целиком
        //ObjectMapper mapper = new ObjectMapper();
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<>(List.of(new GroupData()
                .withName("group name'")
                .withHeader("")
                .withFooter("")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroups(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }

   /* Пример использования теста с источником данных из строки
    @ParameterizedTest
    @ValueSource (strings = {"group name", "group name'"})
    public void canCreateGroup(String name) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData(name, "new header", "new footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }
*/
}
