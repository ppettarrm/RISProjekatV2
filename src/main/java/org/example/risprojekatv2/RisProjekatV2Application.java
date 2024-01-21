package org.example.risprojekatv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.risprojekatv2.repositories")
public class RisProjekatV2Application {

    public static void main(String[] args) {
        SpringApplication.run(RisProjekatV2Application.class, args);
    }

}
