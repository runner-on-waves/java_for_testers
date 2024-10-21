package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import tests.TestBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
    @Parameter(names = {"--type", "-t"})
    private String type;

    @Parameter(names = {"--output", "-o"})
    private String output;

    @Parameter(names = {"--format", "-f"})
    private String format;

    @Parameter(names = {"--count", "-c"})
    private int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }


    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateData(Supplier<Object> dataSupplier){
        var result = new ArrayList<Object>();
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
        }

    private Object generateGroups() {
        return generateData(()->new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString( 10)));
    }

    private Object generateContacts() {
        return generateData(()-> new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString( 10))
                    .withPhoto(TestBase.randomFile("src/test/resources/images"))
                    .withMobilePhone(CommonFunctions.randomString(10))
                    .withEmail(CommonFunctions.randomString(10))
                    .withNickName(CommonFunctions.randomString(10))
                    .withCompany(CommonFunctions.randomString( 10)));
    }


    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper(); // create once, reuse
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            //mapper.writeValue(new File(output), data);
            var json = mapper.writeValueAsString(data);

            try (var writer = new FileWriter(output)) { //try with resources, вызов writer.close(); не нужен
                writer.write(json);
            }
        }
        else if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        }
        else if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}
