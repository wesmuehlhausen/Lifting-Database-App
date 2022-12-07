package Database;

import java.sql.*;

public class PostgreSQL {

    private Connection connection;
    public PostgreSQL(){
        connection = null;
    }
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
            System.out.println("Connected to the PostgreSQL server successfully.");
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

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

    public boolean createUser(String username_id, String name) {

        //Check if the username already exists
        String check_query = "SELECT count(*) FROM public.\"User\" WHERE username_id =" + "'" + username_id + "'";//String check_query = "SELECT count(*) FROM public.\"User\" WHERE username_id = \"ActualBuffalo75\"";
        int count = 0;

        try {
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(check_query);
             rs.next();
             count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        //If the username does not exist, create it
        String query = "INSERT INTO public.\"User\"( username_id, name) VALUES (?, ?)";
        if(count == 0){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username_id);
                preparedStatement.setString(2, name);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Added new user" + username_id);
            return true;//Successful add
        }
        else {
            System.out.println("User already created");
        }

        return false;//Default fail
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
