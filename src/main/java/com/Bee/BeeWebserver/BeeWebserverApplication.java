package com.Bee.BeeWebserver;

//basic Web application import
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//dependencies for the rest controller
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Test dependencies for database connection
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikeri.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Bean;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootApplication
public class BeeWebserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeeWebserverApplication.class, args);
	}

}

@RestController
class HelloController{

	@Value("spring.datasource.url")
	private String dbUrl;

	@Autowired
	private DataSource dataSource;

	@GetMapping("/")
	String hello(){
		return "Hello World";
	}

	@GetMapping("/test")
	String test(){
		try (Connection connection = dataSource.getConnection()) 
		{
			return "PeePeePooPoo";
		} 
		catch(Exception e) 
		{
			return "Error";
		}
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


