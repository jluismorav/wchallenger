package com.jmora.wchallenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.jmora.wchallenger")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
