package acs.dal;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import acs.data.ElementEntity;

public interface ElementDao extends PagingAndSortingRepository<ElementEntity, Long> {
	/**
	 * This method gets all elements from the database which has name like {name}
	 * 
	 * @param name     This is the name of the element to look for in the database.
	 * @param pageable This is an abstract interface for pagination information.
	 * @return List<ElementEntity> This is a list of all elements found that have
	 *         substring of the requested name
	 */
	public List<ElementEntity> findAllByNameLike(@Param("name") String name, Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByActiveAndNameLike(@Param("active") boolean active, @Param("name") String name,
			Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByType(@Param("type") String type, Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByTypeLike(@Param("type") String type, Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByTypeNotLike(@Param("type") String type, Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByActiveAndTypeNotLike(@Param("active") boolean active,
			@Param("type") String type, Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByTypeAndName(@Param("type") String type, @Param("name") String name,
			Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByActiveAndType(@Param("active") boolean active, @Param("type") String type,
			Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByActiveAndTypeLike(@Param("active") boolean active, @Param("type") String type,
			Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByActive(@Param("active") boolean active, Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByActive(@Param("active") boolean active);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByParents(@Param("parents") ElementEntity parents, Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByActiveAndParents(@Param("active") boolean active,
			@Param("parents") ElementEntity parents, Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByChildrens(@Param("childrens") ElementEntity childrens, Pageable pageable);

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List<ElementEntity> findAllByActiveAndChildrens(@Param("active") boolean active,
			@Param("childrens") ElementEntity childrens, Pageable pageable);
	
	public List<ElementEntity> findAllByActiveAndLocation_latBetweenAndLocation_lngBetween(
			@Param("active") boolean active,
			@Param("minLat") double minLat,
			@Param("maxLat") double maxLat, @Param("minLng") double minLng, @Param("maxLng") double maxLng);
}
