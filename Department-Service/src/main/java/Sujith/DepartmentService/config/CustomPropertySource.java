//package Sujith.DepartmentService.config;
//
//import org.springframework.core.env.PropertiesPropertySource;
//import org.springframework.core.io.FileSystemResource;
//
//import java.io.IOException;
//import java.util.Properties;
//
//public class CustomPropertySource extends PropertiesPropertySource
//{
//    private static final String PROPERTY_FILE_PATH="custom.properties";
//
//    private Properties properties;
//
//    public CustomPropertySource() throws IOException
//    {
//        super("customPropertySource",loadProperties());
//        this.properties=loadProperties();
//    }
//
//    private static Properties loadProperties() throws IOException {
//
//        Properties properties = new Properties();
//
//
//        FileSystemResource resource = new FileSystemResource(PROPERTY_FILE_PATH);
//        System.out.println(resource);
//        if (resource.exists()) {
//            System.out.println(resource.getInputStream().toString()+" properties");
//            properties.load(resource.getInputStream());
//        } else {
//            throw new IOException("Custom properties file not found: " + PROPERTY_FILE_PATH);
//        }
//        return properties;
//    }
//
//    @Override
//    public Object getProperty(String name) {
//        return properties.getProperty(name);
//    }
//
//}
