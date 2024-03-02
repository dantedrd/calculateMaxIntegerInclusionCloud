package com.inclusioncloud.maxinteger.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource-h2")
public class DataSourceH2Config {
    private String url;
    private String driverClassName;
    private String username;
    private String password;

    @Value("initialization-mode")
    private String initializationMode;

    private String data;
}
