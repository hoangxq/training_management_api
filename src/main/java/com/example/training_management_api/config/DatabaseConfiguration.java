package com.example.training_management_api.config;

import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.sql.DataSource;
import jakarta.validation.constraints.NotBlank;
import java.util.Optional;

@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DatabaseConfiguration {

    @NotBlank
    private String url;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();

        builder.url(url)
                .username(username)
                .password(password);

        Optional.ofNullable(driverClassName)
                .filter(StringUtils::isNotBlank)
                .ifPresent(builder::driverClassName);

        return builder.build();
    }

}
