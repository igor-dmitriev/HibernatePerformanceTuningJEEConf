package com.jeeconf.hibernate.performancetuning;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Igor Dmitriev on 4/30/16
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.jeeconf.hibernate.performancetuning")
@ComponentScan
@EnableCaching
public class Application {

}
