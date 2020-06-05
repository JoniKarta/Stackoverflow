package acs.data;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import acs.boundaries.data.Element;
import acs.boundaries.data.Invoker;

@Entity
@Table(name="ACTIONS")
public class ActionEntity {

	private Long actionId; 
	private String type;
	private Element element;
	private Date creation;
	private Invoker invokedBy;
	private String actionAttributes;
	
	public ActionEntity(){
		
	}

	public ActionEntity(Long actionId, String type, Element element, Date creation, Invoker invoker,
			String actionAttributes) {
		super();
		this.actionId = actionId;
		this.type = type;
		this.element = element;
		this.creation = creation;
		this.invokedBy = invoker;
		this.actionAttributes = actionAttributes;
	}
	
	@Id
	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Embedded
	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	@Embedded
	public Invoker getInvokedBy() {
		return invokedBy;
	}

	public void setInvokedBy(Invoker invokedBy) {
		this.invokedBy = invokedBy;
	}

	@Lob
	public String getActionAttributes() {
		return actionAttributes;
	}

	public void setActionAttributes(String actionAttributes) {
		this.actionAttributes = actionAttributes;
	}

}