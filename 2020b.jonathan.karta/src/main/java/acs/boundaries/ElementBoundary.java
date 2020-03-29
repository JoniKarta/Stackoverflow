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
	private Date createTimeStamp;
	private Creator createdBy;
	private Location location;
	private Map<String, Object> elementAttr;

	public ElementBoundary() {
		super();
	}

	public ElementBoundary(String elementId, String type, String name, boolean active, Location location,
			Date createTimeStamp, Creator createdBy, Map<String, Object> elementAttr) {
		super();
		this.elementId = elementId;
		this.type = type;
		this.name = name;
		this.active = active;
		this.location = location;
		this.createTimeStamp = createTimeStamp;
		this.createdBy = createdBy;
		this.elementAttr = elementAttr;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreateTimeStamp() {
		return createTimeStamp;
	}

	public void setCreateTimeStamp(Date createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}

	public Creator getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Creator createdBy) {
		this.createdBy = createdBy;
	}

	public Map<String, Object> getElementAttr() {
		return elementAttr;
	}

	public void setElementAttr(Map<String, Object> elementAttr) {
		this.elementAttr = elementAttr;
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
				+ ", createTimeStamp=" + createTimeStamp + ", createdBy=" + createdBy + ", location=" + location
				+ ", elementAttr=" + elementAttr + "]";
	}

}
