package acs.boundaries;

import java.util.Date;
import java.util.Map;

import acs.data.Creator;
import acs.data.Location;

public class ElementBoundary {

	private String elementId;
	private String type;
	private String name;
	private boolean active;
	private Date createdTimestamp;
	private Creator createdBy;
	private Location location;
	private Map<String, Object> elementAttribute;

	public ElementBoundary() {
		super();
	}

	public ElementBoundary(String elementId, String type, String name, boolean active, Location location,
			Date createdTimestamp, Creator createdBy, Map<String, Object> elementAttribute) {
		super();
		this.elementId = elementId;
		this.type = type;
		this.name = name;
		this.active = active;
		this.location = location;
		this.createdTimestamp = createdTimestamp;
		this.createdBy = createdBy;
		this.elementAttribute = elementAttribute;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Creator getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Creator createdBy) {
		this.createdBy = createdBy;
	}

	
	
	public Map<String, Object> getElementAttribute() {
		return elementAttribute;
	}

	public void setElementAttribute(Map<String, Object> elementAttribute) {
		this.elementAttribute = elementAttribute;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "ElementBoundary [elementId=" + elementId + ", type=" + type + ", name=" + name + ", active=" + active
				+ ", createTimeStamp=" + createdTimestamp + ", createdBy=" + createdBy + ", location=" + location
				+ ", elementAttr=" + elementAttribute + "]";
	}

}
