package com.unitedremote.codingchallenge.shopsservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * This class is used to avoid the extra columns added by spring boot into collections.
 * @author Ayoub Khial
 */
@Configuration
public class MongoConfig {

    private MongoDbFactory mongoDbFactory;
    private MongoMappingContext mongoMappingContext;

    @Autowired
    public MongoConfig(MongoDbFactory mongoDbFactory, MongoMappingContext mongoMappingContext) {
        this.mongoDbFactory = mongoDbFactory;
        this.mongoMappingContext = mongoMappingContext;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() {

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(this.mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }
}