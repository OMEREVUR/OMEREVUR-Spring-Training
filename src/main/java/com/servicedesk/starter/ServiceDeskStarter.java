package com.servicedesk.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.servicedesk")
@EnableJpaRepositories("com.servicedesk.repository")
@EntityScan("com.servicedesk.model")
@ConfigurationPropertiesScan("com.servicedesk.config")
public class ServiceDeskStarter {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDeskStarter.class, args);
    }

}
