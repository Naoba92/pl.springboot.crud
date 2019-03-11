package pl.springboot.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages ="pl.springboot.crud.repository")
public class AppPresistanceConfig {

}
