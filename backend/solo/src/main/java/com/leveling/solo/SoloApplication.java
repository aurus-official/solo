package com.leveling.solo;

import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SoloApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SoloApplication.class, args);
        String[] names = applicationContext.getBeanDefinitionNames();
        Stream.of(names).filter(name -> name.compareTo("springSessionRepositoryFilter") == 0)
                .forEach(System.out::println);
        ;
    }
}
