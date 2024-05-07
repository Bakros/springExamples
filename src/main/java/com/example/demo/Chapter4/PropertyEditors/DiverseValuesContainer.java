package com.example.demo.Chapter4.PropertyEditors;


import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by iuliana.cosmina on 26/03/2022
 */

/**
 * This class only store 2 properties: A List of String and an InputStream. Those will be used
 * by the following class using the @Value("#{valuesHolder.stringList}) to configure a default value.
 */
@Component
class ValuesHolder {

    List<String> stringList;
    InputStream inputStream;

    public ValuesHolder() {
        this.stringList = List.of("Mayer", "Psihoza", "Mazikeen");

        try {
            String dir = System.getProperty("java.io.tmpdir")
                    + System.getProperty("file.separator")
                    + "test.txt";
            System.out.println(dir);

            this.inputStream = new FileInputStream(
                    System.getProperty("java.io.tmpdir")
                            + System.getProperty("file.separator")
                            + "test.txt"
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // we are not interested in the exception that much
        }


    }

    public List<String> getStringList() {
        return stringList;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}

@Component("builtInSample")
public class DiverseValuesContainer {
    private byte[] bytes;                 // ByteArrayPropertyEditor
    private Character character;          //CharacterEditor
    private Class<?> cls;                    // ClassEditor
    private Boolean trueOrFalse;          // CustomBooleanEditor
    private List<String> stringList;      // CustomCollectionEditor
    private Date date;                    // CustomDateEditor
    private Float floatValue;             // CustomNumberEditor
    private File file;                    // FileEditor
    private InputStream stream;           // InputStreamEditor
    private Locale locale;                // LocaleEditor
    private Pattern pattern;              // PatternEditor
    private Properties properties;        // PropertiesEditor
    private String trimString;            // StringTrimmerEditor
    private URL url;                      // URLEditor

    private static Logger LOGGER = LoggerFactory.getLogger(DiverseValuesContainer.class);

    @Value("A")
    public void setCharacter(Character character) {
        LOGGER.info("Setting character: {}", character);
        this.character = character;
    }

    @Value("java.lang.String")
    public void setCls(Class<?> cls) {
        LOGGER.info("Setting class: {}" , cls.getName());
        this.cls = cls;
    }

    @Value("#{systemProperties['java.io.tmpdir']}#{systemProperties['file.separator']}test.txt")
    public void setFile(File file) {
        LOGGER.info("Setting file: {}" , file.getAbsolutePath());
        this.file = file;
    }

    @Value("en_US")
    public void setLocale(Locale locale) {
        LOGGER.info("Setting locale: {}" , locale.getDisplayName());
        this.locale = locale;
    }

    @Value("name=Ben age=41")
    public void setProperties(Properties properties) {
        LOGGER.info("Loaded {}" , properties.size() + " properties");
        this.properties = properties;
    }

    @Value("https://iuliana-cosmina.com")
    public void setUrl(URL url) {
        LOGGER.info("Setting URL: {}" , url.toExternalForm());
        this.url = url;
    }

    @Value("John Mayer")
    public void setBytes(byte... bytes) {
        LOGGER.info("Setting bytes: {}" , Arrays.toString(bytes));
        this.bytes = bytes;
    }

    @Value("true")
    public void setTrueOrFalse(Boolean trueOrFalse) {
        LOGGER.info("Setting Boolean: {}" , trueOrFalse);
        this.trueOrFalse = trueOrFalse;
    }

    @Value("#{valuesHolder.stringList}")
    public void setStringList(List<String> stringList) {
        LOGGER.info("Setting stringList with: {}" , stringList);
        this.stringList = stringList;
    }

    @Value("20/08/1981")
    public void setDate(Date date) {
        LOGGER.info("Setting date: {}" , date);
        this.date = date;
    }

    @Value("123.45678")
    public void setFloatValue(Float floatValue) {
        LOGGER.info("Setting float value: {}" , floatValue);
        this.floatValue = floatValue;
    }

    @Value("#{valuesHolder.inputStream}")
    public void setStream(InputStream stream) {
        this.stream = stream;
        LOGGER.info("Setting stream & reading from it: {}" ,
                new BufferedReader(new InputStreamReader(stream)).lines().parallel().collect(Collectors.joining("\n")));
    }

    @Value("a*b")
    public void setPattern(Pattern pattern) {
        LOGGER.info("Setting pattern: {}" , pattern);
        this.pattern = pattern;
    }

    @Value("   String need trimming   ")
    public void setTrimString(String trimString) {
        LOGGER.info("Setting trim string: {}" , trimString);
        this.trimString = trimString;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public Character getCharacter() {
        return character;
    }

    public Class<?> getCls() {
        return cls;
    }

    public Boolean getTrueOrFalse() {
        return trueOrFalse;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public Date getDate() {
        return date;
    }

    public Float getFloatValue() {
        return floatValue;
    }

    public File getFile() {
        return file;
    }

    public InputStream getStream() {
        return stream;
    }

    public Locale getLocale() {
        return locale;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getTrimString() {
        return trimString;
    }

    public URL getUrl() {
        return url;
    }

    @PreDestroy
    public void amIDying(){
        LOGGER.info("Yes you are dying");
    }

    public static void main(String... args) throws Exception {

        /**
         * This section of code only generate a file called text.txt on the following directory
         * C:\Users\Seba\AppData\Local\Temp\\test.txt
         * It should be deleted when the JVM ends its job, but it doesn't get deleted.
         */
        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        Path path = Files.createFile(Path.of(baseDir.getAbsolutePath(), "test.txt"));
        Files.writeString(path, "Hello World!");
        path.toFile().deleteOnExit();

        /**
         * This section of the code instantiate as an object / JavaBean the clases ValueHolder and DiverseValueContainer
         */
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(ValuesHolder.class, DiverseValuesContainer.class);
        ctx.refresh();




        ctx.close();
    }

}