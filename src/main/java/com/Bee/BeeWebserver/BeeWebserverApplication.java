package com.Bee.BeeWebserver;

//basic Web application import
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//dependencies for the rest controller
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.jbdc.core.JbdcTemplate;

//Test dependencies for database connection
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.sql.ResultSet;

@RestController
@SpringBootApplication
public class BeeWebserverApplication {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(BeeWebserverApplication.class, args);
	}

	@GetMapping("/")
	String hello(){
		return "Hello World";
	}

	@GetMapping("/test")
	String test(Map<String, Object> model) {
		try (Connection connection = dataSource.getConnection()) 
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO locations (name) VALUES ('Jeff')");
			ResultSet rs = stmt.executeQuery("SELECT name FROM locations");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
			  output.add("Read from DB: " + rs.getString("name"));
			}
			
			model.put("records", output);
			return test;
		} 
		catch(Exception e) 
		{
			return "error";
		} 
	}

	@GetMapping("/demo")
	String demo(){
		//String dbUrl = System.getenv("JBDC_DATABASE_URL");
    	return "demo";
	}

	@Bean
	public DataSource dataSource() throws SQLException
	{
		if(dbUrl == null || dbUrl.isEmpty())
		{
			return new HikariDataSource();
		}
		else
		{
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			return new HikariDataSource(config);
		}
	} 
}



