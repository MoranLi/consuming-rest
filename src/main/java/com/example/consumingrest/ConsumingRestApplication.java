package com.example.consumingrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;

@SpringBootApplication
public class ConsumingRestApplication {

	public static void main(String[] args) {
		Timer timer = new Timer();
		ReplyTimerTask myTask = new ReplyTimerTask();
		timer.schedule(myTask, 5000L, 5000L);
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

}
