package com.eventManager.weightlifting;

import com.eventManager.weightlifting.repo.CoachRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class WeightliftingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeightliftingApplication.class, args);
	}
}
