package acs.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LastActionIdValue {

	private Long lastIdValue;

	public LastActionIdValue() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getLastIdValue() {
		return lastIdValue;
	}

	public void setLastIdValue(Long lastIdValue) {
		this.lastIdValue = lastIdValue;
	}
}
