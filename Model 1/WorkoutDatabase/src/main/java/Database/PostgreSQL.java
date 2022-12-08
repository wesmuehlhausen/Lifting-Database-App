package Database;

import Helper.PasswordHelper;
import ProjectObjects.Exercise;
import ProjectObjects.User;
import ProjectObjects.Workout;

import java.sql.*;
import java.util.ArrayList;

public class PostgreSQL {

    //Member variable that holds the connection to the sql server
    private Connection connection;

    //Constructor
    public PostgreSQL(){
        connectToDatabase();
    }

    // Default Connects to database and stores connection object in "connection" variable
    public Connection connectToDatabase(){

        //jdbc:postgresql://<database_host>:<port>/<database_name>
        //jdbc:postgresql://localhost/dvdrental

        final String pref = "jdbc:postgresql://localhost";
        final String port = "5433";
        final String name = "LiftingDatabase";
        final String user = "wes";
        final String pass = "wes";

        String url = pref + ":" + port + "/" + name;

        try{
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to the PostgreSQL server successfully" + "...");
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    //Connects to database and stores connection object in "connection" variable
    public Connection connectToDatabase(String urlPrefix, String port, String database, String username, String password){

        //jdbc:postgresql://<database_host>:<port>/<database_name>
        //jdbc:postgresql://localhost/dvdrental

        String url = urlPrefix + ":" + port + "/" + database;

        try{
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    //Create workout in database
    public Workout createWorkout(Workout workout){
        String query = "INSERT INTO public.\"Workouts\"(username_fk, date, type, target, notes) " +
                                            "VALUES (?, ?, ?, ?, ?)";

        try {
            //Setup prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, workout.getUsername_fk());
            preparedStatement.setString(2, workout.getDate());
            preparedStatement.setString(3, workout.getType());
            preparedStatement.setString(4, workout.getTarget());
            preparedStatement.setString(5, workout.getNotes());

            int affectedRows = preparedStatement.executeUpdate();//Execute Query

            //Get generated username ID
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        workout.setWorkout_id(String.valueOf(id));
                        System.out.println("Added workout " + id + "...");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage() + "...");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workout;
    }


    //Create exercise in database
    public boolean createExercise(Exercise exercise){
        String query = "INSERT INTO public.\"Exercise\"(username_fk, workout_fk, exercise, sets, reps, weight, single, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            //Setup prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            //Add parameters to statement
            ArrayList<String> fields = exercise.getValues();
            for(int i = 0; i < fields.size(); ++i){
                if(i == 1)
                    preparedStatement.setLong(i+1, Long.parseLong(fields.get(i)));
                else
                    preparedStatement.setString(i+1, fields.get(i));
            }
            int affectedRows = preparedStatement.executeUpdate();//Execute Query

            //Get generated username ID
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        exercise.setExercise_id(String.valueOf(id));
                        System.out.println("Added exercise " + id + "...");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    return false;
                }
                return true;
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        System.out.println("Failed to add exercise");
        return false;
    }

    //Delete exercise in database
    public boolean deleteExercise(String exercise_id){
        //If the username does not exist, create it
        String query = "DELETE FROM public.\"Exercise\" WHERE exercise_id = ?";
        if(exerciseExists(exercise_id) == true){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, Long.parseLong(exercise_id));
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Deleted exercise: " + exercise_id + "...");
            return true;//Successful delete
        }
        System.out.println("Failed to delete exercise: " + exercise_id + "...");
        return false;//Default fail
    }

    //Delete workout in database
    public boolean deleteWorkout(String workout_id){
        //If the username does not exist, create it
        String query = "DELETE FROM public.\"Workouts\" WHERE workout_id = ?";
        if(workoutExists(workout_id) == true){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, Long.parseLong(workout_id));
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Deleted workout: " + workout_id + "...");
            return true;//Successful delete
        }
        System.out.println("Failed to delete workout: " + workout_id + "...");
        return false;//Default fail
    }

    //For login purposes. we hash the inputted password, salt, and hash again and compare with database password to validate user
    public boolean validateUserLogin(String username_id, String password){

        if(userExists(username_id) == true){
            String query = "SELECT salt FROM public.\"User\" WHERE username_id = ?";
            try {
                //Get Salt associated with username
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username_id);
                ResultSet results = preparedStatement.executeQuery();
                results.next();
                String salt = results.getString(1);

                //Generate password hash based on what their password is
                String hashedPassword = PasswordHelper.hashPassword(password, salt);

                //Get the actual password so we can compare
                String passwordQuery = "SELECT password_hash FROM public.\"User\" WHERE username_id = ?";
                preparedStatement = connection.prepareStatement(passwordQuery);
                preparedStatement.setString(1, username_id);
                results = preparedStatement.executeQuery();
                results.next();
                String actualPassword = results.getString(1);

                //Compare passwords
                if(hashedPassword.equals(actualPassword)){
                    System.out.println("Password validated" + "...");
                    return true;
                }
                else {
                    System.out.println("Incorrect password" + "...");
                    return false;
                }

            } catch (SQLException ex) {

                System.out.println("Error validating login" + "...");
                return false;
            }
        }
        else{
            System.out.println("Username " + username_id + " does not exist");
            return false;
        }
    }

