package acs.logic.services;

import java.util.List;

import acs.boundaries.ActionBoundary;

public interface EnhancedActionService extends ActionService {
	
	public List<ActionBoundary> getAllActions(String adminEmail, int size, int page);
}
