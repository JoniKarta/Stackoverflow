package acs.data;

import java.util.Date;

public class ElementEntity {

	private Long elementId; // In the ElementBoundary it was String
	private String type;
	private String name;
	//TODO ask the team is it's should be Double or double
	private Boolean active; 
	private Date createdTimestamp;
	private Creator createdBy;
	private Location location;
	private String elementAttribute; // In the ElementBoundary it was map

	public ElementEntity() {
	}
	
	public ElementEntity(Long elementId, String type, String name, boolean active, Date createdTimestamp,
			Creator createdBy, Location location, String elementAttribute) {
		super();
		this.elementId = elementId;
		this.type = type;
		this.name = name;
		this.active = active;
		this.createdTimestamp = createdTimestamp;
		this.createdBy = createdBy;
		this.location = location;
		this.elementAttribute = elementAttribute;
	}

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
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
