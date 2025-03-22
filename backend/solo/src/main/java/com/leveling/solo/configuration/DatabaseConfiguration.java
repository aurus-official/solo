package com.leveling.solo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfiguration {
    @Value("${hibernate.datasource.username}")
    private String username;

    @Value("${hibernate.datasource.password}")
    private String password;

    @Bean
    @Primary
    DataSource setupDataSourceDatabase() {
        return DataSourceBuilder.create()
                .username(username)
                .password(password)
                .url("jdbc:postgresql://localhost:5432/leveling-system")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

}
