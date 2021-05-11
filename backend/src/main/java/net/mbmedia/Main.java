package net.mbmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Collections;

@SpringBootApplication
@EnableScheduling
public class Main {

    public static void main(String[] args){
        SpringApplication app = new SpringApplication(Main.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", Konfiguration.PORT));
        app.run(args);
    }
}
