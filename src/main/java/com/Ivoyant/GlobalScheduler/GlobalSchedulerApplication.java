package com.Ivoyant.GlobalScheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

@SpringBootApplication
@ComponentScan(basePackages = {"org.Ivoyant.GsPlugin","org.Ivoyant.service"} )
public class GlobalSchedulerApplication {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(GlobalSchedulerApplication.class, args);
	}
}
