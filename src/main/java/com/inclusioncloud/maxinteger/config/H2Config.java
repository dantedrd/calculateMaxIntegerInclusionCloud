package com.inclusioncloud.maxinteger.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DataSourceH2Config.class)
public class H2Config {
    private final DataSourceH2Config dataSourcePostgresConfig;

    /**
     * Constructor for {@link DataSourceH2Config}, initializing it with the specified {@link DataSourceH2Config}.
     *
     * @param dataSourceH2Config The configuration properties for H2 DataSource.
     */
    public H2Config(DataSourceH2Config dataSourceH2Config) {
        this.dataSourcePostgresConfig = dataSourceH2Config;
    }

    /**
     * Creates and configures a {@link DataSource} bean for the H2 database.
     * The {@link DataSource} is configured based on the properties provided in {@link DataSourceH2Config}.
     *
     * @return Configured {@link DataSource} instance for the H2 database.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourcePostgresConfig.getDriverClassName());
        dataSource.setUrl(dataSourcePostgresConfig.getUrl());
        dataSource.setUsername(dataSourcePostgresConfig.getUsername());
        dataSource.setPassword(dataSourcePostgresConfig.getPassword());
        return dataSource;
    }
}
