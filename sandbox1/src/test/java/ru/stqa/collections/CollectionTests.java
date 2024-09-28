package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {

    @Test
        // Массив имеет фиксированный размер, добавить элементы нельзя
    void arrayTests() {
        var array = new String[]{"a", "b", "c"};
        var array1 = new String[3];
        Assertions.assertEquals(3, array1.length);
        Assertions.assertEquals("a", array[0]);
        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listTests() {
        //var list1 = List.of("a", "b", "c"); // неизменяемый список
        var list = new ArrayList<>(List.of("a", "b", "c")); // на базе неизменяемого списка создается изменяемый
        // добавление элементов в список
        //list.add("a");
        //list.add("b");
        //list.add("c");
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));
        list.set(0, "d"); //изменение значения элемента списка
        Assertions.assertEquals("d", list.get(0));
    }
}
