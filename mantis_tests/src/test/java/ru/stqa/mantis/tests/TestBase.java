package ru.stqa.mantis.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.mantis.manager.ApplicationManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

public class TestBase {

    protected static ApplicationManager app;

    static String getUrl(String text) {
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = "";
        if(matcher.find()){
           url = text.substring(matcher.start(), matcher.end());
        }
        return url;
    }

    @BeforeEach
    public void setUp() throws IOException {
        var properties = new Properties();
        properties.load(new FileReader(System.getProperty("target", "local.properties")));
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "firefox"), properties);

    }
}
