package com.leveling.solo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@Configuration
@EnableJdbcHttpSession(tableName = "spring_session")
public class SessionConfiguration {

    @Value("${session.datasource.username}")
    private String username;

    @Value("${session.datasource.password}")
    private String password;

    @Bean
    @SpringSessionDataSource
    DataSource setupSessionDatabase() {
        return DataSourceBuilder.create()
                .username(username)
                .password(password)
                .url("jdbc:mariadb://localhost:3306/session_levelling_system")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }
}
