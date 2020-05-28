package acs.dal;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * CrudRepository is a Spring Data interface for generic CRUD(Create, Read,
 * Update, Delete) operations on a repository of a specific type. It provides
 * several methods out of the box for interacting with a database.
 *
 * @param LastActionIdValue - the domain type the repository manages
 * @param Long -the type of the id of the entity the repository manages
 */
public interface LastActionValueDao extends CrudRepository<LastActionIdValue, Long> {

}
