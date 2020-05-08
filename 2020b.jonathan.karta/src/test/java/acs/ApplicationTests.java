package acs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import acs.boundaries.ElementBoundary;
import acs.boundaries.UserBoundary;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
	
	private RestTemplate restTemplate;
	private String url;
	private int port;
	@LocalServerPort
	public void setPort(int port) {
		this.port = port;
	}
	
	@BeforeEach
	public void setup() {
		
	}
	
	@AfterEach
	public void teardown() {
		
	}
	@Test
	public void testContext() {
		
	}
	
	
	@Test
	public void testGetAllUsersOnServerInitReturnsEmptyUserBoundaryArray() throws Exception {
		
		 // Given the server is up
		 //	do nothing
		 
		 //When the admin invoke GET request with url - "acs/admin/users/Jonathan@gmail.com
		this.restTemplate = new RestTemplate();
		this.url = "http://localhost:" + port + "/acs/admin/users/Jonathan@gmail.com";
		UserBoundary[] users = this.restTemplate.getForObject(url, UserBoundary[].class);

		//* Then the server respond with status 2xx 
		// do nothing
		
		// * And the response body is empty array
		if(users.length != 0) {
			throw new Exception("Expected empty array, but get not empty array");
			
		}
		
	}
	
	@Test
	public void testGetElementWithEmptyElementIdInTheUri() {
		this.restTemplate = new RestTemplate();
		this.url = "http://localhost:" + port + "/acs/elements/Jonathan@gmail.com";
	//	this.restTemplate.postForObject(this.url + "", request, responseType)
		this.url = "http://localhost:" + port + "/acs/elements/Jonathan@gmail.com/1";
		ElementBoundary el = this.restTemplate.getForObject(url, ElementBoundary.class);
		System.out.println(el);
		
	}
}
