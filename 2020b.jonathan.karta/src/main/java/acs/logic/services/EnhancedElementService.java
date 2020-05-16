package acs.logic.services;

import java.util.Collection;
import java.util.Set;

import acs.boundaries.ElementBoundary;
import acs.boundaries.ElementIdBoundary;

public interface EnhancedElementService extends ElementService{
	
	public void bindChildToParent(String managerEmail, String parentElementId, ElementIdBoundary input);
	
	public Set<ElementBoundary> getAllElementChildrens(String userEmail, String parentElementId);
	
	public Collection<ElementBoundary> getAllElementParents(String userEmail, String childElementId);

}
