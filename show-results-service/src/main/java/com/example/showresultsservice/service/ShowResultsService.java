package com.example.showresultsservice.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowResultsService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ShowResultsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Document> retrieveAndShowAnalytics() {
        List<Document> results = new ArrayList<>();

        try {
            MongoDatabase database = mongoTemplate.getDb();

            MongoCollection<Document> collection = database.getCollection("Mongo");
            MongoCursor<Document> cursor = collection.find()
                    .sort(Sorts.descending("_id"))
                    .iterator();

            while (cursor.hasNext()) {
                Document document = cursor.next();
                results.add(document);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}
