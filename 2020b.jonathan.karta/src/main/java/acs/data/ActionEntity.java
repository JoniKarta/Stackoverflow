package acs.data;

import java.util.Date;

public class ActionEntity {

	private Long actionId; // In the ActionBoundary it was String
	private String type;
	private Element element;
	private Date createdTimestamp;
	private Invoker invokedBy;
	private String actionAttributes; // In the ActionBoundary it was map

	public ActionEntity(Long actionId, String type, Element element, Date createdTimestamp, Invoker invokedBy,
			String actionAttributes) {
		super();
		this.actionId = actionId;
		this.type = type;
		this.element = element;
		this.createdTimestamp = createdTimestamp;
		this.invokedBy = invokedBy;
		this.actionAttributes = actionAttributes;
	}
	
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

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Invoker getInvokedBy() {
		return invokedBy;
	}

	public void setInvokedBy(Invoker invokedBy) {
		this.invokedBy = invokedBy;
	}

	public String getActionAttributes() {
		return actionAttributes;
	}

	public void setActionAttributes(String actionAttributes) {
		this.actionAttributes = actionAttributes;
	}

}
