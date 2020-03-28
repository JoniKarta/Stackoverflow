package acs.boundaries;

import java.util.Date;

public class ActionBoundary {
	private String actionId; // may be changed to a class which contains domain-url and id
	private String type; // may be changed to enum
	private Element element;
	private Date createdTimestamp;


	public ActionBoundary() {

	}

	public ActionBoundary(String actionId, String type, Element element) {
		super();
		this.actionId = actionId;
		this.type = type;
		this.element = element;
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

	@Override
	public String toString() {
		return "ActionBoundary [actionId=" + actionId + ", type=" + type + ", element=" + element + "]";
	}

}
