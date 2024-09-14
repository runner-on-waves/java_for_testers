package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void cannotCreateRectangleWithNegativeSideA(){
        try {
            new Rectangle(-5, 6);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            // OK
        }
    }
    @Test
    void cannotCreateRectangleWithNegativeSideB(){
        try {
            new Rectangle(5, -6);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            // OK
        }
    }
}
