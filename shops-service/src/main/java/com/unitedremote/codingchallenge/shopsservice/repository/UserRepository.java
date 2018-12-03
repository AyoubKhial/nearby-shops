package com.unitedremote.codingchallenge.shopsservice.repository;

import com.unitedremote.codingchallenge.shopsservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * This interface is the connector between {@link User} model and MongoDB's user collection.
 * <br>
 * This interface will extend the <code>MongoRepository</code> class, which already contains generic methods like save
 * (for creating/updating documents) and delete (for removing documents).
 * @author Ayoub Khial
 * @see MongoRepository
 * @see User
 */
@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
