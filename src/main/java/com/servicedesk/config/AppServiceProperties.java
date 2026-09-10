package com.servicedesk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.service")
public class AppServiceProperties {

    private String currency;
    private Double baseHandlingFee;
    private Integer maxActiveTicketsPerCustomer;
}
