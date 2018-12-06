package com.unitedremote.codingchallenge.shopsservice.repository.impl;

import com.mongodb.client.MongoCollection;
import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.repository.CustomShopRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * A custom shop repository to do some complicated queries using MongoTemplate
 */
public class CustomShopRepositoryImpl implements CustomShopRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public CustomShopRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * get liked shops list of the given user.
     * @param pageable page information.
     * @param userId current user id.
     * @return page of shops.
     */
    @Override
    public Page<Shop> getLikedShopsByUser(Pageable pageable, String userId) {
        List<Shop> shopList = this.getLikedOrDislikedShops("likedShops", userId);
        int start = Math.toIntExact(pageable.getOffset());
        int end = (start + pageable.getPageSize()) > shopList.size() ? shopList.size() : (start + pageable.getPageSize());
        return new PageImpl<>(shopList.subList(start, end), pageable, shopList.size());
    }

    /**
     * get disliked shops list of the given user.
     * @param pageable page information.
     * @param userId current user id.
     * @return page of shops.
     */
    @Override
    public Page<Shop> getDislikedShopsByUser(Pageable pageable, String userId) {
        List<Shop> shopList = this.getLikedOrDislikedShops("dislikedShops", userId);
        int start = Math.toIntExact(pageable.getOffset());
        int end = (start + pageable.getPageSize()) > shopList.size() ? shopList.size() : (start + pageable.getPageSize());
        return new PageImpl<>(shopList.subList(start, end), pageable, shopList.size());
    }

    private List<Shop> getLikedOrDislikedShops(String collection, String userId) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from(collection)
                .localField("_id")
                .foreignField("shop")
                .as("shops");
        UnwindOperation unwindOperation = Aggregation.unwind("shops", true);
        Aggregation aggregation = Aggregation
                .newAggregation(lookupOperation, unwindOperation, Aggregation.match(Criteria.where("shops.user")
                        .is(new ObjectId(userId))));
        return this.mongoTemplate.aggregate(aggregation, "shops", Shop.class).getMappedResults();
    }

    @Override
    public void addShopToLikedShops(String shopId, String userId) {
        Document likedShop = new Document();
        likedShop.append("user", new ObjectId(userId));
        likedShop.append("shop", new ObjectId(shopId));
        MongoCollection collection = mongoTemplate.getCollection("likedShops");
        collection.insertOne(likedShop);
    }
}
