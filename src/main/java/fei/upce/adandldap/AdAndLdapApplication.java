package fei.upce.adandldap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class AdAndLdapApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AdAndLdapApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8085"));
        app.run(args);
    }

}
