package com.example.analyticsservice.service;

import com.example.analyticsservice.db.DBConnector;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AnalyticsService {
 private static final String MONGO_CONNECTION_STRING = "mongodb://host.docker.internal:27017";
 private static final String MONGO_DATABASE_NAME = "admin";
 private static final String MONGO_COLLECTION_NAME = "Mongo";


 public static void calculateAnalytics () {
  try (Connection connection = DBConnector.getConnection()) {
   String query = "SELECT Grade FROM Grades";
   try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    try (ResultSet resultSet = preparedStatement.executeQuery()) {
     float maxGrade = Float.MIN_VALUE;
     float minGrade = Float.MAX_VALUE;
     float sum = 0;
     int count = 0;

     while (resultSet.next()) {
      float grade = resultSet.getFloat("Grade");
      maxGrade = Math.max(maxGrade, grade);
      minGrade = Math.min(minGrade, grade);
      sum += grade;
      count++;
     }

     float average = count > 0 ? sum / count : 0;

     // Write statistics to MongoDB
     try (MongoClient mongoClient = MongoClients.create(MONGO_CONNECTION_STRING)) {
      MongoDatabase database = mongoClient.getDatabase(MONGO_DATABASE_NAME);
      MongoCollection<Document> collection = database.getCollection(MONGO_COLLECTION_NAME);

      Document statisticsDocument = new Document()
              .append("maxGrade", maxGrade)
              .append("minGrade", minGrade)
              .append("average", average);

      collection.insertOne(statisticsDocument);

      System.out.println("Statistics written to MongoDB successfully!");
     }
    }
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
}
