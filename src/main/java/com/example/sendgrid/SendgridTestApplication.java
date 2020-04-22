package com.example.sendgrid;

import com.sendgrid.SendGrid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SendgridTestApplication implements CommandLineRunner {

	@Autowired
	private NotificationService notificationService;


	public static void main(String[] args) {
		SpringApplication.run(SendgridTestApplication.class, args);
	}

	@Bean
	public SendGrid sendGrid() {
		return new SendGrid("<<apikey goes here!!>>");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("In runner");
		notificationService.sendEmail("","Hello","gajendran.c@egovernments.org", null);
	}
}
