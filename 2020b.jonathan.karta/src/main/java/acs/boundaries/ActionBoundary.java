package acs.boundaries;

import java.util.Date;
import java.util.Map;

import acs.data.Creator;

public class ActionBoundary {
	private String actionId; // may be changed to a class which contains domain-url and id
	private String type; // may be changed to enum
	private Element element; 
	private Date createdTimestamp;
	private Invoker invokedBy; // may be changed to class 'InvokedBy'
	private Map<String, Object> actionAttr;

	public ActionBoundary() {

	}

	public ActionBoundary(String actionId, String type, Element element, Date createdTimestamp, Invoker invokedBy,
			Map<String, Object> actionAttr) {
		super();
		this.actionId = actionId;
		this.type = type;
		this.element = element;
		this.createdTimestamp = createdTimestamp;
		this.invokedBy = invokedBy;
		this.actionAttr = actionAttr;
	}

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

	public Map<String, Object> getActionAttr() {
		return actionAttr;
	}

	public void setActionAttr(Map<String, Object> actionAttr) {
		this.actionAttr = actionAttr;
	}

	@Override
	public String toString() {
		return "ActionBoundary [actionId=" + actionId + ", type=" + type + ", element=" + element + "]";
	}

}
