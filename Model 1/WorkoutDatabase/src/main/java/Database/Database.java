package Database;

import com.mongodb.BasicDBObject;
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

public class Database {

    private final String USERNAME;
    private final String password;
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection = null;
    public final String DATABASE_NAME = "LiftingDataBase";
    public final String[] TABLE_NAMES = {"id", "day", "type", "target", "exercise", "sets", "reps", "weight", "single", "notes"};


    public Database(){
        USERNAME = "testUser";
        password = "reallystrongpassword";
    }

    public Database(String username, String password){
        this.USERNAME = username;
        this.password = password;


    }

    public boolean connectToDatabase(){
        String connection = "mongodb+srv://" + USERNAME + ":" + password +  "@cluster0.o1nhs5j.mongodb.net/?retryWrites=true&w=majority";
        String collectionName = USERNAME + "-lifts";

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
                return false;
            }
        } catch (MongoException me) {
            System.err.println("An error occurred while attempting to to connect to the server: " + me);
            return false;
        }
        return true;
    }

    public void addLiftData(String[] tableValue){
        Document entry = new Document("key", "value");
        for(int i = 0; i < TABLE_NAMES.length; ++i)
            entry.append(TABLE_NAMES[i], tableValue[i]);//Add KV pair
        try {
            collection.insertOne(entry);
        } catch (MongoException me) {
            System.err.println("An  error occurred while attempting enter value into database: " + me);
        }
    }

    //ONLY WORKS ON LOCAL DB NOT CLOUD DB
    public void createUser(String username, String password, String databaseName){
        BasicDBObject createUserCmd = new BasicDBObject("createUser", username) // (3)
                .append("pwd", password)
                .append("roles",
                        Collections.singletonList( // (4)
                                new BasicDBObject(
                                        "role", "readWrite").append("db", databaseName)
                        ));
        database.runCommand(createUserCmd); // (5)
    }
}
