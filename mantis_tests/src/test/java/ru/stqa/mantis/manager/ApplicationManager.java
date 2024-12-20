package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String browser;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private  RegistrationHelper registrationHelper;
    private  JamesApiHelper jamesApi;
    private  DeveloperMailHelper developerMailHelper;
    private  RestApiHelper restApiHelper;
    private SoapApiHelper soapApiHelper;

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;

    }

    public WebDriver driver(){
        if (driver == null){
            if ("firefox".equals(browser)){
                driver = new FirefoxDriver();
            } else if("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1075, 824));
        }
        return driver;
    }

    public HttpSessionHelper http() {
        if(httpSessionHelper == null){
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper jamesCli() {
        if(jamesCliHelper == null){
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }
    public MailHelper mail() {
        if(mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesApiHelper jamesApi() {
        if(jamesApi == null){
            jamesApi = new JamesApiHelper(this);
        }
        return jamesApi;
    }

    public RegistrationHelper signup() {
        if(registrationHelper == null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public DeveloperMailHelper developerMail() {
        if(developerMailHelper == null){
            developerMailHelper = new DeveloperMailHelper(this);
        }
        return developerMailHelper;
    }

    public String property(String name){
        return properties.getProperty(name);
    }

    public RestApiHelper rest() {

        if(restApiHelper == null){
            restApiHelper = new RestApiHelper(this);
        }
        return restApiHelper;
    }

    public SoapApiHelper soap() {

        if(soapApiHelper == null){
           soapApiHelper = new SoapApiHelper(this);
        }
        return soapApiHelper;
    }
}
