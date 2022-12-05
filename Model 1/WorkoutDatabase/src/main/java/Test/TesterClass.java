package Test;

import com.mongodb.AuthenticationMechanism;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Collections;

public class TesterClass {
    public static void main(String[] args) {
        //final String USERNAME = ;
        //final String password;
        MongoClient client;
        MongoDatabase database;
        MongoCollection<Document> collection = null;
        final String DATABASE_NAME = "LiftingDataBaseeeeeee";
        String connection = "mongodb://localhost:27017";
        String collectionName = "-lifts";

        try {
            client = MongoClients.create(connection);//Set client
            database = client.getDatabase(DATABASE_NAME);//create database
            collection = database.getCollection(collectionName);//Create collection for specific user
            try {//Test the connection and see if login works
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Connected successfully to server.");
            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to connect to the server: " + me);
            }
        } catch (MongoException me) {
            System.err.println("An error occurred while attempting to to connect to the server: " + me);
        }

        Document entry = new Document("key", "value");
        if(connection != null)
            collection.insertOne(entry);

    }}

