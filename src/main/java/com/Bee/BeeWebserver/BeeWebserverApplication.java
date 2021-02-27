package com.Bee.BeeWebserver;

//basic Web application import
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//dependencies for the rest controller
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://bee-webapp.herokuapp.com/")
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
	String test(Map<String, Object> model){

		String something = " ";
		try (Connection connection = dataSource.getConnection()) 
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO locations (name) VALUES ('Jeff')");
			ResultSet rs = stmt.executeQuery("SELECT name FROM locations");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
			  output.add("Read from DB: " + rs.getString("name"));
			  something = something + rs.getString("name");
			}
			
			model.put("records", output);
			return something;
		} 
		catch(Exception e) 
		{
			return "error";
		} 
	}

	@GetMapping("/events")
	String events(Map<String,Object> model){
		String test = " ";
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO events (severity, instructions, type) VALUES ('extreme','flee','real bad')");
			ResultSet rs = stmt.executeQuery("SELECT severity FROM events");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getString("severity"));
				test = test + rs.getString("severity");
			}

			model.put("records", output);
			return test;
		}
		catch(Exception e){
			return "error lol idiot";
		}
	}

	@GetMapping("/bound_coords")
	String bound_coords(Map<String,Object> model){
		String test = " ";
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			// changed location data type to text from geometry for testing
			stmt.executeUpdate("INSERT INTO bound_coords (location) VALUES ('text')");
			ResultSet rs = stmt.executeQuery("SELECT ordinal FROM bound_coords");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getString("ordinal"));
				test = test + rs.getString("ordinal");
			}

			model.put("records", output);
			return test;
		}
		catch(Exception e){
			return "error lol idiot";
		}
	}




	/*return relevant info
	@GetMapping("/Return_Location")
	String Return_Location(){

	}*/

	//retrieves json files and parses through them
	@PostMapping(path = "/Input_Location", consumes = "application/json", produces = "application/json")
	ResponseEntity<String> Input_Locations(){
		return new ResponseEntity<>("Success!", HttpStatus.OK);
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



