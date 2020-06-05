package acs.logic.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import acs.boundaries.ElementBoundary;
import acs.boundaries.ElementIdBoundary;

public interface EnhancedElementService extends ElementService{
	
	public void bindChildToParent(String managerEmail, String parentElementId, ElementIdBoundary input);
	
	public Set<ElementBoundary> getAllElementChildrens(String userEmail, String parentElementId);
	
	public Collection<ElementBoundary> getAllElementParents(String userEmail, String childElementId);

	public List<ElementBoundary> searchElementsByName(String userEmail, String name, int size, int page);

	public List<ElementBoundary> searchElementsByType(String userEmail, String type, int size, int page);

	public List<ElementBoundary> getAllElements(String userEmail, int size, int page);

	public List<ElementBoundary> getAllElementChildrens(String userEmail, String parentElementId, int size, int page);

	public List<ElementBoundary> getAllElementParents(String userEmail, String childElementId, int size, int page);

}
