package acs.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * This class creates an entity in the database which has a column of 'lastIdValue' that 
 * auto-increment with each creation of an action entity.
 */
@Entity
public class LastActionIdValue {

	private Long lastIdValue;

	public LastActionIdValue() {
	}

	/**
	 * This method auto-increment the database's column and lets the database generate
	 * a new value with each insert operation.
	 * 
	 * @return Return the last id value of the action entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getLastIdValue() {
		return lastIdValue;
	}

	public void setLastIdValue(Long lastIdValue) {
		this.lastIdValue = lastIdValue;
	}
}
