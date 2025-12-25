package com.valentin.schedule;

import org.springframework.boot.SpringApplication;

public class TestScheduleManagementApplication {

	public static void main(String[] args) {
		SpringApplication.from(ScheduleManagementApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
