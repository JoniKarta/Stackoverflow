package acs.dal;

import org.springframework.data.repository.PagingAndSortingRepository;

import acs.data.ActionEntity;

/**
 * 
 * Extension of CrudRepository to provide additional methods to retrieve
 * entities using the pagination and sorting abstraction.
 *
 * @param ActionEntity - the domain type the repository manages
 * @param Long - the type of the id of the entity the repository manages
 */
public interface ActionDao extends PagingAndSortingRepository<ActionEntity, Long> {

}
