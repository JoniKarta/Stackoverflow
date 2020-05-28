package acs.boundaries;

/**
 * 
 * Roles that can be used in our system
 *
 */
public enum UserRole {
	/**
	 * Player role - has a limited access to the API 
	 */
	PLAYER,
	
	/**
	 * Manager role - has an access to the most of the API,
	 * excludes remove all objects from database.
	 */
	MANAGER,
	
	/**
	 * Admin role - has full control and access to the API.
	 */
	ADMIN
}
