package com.Bee.BeeWebserver;

import com.Bee.BeeWebserver.Locations;

//basic Web application import
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//dependencies for the rest controller
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

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
@CrossOrigin
@SpringBootApplication
public class BeeWebserverApplication {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(BeeWebserverApplication.class, args);
	}

	@CrossOrigin
	@GetMapping("/")
	String hello(){
		return "Hello World";
	}

	@CrossOrigin
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


	// Testing for events table
	@CrossOrigin
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

	// Testing for bound_coords table
	@CrossOrigin
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

	//Testing for evacuee table
	@CrossOrigin
	@GetMapping("/evacuee")
	String evacuee(Map<String,Object> model){
		String d = " ";
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO evacuee (notification_token, notification_sent_at, acknowledged, acknowledged_at, safe, marked_safe_at, location, location_updated_at, name) VALUES ('false','2004-10-19 10:23:54+02','false','2004-10-19 10:23:54+02','false','2004-10-19 10:23:54+02','POINT(-118.4079 33.9434)','2004-10-19 10:23:54+02','Fred Flinstone')");
			ResultSet rs = stmt.executeQuery("SELECT acknowledged FROM evacuee");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getString("acknowledged"));
				d = d + rs.getString("acknowledged");
			}

			model.put("records", output);
			return d;
		}
		catch(Exception e){
			return "idiot";
		}
	}

	// Testing for reports table
	@CrossOrigin
	@GetMapping("/reports")
	String reports(Map<String,Object> model){
		String d = " ";
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO reports (reported_at, type, info, location) VALUES ('2004-10-19 10:23:54+02','fire','its lookin real bad chief','POINT(-118.4079 33.9434)')");
			ResultSet rs = stmt.executeQuery("SELECT type FROM reports");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getString("type"));
				d = d + rs.getString("type");
			}

			model.put("records", output);
			return d;
		}
		catch(Exception e){
			return "idiot";
		}
	}

	// Testing for Routes table
	@CrossOrigin
	@GetMapping("/routes")
	String routes(Map<String,Object> model){
		String d = " ";
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO routes (status, last_update) VALUES ('inactive','2004-10-19 10:23:54+02')");
			ResultSet rs = stmt.executeQuery("SELECT status FROM routes");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getString("status"));
				d = d + rs.getString("status");
			}

			model.put("records", output);
			return d;
		}
		catch(Exception e){
			return "idiot";
		}
	}

	// Testing for waypoints table
	@CrossOrigin
	@GetMapping("/waypoints")
	String waypoints(Map<String,Object> model){
		String d = " ";
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			// in this case ordinal is considered an integer, incremented webapp-side
			stmt.executeUpdate("INSERT INTO waypoints (checkpoint, location) VALUES ('t','POINT(-118.4079 33.9434)')");
			ResultSet rs = stmt.executeQuery("SELECT checkpoint FROM waypoints");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getString("checkpoint"));
				d = d + rs.getString("checkpoint");
			}

			model.put("records", output);
			return d;
		}
		catch(Exception e){
			return "idiot";
		}
	}

	// Testing for locations table
	@CrossOrigin
	@GetMapping("/locations")
	String locations(Map<String,Object> model){
		String d = " ";
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO locations (name, type, info, location) VALUES ('fred','something','something else',POINT(-118.4079 33.9434))");
			ResultSet rs = stmt.executeQuery("SELECT name FROM locations");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getString("name"));
				d = d + rs.getString("name");
			}

			model.put("records", output);
			return d;
		}
		catch(Exception e){
			return "idiot";
		}
	}
	/*return relevant info
	@GetMapping("/Return_Location")
	String Return_Location(){

	}*/

	/*
	//general structure for editing already existing data
	@PostMapping(path = "/NamePlaceholder/{Id}", consumes = datatype)
	public ResponseEntity<String> functionName(@PathVariable string id, @RequestBody classtype container)
	{

	}
	*/ 

	/*
	//general structure for requesting data of certain id
	@GetMapping(path = "/NamePlaceholder/{id}", produces = datatype)
	public ResponseEntity<String> functionName(@Pathvariable string id){

	}
	*/

	/* 
	//general structure of deleting data from the table
	@DeleteMapping("/Nameplaholder/{id}")
	public ResponseEntity<String> functionName(@Pathvariable String id){
		
	}
	*/

	//attempt to mark safe when mobile app sends
	@CrossOrigin
	@PostMapping(path = "/Mark_Safe_M/{id}", consumes = "application/json")
	public ResponseEntity<String> Mark_Safe(@PathVariable String id, @RequestBody Locations test){
		
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE evacuee SET safe = 'true' WHERE user_id = '"+ id +"'");
		}
		catch(Exception e){
			return new ResponseEntity<>("Error " + id, HttpStatus.BAD_REQUEST);
		}

		String file = "Marked safe at longitude = " + test.longitude + ", latitude = " + test.latitude;

		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	/*
	//attempted function in creating a report from the mobile app
	@CrossOrigin
	@PostMapping(path = "/User_Report/", consumes = "application/json")
	public ResponseEntity<String> Make_Report(@ResponsBody Reports test){
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE evacuee SET safe = 'true' WHERE user_id = '"+ id +"'");
		}
		catch(Exception e){
			return new ResponseEntity<>("Error " + id, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(file, HttpStatus.OK);
	}
*/

	@CrossOrigin
	@PostMapping(path = "/Create_New_User_M", consumes = "application/json")
	public ResponseEntity<String> Create_New_User(@RequestBody Evacuee test){

		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO evacuee (notification_token, notification_sent_at, acknowledged, acknowledged_at, safe, marked_safe_at, location, location_updated_at, name) VALUES ('"+ test.notification_token + "', '" + test.notification_sent_at + "','" + test.acknowledged + "','" + test.acknowledged_at + "','" + test.safe + "','" + test.marked_safe_at + "','" + test.location + "','" + test.location_updated_at + "','" + test.name + "')");
		}
		catch(Exception e){
			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}

		String file = "this worked";

		return new ResponseEntity<>(file, HttpStatus.OK);
	}


	@CrossOrigin
	@GetMapping(path = "/Return_Location_M", produces = "application/json")
	public ResponseEntity<String> Return_Location_M(){
		return new ResponseEntity<>("Data request recieved", HttpStatus.OK);
	}

	//retrieves json files and parses through them
	@CrossOrigin
	@PostMapping(path = "/Input_Location", consumes = "application/x-www-form-urlencoded")
	public ResponseEntity<String> Input_Locations(){
		

		return new ResponseEntity<>("Success!", HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(path = "/Input_Location_M", consumes = "application/json")
	public ResponseEntity<String> Input_Locations_M(@RequestBody Locations test){

		String file = "longitude = " + test.longitude + ", latitude = " + test.latitude;

		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	// Recieving evacuee info from mobile application
	@CrossOrigin
	@PostMapping(path = "/get_evacuee_M", consumes = "application/json")
	public ResponseEntity<String> Get_Evacuee_M(@RequestBody Evacuee evacuee_test){
		return new ResponseEntity<>("Data request recieved", HttpStatus.OK);
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