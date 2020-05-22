package acs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import acs.boundaries.ActionBoundary;
import acs.boundaries.UserBoundary;
import acs.boundaries.UserRole;
import acs.boundaries.data.Element;
import acs.boundaries.data.Invoker;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ActionControllerKit1Tests {

	private RestTemplate restTemplate;
	private String adminUrl;
	private String userUrl;
	private String url;
	private int port;

	@LocalServerPort
	public void setPort(int port) {
		this.port = port;
	}

	@PostConstruct
	public void init() {
		this.restTemplate = new RestTemplate();
		this.url = "http://localhost:" + port + "/acs/actions";
		this.userUrl = "http://localhost:" + port + "/acs/users";
		this.adminUrl = "http://localhost:" + port + "/acs/admin/actions";

	}

	@BeforeEach
	public void setup() {
		this.restTemplate.delete(this.userUrl + "/{adminEmail}", "gil@gmail.com");
		this.restTemplate.delete(this.userUrl + "/{playerEmail}", "jonathan@gmail.com");
		
		this.restTemplate.postForObject(userUrl, new UserBoundary("gil@gmail.com", UserRole.ADMIN, "glide", ";)"),
				UserBoundary.class);
		this.restTemplate.postForObject(userUrl, new UserBoundary("jonathan@gmail.com", UserRole.PLAYER, "Joni", "0"),
				UserBoundary.class);
		this.restTemplate.delete(this.adminUrl + "/{adminEmail}", "gil@gmail.com");
	}

	public void teardown() {
		this.restTemplate.delete(this.adminUrl + "/{adminEmail}", "gil@gmail.com");
		this.restTemplate.delete(this.userUrl + "/{adminEmail}", "gil@gmail.com");
		this.restTemplate.delete(this.userUrl + "/{playerEmail}", "jonathan@gmail.com");
	}

	@Test
	public void testContext() {

	}

	@Test
	public void testGetAllUsersOnServerInitReturnsEmptyActionBoundaryArray() throws Exception {
		/*
		 * GIVEN the server is up AND the server is empty WHEN admin invoke GET request
		 * with the url /acs/admin/actions/{adminEmail} HEN the operation will response
		 * status 2xx AND the server will return to the client empty array
		 */
		ActionBoundary[] actions = this.restTemplate.getForObject(this.adminUrl + "/{adminEmail}",
				ActionBoundary[].class, "gil@gmail.com");
		assertThat(actions).isEmpty();
	}

	public void testDeleteAllActionsFromTheDatabaseReturnsEmptyActionBoundaryArray() {
		/*
		 * GIVEN the server is up WHEN admin invoke the DETELE request with the
		 * url:/acs/admin/actions/{adminEmail} THEN the delete operation responded with
		 * status 2xx AND the server return to the client empty array
		 */
		this.restTemplate.postForObject(this.url, new ActionBoundary(null, "complete", new Element("1a"), new Date(),
				new Invoker("jonathan@gmail.com"), null), ActionBoundary.class);

	}

}
