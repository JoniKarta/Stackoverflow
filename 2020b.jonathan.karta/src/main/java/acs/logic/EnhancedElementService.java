package acs.logic;

import java.util.Set;

import acs.boundaries.ElementBoundary;
import acs.boundaries.ElementIdBoundary;

public interface EnhancedElementService extends ElementService{
	
	public void bindElements(String managerEmail, String parentElementId, ElementIdBoundary input);
	
	public Set<ElementBoundary> getAllChildrens(String userEmail, String parentElementId);
	
	public Set<ElementBoundary> getAllParents(String userEmail, String childElementId);

}
