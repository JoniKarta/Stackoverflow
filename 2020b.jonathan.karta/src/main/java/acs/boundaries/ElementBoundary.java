package acs.boundaries;

import java.util.Date;

import acs.data.ElementAttr;
import acs.data.ElementType;
import acs.data.Location;
import acs.data.UserGenerator;

public class ElementBoundary {

	private String elementId;
	private ElementType type;
	private String name;
	private boolean active;
	private Location location;
	private Date createTimeStamp;
	private UserGenerator createdBy;
	private ElementAttr elementAttr;

	public ElementBoundary() {
		super();
	}

	public ElementBoundary(String elementId, ElementType type, String name, boolean active, Location location,
			Date createTimeStamp, UserGenerator createdBy, ElementAttr elementAttr) {
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

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
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

	public UserGenerator getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserGenerator createdBy) {
		this.createdBy = createdBy;
	}

	public ElementAttr getElementAttr() {
		return elementAttr;
	}

	public void setElementAttr(ElementAttr elementAttr) {
		this.elementAttr = elementAttr;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
