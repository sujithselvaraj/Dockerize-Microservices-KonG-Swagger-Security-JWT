//package Sujith.DepartmentService.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.ConfigurableEnvironment;
//
//import java.io.IOException;
//
//@Configuration
//public class Jwtconfig {
//
////    @Autowired
////    private ConfigurableEnvironment environment;
//
//    private Long expirationTime;
//
//    public Long getExpirationValidity() throws IOException {
//        CustomPropertySource customPropertySource=new CustomPropertySource();
//
//        expirationTime=Long.parseLong((String) customPropertySource.getProperty("custom.expirationTime"));
//        System.out.println("expirationTime ="+expirationTime);
//
//        return expirationTime;
//    }
//}