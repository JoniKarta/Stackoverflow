package acs.data;

import java.util.Date;
import java.util.Map;

public class ElementEntity {


	private Long elementId; // In the ElementBoundary it was String
	private String type;
	private String name;
	private boolean active;
	private Date createdTimestamp;
	private Creator createdBy;
	private Location location;
	private String elementAttribute; // In the ElementBoundary it was map
	public Long getElementId() {
		return elementId;
	}
	public void setElementId(Long elementId) {
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
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getElementAttribute() {
		return elementAttribute;
	}
	public void setElementAttribute(String elementAttribute) {
		this.elementAttribute = elementAttribute;
	}
	
	
	
}
