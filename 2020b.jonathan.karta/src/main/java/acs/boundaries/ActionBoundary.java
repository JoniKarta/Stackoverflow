package acs.boundaries;

import java.util.Date;
import java.util.Map;

import acs.boundaries.data.Element;
import acs.boundaries.data.Invoker;

public class ActionBoundary {

	private String actionId; 
	private String type; 
	private Element element; 
	private Date createdTimestamp;
	private Invoker invokedBy; 
	private Map<String, Object> actionAttributes;

	public ActionBoundary() {

	}

	public ActionBoundary(String actionId, String type, Element element, Date createdTimestamp, Invoker invokedBy,
			Map<String, Object> actionAttributes) {
		super();
		this.actionId = actionId;
		this.type = type;
		this.element = element;
		this.createdTimestamp = createdTimestamp;
		this.invokedBy = invokedBy;
		this.actionAttributes = actionAttributes;
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

	public Map<String, Object> getActionAttributes() {
		return actionAttributes;
	}

	public void setActionAttributes(Map<String, Object> actionAttributes) {
		this.actionAttributes = actionAttributes;
	}

	@Override
	public String toString() {
		return "ActionBoundary [actionId=" + actionId + ", type=" + type + ", element=" + element + "]";
	}

}
