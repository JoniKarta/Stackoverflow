package acs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @authors Naor Farag, Jonathan Karta, Gil Glick, Miri Krihely, Dani kozakovich
 */
@SpringBootApplication
public class Application {

	/**
	 * This is the main method that starts the whole spring framework and turn on
	 * our server using TomCat.
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
