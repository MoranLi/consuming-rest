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

		initReplyTable(template);
		// default user name admin
		// default password admin@123
		initUserTable(template);

		timer.startTimer();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	private void initReplyTable(JdbcTemplate template){
		template.execute("DROP TABLE IF EXISTS reply");
		template.execute("CREATE TABLE reply (pid SERIAL, type VARCHAR(255), id INT, quote VARCHAR(255))");
	}

	private void initUserTable(JdbcTemplate template){
		//template.execute("ALTER TABLE authorities DROP FOREIGN KEY fk_authorities_users");
		template.execute("DROP TABLE IF EXISTS authorities");
		template.execute("DROP TABLE IF EXISTS users");
		template.execute("create table users(\n" +
				"\tusername varchar(50) not null primary key,\n" +
				"\tpassword varchar(100) not null,\n" +
				"\tenabled boolean not null\n" +
				");");
		template.execute("create table authorities (\n" +
				"\tusername varchar(50) not null,\n" +
				"\tauthority varchar(50) not null,\n" +
				"\tconstraint fk_authorities_users foreign key(username) references users(username)\n" +
				");");
		template.execute("create unique index ix_auth_username on authorities (username,authority);");
		template.execute("insert into users(username,password,enabled)\n" +
				"\tvalues('admin','$2a$10$hbxecwitQQ.dDT4JOFzQAulNySFwEpaFLw38jda6Td.Y/cOiRzDFu',true);\n");
		template.execute("insert into authorities(username,authority) \n" +
				"\tvalues('admin','ROLE_ADMIN');");
	}
}
