package acs.dal;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import acs.boundaries.UserBoundary;
import acs.data.ElementEntity;
import acs.data.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, String>{

}
