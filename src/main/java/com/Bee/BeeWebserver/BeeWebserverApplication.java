package com.Bee.BeeWebserver;

import com.Bee.BeeWebserver.Locations;
//import com.Bee.BeeWebserver.Safe_Evac;
import java.util.ArrayList;

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

//Imports for sql statement calls. 
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.*;
import java.sql.ResultSet;

//dependencies for parsing JSON
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
public class BeeWebserverApplication {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(BeeWebserverApplication.class, args);
	}

	//original test function to see if the webserver was functional. Prints hello world to the screen when you go the web address
	@CrossOrigin
	@GetMapping("/")
	String hello(){
		return "Hello World";
	}

	//test function for inputing data into the locations table in the database
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


	//test function for inputing data into the events table in the database
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

	//test function for inputing data into the bound_coords table in the database
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

	//test function for inputing data into the evacuee table in the database
	@CrossOrigin
	@GetMapping("/evacuee")
	String evacuee(Map<String,Object> model){
		Integer d = 0;
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO evacuee (notification_token, notification_sent_at, acknowledged, acknowledged_at, safe, marked_safe_at, location, location_updated_at, name) VALUES ('false','2004-10-19 10:23:54+02','false','2004-10-19 10:23:54+02','false','2004-10-19 10:23:54+02','POINT(-118.4079 33.9434)','2004-10-19 10:23:54+02','Fred Flinstone')");
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM evacuee");

			//ArrayList<String> output = new ArrayList<String>();
			/*while (rs.next()) {
				output.add("Read from DB: " + String.valueOf(rs.getString(1)));
				d = rs.getInt(1);
			}*/

			while (rs.next()){
				//output.add("There are: " + rs.getInt("total") + " evacuees");
				d = rs.getInt(1);
			}


			//String z = String.valueOf(d);
			//model.put("records", output);
			//asdf
			return String.valueOf(d);
		}
		catch(Exception e){
			return e.toString();
		}
	}

	// test function for inputing data into the reports table in the database
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

	// test function for inputing data into the routes table in the database
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

	// test function for inputing data into the waypoint table in the database
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

	// test function for inputing data into the location table in the database
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

	//function prefabs for the updating functionality that we didn't have time to implement
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

	//marking not safe when the mobile app sends a not safe flag
	@CrossOrigin
	@PostMapping(path = "/Mark_Not_Safe_M/{id}", consumes = "application/json")
	public ResponseEntity<String> Mark_Not_Safe(@PathVariable String id, @RequestBody Locations test){
		
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE evacuee SET safe = 'false' WHERE user_id = '"+ id +"'");
		}
		catch(Exception e){
			return new ResponseEntity<>("Error " + id, HttpStatus.BAD_REQUEST);
		}

		String file = "Marked not safe at longitude = " + test.longitude + ", latitude = " + test.latitude;

		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	//attempted function in creating a report from the mobile app
	@CrossOrigin
	@PostMapping(path = "/User_Report", consumes = "application/json")
	public ResponseEntity<String> Make_Report(@RequestBody Reports test){

		String loc = "SRID=4326;POINT(" + test.latitude + " " + test.longitude + ")";

		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO reports (reported_at, type, info, reporter_id, location) VALUES ('" + test.reported_at + "','" + test.type + "','" + test.info + "','" + test.reporter_id + "','" + loc + "')");
		}
		catch(Exception e){
			return new ResponseEntity<>("Error ", HttpStatus.BAD_REQUEST);
		}

		String file = "success";

		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	// function that updates the name of the user corresponding the to the uuid in the database
	@CrossOrigin
	@PostMapping(path = "/Update_Username", consumes = "application/json")
	public ResponseEntity<String> Make_Report(@RequestBody Evacuee evac){
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE evacuee SET name = '" + evac.name + "' WHERE user_id = '"+ evac.user_id + "'");
		}
		catch(Exception e){
			return new ResponseEntity<>("Error ", HttpStatus.BAD_REQUEST);
		}
		String file = "username updated";
		return new ResponseEntity<>(file, HttpStatus.OK);
	}
	
	// Sends Report info to web application
	/* NOT DONE
	@CrossOrigin
	@GetMapping(path = "/Send_Report", produces = "application/json")
	public ResponseEntity<String> Send_Report(){

		Integer total = 0;
		Integer safe = 0;

		try(Connection connection = dataSource.getConnection())
		{
			//rs = safe count
			//rs2 = total count
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM evacuee WHERE safe = 'true'");
			ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM evacuee");

			while (rs.next()) {
				safe = rs.getInt(1);
			}

			while (rs2.next()) {
				total = rs2.getInt(1);
			}

			//Convert to json - Json won't format correctly unless you use the class container (in this case Safe_Evac)
			Safe_Evac safeCont = new Safe_Evac(total,safe);

			Gson gson = new Gson();
			String safeJson = gson.toJson(safeCont);

			return new ResponseEntity<>(safeJson, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}
	*/

	//Template for processing request from mobile application to add new user
	@CrossOrigin
	@PostMapping(path = "/Create_New_User_M", consumes = "application/json")
	public ResponseEntity<String> Create_New_User(@RequestBody Evacuee test){

		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO evacuee (user_id, notification_token, notification_sent_at, acknowledged, acknowledged_at, safe, marked_safe_at, location, location_updated_at, name) VALUES ('" + test.user_id + "','"+ test.notification_token + "', '" + test.notification_sent_at + "','" + test.acknowledged + "','" + test.acknowledged_at + "','" + test.safe + "','" + test.marked_safe_at + "','" + test.location + "','" + test.location_updated_at + "','" + test.name + "')");
		}
		catch(Exception e){
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}

		String file = "this worked";

		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	// function that changes the acknowledged bool in the database to true
	@CrossOrigin
	@PostMapping(path = "/Acknowledge_Notification", consumes = "application/json")
	public ResponseEntity<String> Acknowledge_Notification(@RequestBody Evacuee evacuee){
	
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE evacuee SET acknowledged = 'true' WHERE user_id = ('" + evacuee.user_id + "')");
		}
		catch(Exception e){
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
		String z = "success";
		return new ResponseEntity<>(z,HttpStatus.OK);
	}

	// function that fetches the amount of people in the zone and how many people are safe and sends them as a json
	@CrossOrigin
	@GetMapping(path = "/Return_Acknowledge_Count", produces = "application/json")
	public ResponseEntity<String> Return_Acknowledge_Count(){
		//might want to change the response entity to the class container maybe?

		Integer total = 0;
		Integer acknowledged = 0;

		try(Connection connection = dataSource.getConnection())
		{
			//rs = safe count
			//rs2 = total count
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM evacuee WHERE acknowledged = 'true'");
			ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM evacuee");

			while (rs.next()) {
				acknowledged = rs.getInt(1);
			}

			while (rs2.next()) {
				total = rs2.getInt(1);
			}

			//Convert to json - Json won't format correctly unless you use the class container (in this case Safe_Evac)
			//Safe_Evac safeCont = new Safe_Evac(total,safe);

			Gson gson = new Gson();
			String ack = new String("Acknowledged:" + acknowledged + ", Total: " + total);
			String ackJson = gson.toJson(ack);

			return new ResponseEntity<>(ackJson, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	//Recieve instructions from web application
	@CrossOrigin
	@PostMapping(path = "/Input_Instructions", consumes = "application/json")
	public ResponseEntity<String> Input_Instructions(@RequestBody Events event){
		
		Events testEvent = new Events(event.severity, event.type, event.instructions);
		
		try(Connection connection = dataSource.getConnection()){
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO events (severity, instructions, type) VALUES ('"+ testEvent.severity + "', '" + testEvent.instructions + "','" + testEvent.type + "')");
		}
		catch(Exception e){
			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}
		String s = "success";
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	//Send Instructions to mobile application
	@CrossOrigin
	@GetMapping(path = "/Send_Instructions", produces = "application/json")
	public ResponseEntity<String> Send_Instructions(){

		String severity = " ";
		String instructions = " ";
		String type = " ";

		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM events");

			while(rs.next()){
				severity = rs.getString(1);
				type = rs.getString(3);
				instructions = rs.getString(2);
			}
			Events eventInstruction = new Events(severity, type, instructions);
			Gson gson = new Gson();
			String eventsJson = gson.toJson(eventInstruction);

			return new ResponseEntity<>(eventsJson, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
		}
	}

	// Returns count of users that have marked themselves safe to web application.
	// complete
	@CrossOrigin
	@GetMapping(path = "/Return_Safe_Count", produces = "application/json")
	public ResponseEntity<String> Return_Safe_Count(){
		//might want to change the response entity to the class container maybe?

		Integer total = 0;
		Integer safe = 0;

		try(Connection connection = dataSource.getConnection())
		{
			//rs = safe count
			//rs2 = total count
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM evacuee WHERE safe = 'true'");
			ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM evacuee");

			while (rs.next()) {
				safe = rs.getInt(1);
			}

			while (rs2.next()) {
				total = rs2.getInt(1);
			}

			//Convert to json - Json won't format correctly unless you use the class container (in this case Safe_Evac)
			Safe_Evac safeCont = new Safe_Evac(total,safe);

			Gson gson = new Gson();
			String safeJson = gson.toJson(safeCont);

			return new ResponseEntity<>(safeJson, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	/* Pseudo-template for including json object in sql query
	Converts json to string, stores it in jsonb object in the table
	requires requestbody to be a string
	@CrossOrigin
	@PostMapping(path = "/Input_Route", consumes = "application/json")
	public ResponseEntity<String> Input_Routes(@RequestBody String feature){

		String test = new String(feature);
		try(Connection connection = dataSource.getConnection())
			{
				Statement stmt = connection.createStatement();
				stmt.executeUpdate("INSERT INTO json_test (json) VALUES ('"+ test + "')");
				String s = "success";
				return new ResponseEntity<>(s, HttpStatus.OK);
			}
		catch(Exception e){
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}s
	*/

	//grabs all the locations in the evacuee table and sends it all back to the requester
	@CrossOrigin
	@GetMapping(path = "/Get_User_Locations", produces = "application/json")
	public ResponseEntity<String> Get_User_Locations(){
		String user_loc = "";
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT location FROM evacuee");
			while(rs.next()){
				user_loc += "|" + rs.getString(1);
			}
			user_loc += "|";
			Gson gson = new Gson();
			String user_loc_json = gson.toJson(user_loc);
			return new ResponseEntity<>(user_loc_json, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	// function called to update the location of evacuee
	@CrossOrigin
	@PostMapping(path = "/Input_Location", consumes = "application/json")
	public ResponseEntity<String> Input_Locations(@RequestBody String feature){

		String test = new String(feature);
		Integer id = 0;

		if(test.indexOf("Polygon") != -1){
			try(Connection connection = dataSource.getConnection())
			{
				Statement stmt = connection.createStatement();
				stmt.executeUpdate("INSERT INTO bound_coords (json) VALUES ('"+ test + "')");
				ResultSet rs = stmt.executeQuery("SELECT id FROM bound_coords WHERE json = ('"+ test + "')");
				while(rs.next()){
					id = rs.getInt(1);
				}
				return new ResponseEntity<>(id.toString(), HttpStatus.OK);
			}
			catch(Exception e){
				return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
			}
		} else if(test.indexOf("LineString") != -1){
			try(Connection connection = dataSource.getConnection())
			{
				Statement stmt = connection.createStatement();
				stmt.executeUpdate("INSERT INTO routes (json) VALUES ('"+ test + "')");
				ResultSet rs = stmt.executeQuery("SELECT id FROM routes WHERE json = ('"+ test + "')");
				while(rs.next()){
					id = rs.getInt(1);
				}
				return new ResponseEntity<>(id.toString(), HttpStatus.OK);
			}
			catch(Exception e){
				return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
			}
		} else if(test.indexOf("Point") != -1){
			try(Connection connection = dataSource.getConnection())
			{
				Statement stmt = connection.createStatement();
				stmt.executeUpdate("INSERT INTO waypoints (json) VALUES ('"+ test + "')");
				ResultSet rs = stmt.executeQuery("SELECT id FROM waypoints WHERE json = ('"+ test + "')");
				while(rs.next()){
					id = rs.getInt(1);
				}
				return new ResponseEntity<>(id.toString(), HttpStatus.OK);
			}
			catch(Exception e){
				return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("failed", HttpStatus.OK);
	}

	// Sends all zone data to mobile application from database
	@CrossOrigin
	@GetMapping(path = "/Send_Zone", produces = "application/json")
	public ResponseEntity<String> Send_Zone(){
		String zones = "";
		Gson gson = new Gson();
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bound_coords");
			while(rs.next()){
				zones += "|" + rs.getString(2);
			}
			return new ResponseEntity<>(zones, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	//Sends all route data to mobile app from database
	@CrossOrigin
	@GetMapping(path = "/Send_Route", produces = "application/json")
	public ResponseEntity<String> Send_Route(){
		//ArrayList<String> routes = new ArrayList<String>();
		String routes = "";
		Gson gson = new Gson();
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM routes");
			while(rs.next()){
				routes += "|" + rs.getString(2);
			}
			return new ResponseEntity<>(routes, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	//Sends all point data to mobile app
	@CrossOrigin
	@GetMapping(path = "/Send_Point", produces = "application/json")
	public ResponseEntity<String> Send_Point(){
		String points = "";
		Gson gson = new Gson();
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM waypoints");
			while(rs.next()){
				points += "|" + rs.getString(2);
			}
			return new ResponseEntity<>(points, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	//recives the location of the user and updates their location in the database
	@CrossOrigin
	@PostMapping(path = "/Input_Location_M", consumes = "application/json")
	public ResponseEntity<String> Input_Locations_M(@RequestBody Locations test){

		try(Connection connection = dataSource.getConnection())
		{
			//String loc = "SRID=4326;POINT(" + String.valueOf(test.latitude) + " " + String.valueOf(test.longitude) + ")";
			String loc2 = test.latitude + " " + test.longitude;
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE evacuee SET location = '" + loc2 + "' WHERE user_id = '"+ test.user_id +"'");
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	// Recieving evacuee info from mobile application
	@CrossOrigin
	@PostMapping(path = "/get_evacuee_M", consumes = "application/json")
	public ResponseEntity<String> Get_Evacuee_M(@RequestBody Evacuee evacuee_test){
		return new ResponseEntity<>("Data request recieved", HttpStatus.OK);
	}

	// Sending zone data to Web Application
	@CrossOrigin
	@GetMapping(path = "/Send_Zone_to_WA", produces = "application/json")
	public ResponseEntity<String> Send_Zone_to_WA(){
		//SQL query to get zone data from database
		String d = " ";
		try(Connection connection = dataSource.getConnection())
		{
			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM bound_coords WHERE bound_coord_id = '09ad2e75-c5d7-4142-b680-280646ffbb01'");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getString("bound_coord_id"));
				d = d + rs.getString("bound_coord_id");
			}

			Gson gson = new Gson();
			String e = gson.toJson(d);

			//model.put("records", output);
			return new ResponseEntity<>(e, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	// test fucntion on sending data back to the mobile applicaiton
	@CrossOrigin
	@GetMapping(path = "/Return_Location_M", produces = "application/json")
	public ResponseEntity<String> Return_Location_M(){
		return new ResponseEntity<>("Data request recieved", HttpStatus.OK);
	}
	

	// database connection function
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
