package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		String sql = "INSERT INTO routes (status, route_id, event_id, last_update) VALUES ("
				+ "'safe', 'null', 'null', 'null')";

		int rows = jdbcTemplate.update(sql);
		if (rows > 0){
			System.out.println("a new row has been created");
		}
	}

}
