package com.unitedremote.codingchallenge.shopsservice.repository.impl;

import com.mongodb.client.MongoCollection;
import com.unitedremote.codingchallenge.shopsservice.model.Shop;
import com.unitedremote.codingchallenge.shopsservice.repository.CustomShopRepository;
import org.bson.*;
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

import java.util.Arrays;
import java.util.List;

/**
 * A custom shop repository to do some complicated queries using MongoTemplate.
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

    /**
     * Add a shop into likedShops list.
     * @param shopId the shop we want to add into likedShops.
     * @param userId current user.
     */
    @Override
    public void addShopToLikedShops(String shopId, String userId) {
        Document likedShop = new Document();
        likedShop.append("user", new ObjectId(userId));
        likedShop.append("shop", new ObjectId(shopId));
        this.mongoTemplate.insert(likedShop, "likedShops");
        //collection.insertOne(likedShop);
    }

    /**
     * Add a shop into dislikedShops list.
     * @param shopId the shop we want to add into dislikedShops.
     * @param userId current user.
     */
    @Override
    public void addShopToDislikedShops(String shopId, String userId) {
        Document dislikedShop = new Document();
        dislikedShop.append("user", new ObjectId(userId));
        dislikedShop.append("shop", new ObjectId(shopId));
        this.mongoTemplate.insert(dislikedShop, "dislikedShops");
    }

    /**
     * Remove a shop from likedShops list.
     * @param shopId the shop we want to remove from likedShops.
     * @param userId current user.
     */
    @Override
    public void removeShopFromLikedShops(String shopId, String userId) {
        MongoCollection collection = this.mongoTemplate.getCollection("likedShops");
        BsonElement userBson = new BsonElement("user", new BsonObjectId(new ObjectId(userId)));
        BsonElement shopBson = new BsonElement("shop", new BsonObjectId(new ObjectId(shopId)));
        List<BsonElement> bsonElements = Arrays.asList(userBson, shopBson);
        BsonDocument document = new BsonDocument(bsonElements);
        collection.deleteOne(document);
    }

    /**
     * Remove a shop from dislikedShops list.
     * @param shopId the shop we want to remove from dislikedShops.
     * @param userId current user.
     */
    @Override
    public void removeShopFromDislikedShops(String shopId, String userId) {
        MongoCollection collection = this.mongoTemplate.getCollection("dislikedShops");
        BsonElement userBson = new BsonElement("user", new BsonObjectId(new ObjectId(userId)));
        BsonElement shopBson = new BsonElement("shop", new BsonObjectId(new ObjectId(shopId)));
        List<BsonElement> bsonElements = Arrays.asList(userBson, shopBson);
        BsonDocument document = new BsonDocument(bsonElements);
        collection.deleteOne(document);
    }
}
