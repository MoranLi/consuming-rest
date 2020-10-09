package com.example.consumingrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Timer;

@SpringBootApplication
public class ConsumingRestApplication  implements CommandLineRunner {

	@Autowired
	ReplyTimer timer;

	@Autowired
	JdbcComponent component;

	@Override
	public void run(String... strings) throws Exception {
		JdbcTemplate template = component.getTemplate();
		template.execute("DROP TABLE IF EXISTS reply");
		template.execute("CREATE TABLE reply (pid SERIAL, type VARCHAR(255), id INT, quote VARCHAR(255))");

		timer.startTimer();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

}
