package acs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import acs.boundaries.UserBoundary;
import acs.boundaries.UserRole;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerKit1Tests {

	private RestTemplate restTemplate;
	private String adminUrl;
	private String url;
	private int port;

	@LocalServerPort
	public void setPort(int port) {
		this.port = port;
	}
	
	@PostConstruct
	public void init() {
		this.restTemplate = new RestTemplate();
		this.url = "http://localhost:" + port + "/acs/users";
		this.adminUrl = "http://localhost:" + port + "/acs/admin/users";
	}

	

	@BeforeEach
	public void setup() {
		this.restTemplate.delete(this.adminUrl + "/{adminEmail}", "Jonathan@gmail.com");
	}

	@AfterEach
	public void teardown() {
		this.restTemplate.delete(this.adminUrl + "/{adminEmail}", "Jonathan@gmail.com");
	}

	@Test
	public void testContext() {
		
	}

	@Test
	public void testGetAllUsersOnServerInitReturnsEmptyUserBoundaryArray() throws Exception {
		 /* GIVEN the server is up
		 AND the server is empty  
		 WHEN i invoke GET request with the url /acs/admin/users/{adminEmail} 
		 THEN the operation will response status 2xx
		 AND the server will return to the client empty array*/

		UserBoundary[] users = this.restTemplate
				.getForObject(this.adminUrl + "/{adminEmail}", UserBoundary[].class, "Jonathan@gmail.com");
		assertThat(users).isEmpty();
	}
	
	@Test
	public void testPostRequestWithNewUserReturnsUserDetailsInResponse() {
		// GIVEN the server is  up
		// AND the database is empty 
		// WHEN i invoke POST request with the url /acs/users
		// THEN the operation will response with status 2xx 
		//AND the server will return object as follow:
		// {"email":"Jonathan@gmail.com", "role":"PLAYER", "username":"Joni","avatar":";)"}
		UserBoundary user = new 
				UserBoundary("Jonathan@gmail.com", 
						UserRole.PLAYER, 
						"Joni", 
						";)");
		UserBoundary userFromServer = this.restTemplate
				.postForObject(this.url, user, UserBoundary.class);
		
		assertThat(user).isEqualToComparingOnlyGivenFields(
				userFromServer, "email", "role", "username", "avatar");
	}
	
	
	@Test
	public void testGetSpecicifUserFromTheDatabaseByIdReturnProperUserDetails() {
		// GIVEN the server is up 
		// WHEN i invoke POST request with the url : acs/users/
		// THEN the server will response with status 2xx
		// AND the server will return object as follow:
		// "{"email": "Gil@gmail.com", "role":"ADMIN", "username":"Glide","avatar":"0)"
		UserBoundary newUser = new 
				UserBoundary("Gil@gmail.com",
				UserRole.ADMIN,
				"Glide",
				"0))");
				
		UserBoundary userFromServer = this.restTemplate.postForObject(this.url, newUser, UserBoundary.class);
		
		UserBoundary userFromDatabase = this.restTemplate.getForObject(this.url + "/login/{userEmail}", UserBoundary.class,userFromServer.getEmail());
		assertThat(userFromServer)
		.usingRecursiveComparison()
		.isEqualTo(userFromDatabase);
	}
	
	@Test
	public void testDeleteAllUsersFromTheDatabaseReturnsEmptyUserBoundaryArray() {
		/* GIVEN the server is up 
		 WHEN i invoke the DETELE request with the url : acs/admin/users/Jonathan@gmail.com
		 THEN the delete operation responded with status 2xx
		 AND the server return to the client empty array*/
		this.restTemplate.delete(this.adminUrl + "/{adminEmail}", "Jonathan@gmail.com");
		UserBoundary[] users = this.restTemplate.getForObject(this.adminUrl + "/{adminEmail}", UserBoundary[].class, "Jonathan@gmail.com");
		assertThat(users).isEmpty();
	}
	
	@Test
	public void testUpdateUserNameFieldReturnsTheProperUserAfterUpdate() {
		// GIVEN the server is up
		// AND the server contain user with userName "Joni" and avatar ";)"
		// THEN the put operation responded with 2xx status
		// WHEN I PUT /{userEmail} and {"userName":"jon", "avatar": "*)"}
		// AND the database gets updated	
		//AND the database dosn't update non relevant fields
		UserBoundary user = this.restTemplate.postForObject(this.url, 
				new 
				UserBoundary("Jonathan@gmail.com",
						UserRole.MANAGER, 
						"Joni", 
						";)") , UserBoundary.class);
		
		Map<String,Object> update = new HashMap<>();
		update.put("username", "Jon");
		update.put("avatar", "*)");
		this.restTemplate.put(this.url + "/{userEmail}", update,"Jonathan@gmail.com");
		
		assertThat(this.restTemplate
				.getForObject(this.url + "/login/{userEmail}", UserBoundary.class,"Jonathan@gmail.com"))
		.extracting(
				"email",
				"role",
				"username",
				"avatar")
		.containsExactly(
				user.getEmail(),
				user.getRole(),
				"Jon",
				"*)");
	}
	
	
	
	 
	
	

}
