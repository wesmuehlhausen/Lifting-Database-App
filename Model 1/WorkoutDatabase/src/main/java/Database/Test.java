package Database;

public class Test {
    public static void main(String[] args) {
        PostgreSQL postgreSQL = new PostgreSQL();

        postgreSQL.connectToDatabase();
        postgreSQL.createUser("ActualBuffalo75", "Wesley");
        postgreSQL.createUser("ActualBuffalo76", "Poopy");


    }
}
