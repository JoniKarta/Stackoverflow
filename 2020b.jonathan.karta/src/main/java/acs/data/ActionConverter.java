package acs.data;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import acs.boundaries.ActionBoundary;

@Component
public class ActionConverter {

	private ObjectMapper jackson;

	@PostConstruct
	public void setup() {
		jackson = new ObjectMapper();
	}

	public ActionEntity convertToEntity(ActionBoundary action) {
		return new ActionEntity
				(toEntityId(action.getActionId()), action.getType(), action.getElement(),
				action.getCreatedTimestamp(), action.getInvokedBy(),
				marshActionAtrributes(action.getActionAttributes()));
	}

	public Object convertFromEntity(ActionEntity action) {
		return new ActionBoundary(fromEntityId(action.getActionId()), action.getType(), action.getElement(),
				action.getCreation(), action.getInvokedBy(),
				unMarshActionAtrributes(action.getActionAttributes()));
	}

	private Map<String, Object> unMarshActionAtrributes(String actionAttributes) {
		Map<String, Object> actionUnMarshaling;
		try {
			actionUnMarshaling = jackson.readValue(actionAttributes, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return actionUnMarshaling;
	}

	public String marshActionAtrributes(Map<String, Object> actionAttributes) {
		String actionMarshaling;
		try {
			actionMarshaling = jackson.writeValueAsString(actionAttributes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return actionMarshaling;
	}

	public Long toEntityId(String actionId) {
		if (actionId != null) {
			return Long.parseLong(actionId);
		} else {
			return null;
		}
	}

	public String fromEntityId(Long actionId) {
		if (actionId != null) {
			return actionId.toString();
		} else {
			return null;
		}
	}

}