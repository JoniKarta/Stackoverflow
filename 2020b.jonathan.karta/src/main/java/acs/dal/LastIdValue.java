package acs.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LastIdValue {

	private Long lastIdValue;
	
	public LastIdValue() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue
	public Long getLastIdValue() {
		return lastIdValue;
	}
	
	public void setLastIdValue(Long lastIdValue) {
		this.lastIdValue = lastIdValue;
	}
	

}
