package acs.data;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Actions")
public class ActionEntity {

	private String actionId; // In the ActionBoundary it was String
	private String type;
	private Element element;
	private Date createdTimestamp;
	private Invoker invokedBy;
	private String actionAttributes; // In the ActionBoundary it was map
	
	public ActionEntity(){
		
	}

	public ActionEntity(String actionId, String type, Element element, Date createdTimestamp, Invoker invokedBy,
			String actionAttributes) {
		super();
		this.actionId = actionId;
		this.type = type;
		this.element = element;
		this.createdTimestamp = createdTimestamp;
		this.invokedBy = invokedBy;
		this.actionAttributes = actionAttributes;
	}
	
	@Id
	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
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
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
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
