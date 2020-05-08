package acs.data;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import acs.boundaries.ElementBoundary;

@Component
public class ElementConverter {
	private ObjectMapper jackson;

	@PostConstruct
	public void setup() {
		jackson = new ObjectMapper();
	}

	public ElementBoundary convertFromEntity(ElementEntity entity) {
		return new ElementBoundary(
				entity.getElementId(),
				entity.getType(), 
				entity.getName(),
				entity.getActive(), 
				entity.getLocation(), 
				entity.getCreatedTimestamp(), 
				entity.getCreatedBy(),
				unMarshElementAttribute(entity.getElementAttribute()));
	} 

	public ElementEntity convertToEntity(ElementBoundary input) {
		return new ElementEntity(
				input.getElementId(), 
				input.getType(), 
				input.getName(),
				input.getActive(), 
				input.getCreatedTimestamp(), 
				input.getCreatedBy(), 
				input.getLocation(),
				marshElementAttribute(input.getElementAttribute()));
	}

	public String marshElementAttribute(Map<String, Object> input) {
		String elementMarshaling = null;
		try {
			elementMarshaling = this.jackson.writeValueAsString(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return elementMarshaling;
	}

	public Map<String, Object> unMarshElementAttribute(String input) {
		Map<String, Object> elementAttribute;
		try {
			elementAttribute = this.jackson.readValue(input, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return elementAttribute;
	}

	public Long toEntityId(String elementId) {
		if (elementId != null) {
			return Long.parseLong(elementId);
		} else {
			return null;
		}
	}

	public String fromEntityId(Long elementId) {
		if (elementId != null) {
			return elementId.toString();
		} else {
			return null;
		}
	}
}