    //Deletes user if user exists
    public boolean deleteUser(User user){

        //If the username does not exist, create it
        String query = "DELETE FROM public.\"User\" WHERE username_id = ?";
        if(userExists(user) == true){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, user.getUsername_id());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Deleted User: " + user.getUsername_id() + "...");
            return true;//Successful delete
        }
        System.out.println("Failed to delete User: " + user.getUsername_id() + "...");
        return false;//Default fail
    }

    //Retrieves name associated with username in database
    public String getName(String username_id){
        //If the username does not exist, create it
        String query = "SELECT name FROM public.\"User\" WHERE username_id = ?";
        String name = "";
        if(userExists(username_id) == true){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username_id);
                ResultSet results = preparedStatement.executeQuery();
                results.next();
                name = results.getString(1);
                System.out.println(name);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Found user...");
            return name;//Successful delete
        }
        System.out.println("Couldn't find user...");
        return name;//Default fail
    }

    //Created a user if that user doesn't already exist
    public boolean createUser(User user, String password) {

        //If the username does not exist, create it
        String query = "INSERT INTO public.\"User\"( username_id, name, password_hash, salt) VALUES (?, ?, ?, ?)";
        if(userExists(user) == false){
            try {
                //Setup prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, user.getUsername_id());//Set username
                preparedStatement.setString(2, user.getName());//Set name

                //Generate salt for user
                String salt = PasswordHelper.getSalt();
                preparedStatement.setString(4, salt);//Set salt

                //Generate hashed password for user based on input password and salt
                String generatePassword = PasswordHelper.hashPassword(password, salt);
                preparedStatement.setString(3, generatePassword);//Set password

                //Execute Query
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Added new user " + user.getUsername_id() + "...");
            return true;//Successful add
        }
        else {
            System.out.println("User already created...");
        }

        return false;//Default fail
    }

    //Helper for getting connection
    public Connection getConnection() {
        return connection;
    }

    //Helper for setting connection
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    //Private helper that checks if the username exists in the database
    private boolean userExists(User user){
        //Check if the username already exists
        String checkQuery = "SELECT count(*) FROM public.\"User\" WHERE username_id = ?";//String check_query = "SELECT count(*) FROM public.\"User\" WHERE username_id = \"ActualBuffalo75\"";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, user.getUsername_id());
            ResultSet results = preparedStatement.executeQuery();
            results.next();
            if(results.getInt(1) > 0){
                System.out.println("Username found...");
                return true;
            }
            else
                return false;//No one found with that username
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;//default fail
    }

    //Private helper that checks if the username exists in the database
    public boolean userExists(String username_id){
        //Check if the username already exists
        String checkQuery = "SELECT count(*) FROM public.\"User\" WHERE username_id = ?";//String check_query = "SELECT count(*) FROM public.\"User\" WHERE username_id = \"ActualBuffalo75\"";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, username_id);
            ResultSet results = preparedStatement.executeQuery();
            results.next();
            if(results.getInt(1) > 0)
                return true;
            else
                return false;//No one found with that username
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;//default fail
    }

    //Private helper that checks if the username exists in the database
    private boolean workoutExists(String workout_id){
        //Check if the username already exists
        String checkQuery = "SELECT count(*) FROM public.\"Workouts\" WHERE workout_id = ?";//String check_query = "SELECT count(*) FROM public.\"User\" WHERE username_id = \"ActualBuffalo75\"";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setLong(1, Long.parseLong(workout_id));
            ResultSet results = preparedStatement.executeQuery();
            results.next();
            if(results.getInt(1) > 0)
                return true;
            else
                return false;//No one found with that username
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;//default fail
    }

    //Helper to determine if exercise exists
    private boolean exerciseExists(String exercise_id){
        //Check if the username already exists
        String checkQuery = "SELECT count(*) FROM public.\"Exercise\" WHERE exercise_id = ?";//String check_query = "SELECT count(*) FROM public.\"User\" WHERE username_id = \"ActualBuffalo75\"";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setLong(1, Long.parseLong(exercise_id));
            ResultSet results = preparedStatement.executeQuery();
            results.next();
            if(results.getInt(1) > 0)
                return true;
            else
                return false;//No one found with that username
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;//default fail
    }
}
