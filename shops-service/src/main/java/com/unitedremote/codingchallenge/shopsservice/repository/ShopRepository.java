package com.unitedremote.codingchallenge.shopsservice.repository;

import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * This interface is the connector between {@link Shop} model and MongoDB's shop collection.
 * <br>
 * This interface will extend the <code>MongoRepository</code> class, which already contains generic methods like save
 * (for creating/updating documents) and delete (for removing documents).
 * @author Ayoub Khial
 * @see MongoRepository
 * @see User
 */
@RepositoryRestResource
public interface ShopRepository extends MongoRepository<Shop, String>, CustomShopRepository {

    @Query("{location : {$near : {$geometry : {type: 'Point',coordinates : [?0, ?1]}}}}")
    Page<Shop> sortedShopsByNearest(Pageable pageable, double longitude, double latitude);
}
