package com.servicedesk.statrter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.servicedesk")
@EnableJpaRepositories("com.servicedesk.repository")
@EntityScan("com.servicedesk.model")
public class ServiceDeskStarter {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDeskStarter.class, args);
    }

}