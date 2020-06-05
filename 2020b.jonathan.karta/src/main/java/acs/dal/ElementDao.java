package acs.dal;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import acs.data.ElementEntity;

public interface ElementDao extends PagingAndSortingRepository<ElementEntity, Long> {

	public List<ElementEntity> findAllByNameLike(@Param("name") String name, Pageable pageable);

	public List<ElementEntity> findAllByActiveAndNameLike(@Param("active") boolean active, @Param("name") String name,
			Pageable pageable);

	public List<ElementEntity> findAllByType(@Param("type") String type, Pageable pageable);

	public List<ElementEntity> findAllByTypeLike(@Param("type") String type, Pageable pageable);

	public List<ElementEntity> findAllByTypeNotLike(@Param("type") String type, Pageable pageable);

	public List<ElementEntity> findAllByActiveAndTypeNotLike(@Param("active") boolean active,
			@Param("type") String type, Pageable pageable);

	public List<ElementEntity> findAllByTypeAndName(@Param("type") String type, @Param("name") String name,
			Pageable pageable);

	public List<ElementEntity> findAllByActiveAndType(@Param("active") boolean active, @Param("type") String type,
			Pageable pageable);

	public List<ElementEntity> findAllByActiveAndTypeLike(@Param("active") boolean active, @Param("type") String type,
			Pageable pageable);

	public List<ElementEntity> findAllByActive(@Param("active") boolean active, Pageable pageable);

	public List<ElementEntity> findAllByActive(@Param("active") boolean active);

	public List<ElementEntity> findAllByParents(@Param("parents") ElementEntity parents, Pageable pageable);

	public List<ElementEntity> findAllByActiveAndParents(@Param("active") boolean active,
			@Param("parents") ElementEntity parents, Pageable pageable);

	public List<ElementEntity> findAllByChildrens(@Param("childrens") ElementEntity childrens, Pageable pageable);

	public List<ElementEntity> findAllByActiveAndChildrens(@Param("active") boolean active,
			@Param("childrens") ElementEntity childrens, Pageable pageable);

	public List<ElementEntity> findAllByActiveAndLocation_latBetweenAndLocation_lngBetween(
			@Param("active") boolean active, @Param("minLat") double minLat, @Param("maxLat") double maxLat,
			@Param("minLng") double minLng, @Param("maxLng") double maxLng);
}
