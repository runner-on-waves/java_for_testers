import java.io.File;

public class Hello {
    public static void main(String[] args) {
        try {
            var z = calculate();
            System.out.println(z);
            System.out.println("Hello, world!");
        } catch (ArithmeticException exception) {
            //System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        /* var configFile = new File("sandbox1/build.gradle");
        System.out.println(configFile.exists());
        System.out.println(configFile.getAbsolutePath());*/
    }

    private static int calculate() {
        var x = 1;
        var y = 1;
        var z = divide(x, y);
        return z;
    }

    private static int divide(int x, int y) {
        var z = x / y;
        return z;
    }
}
