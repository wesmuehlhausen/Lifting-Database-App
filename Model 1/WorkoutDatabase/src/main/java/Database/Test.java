package Database;

import Helper.PasswordHelper;
import ProjectObjects.Exercise;
import ProjectObjects.User;
import ProjectObjects.Workout;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        PostgreSQL postgreSQL = new PostgreSQL();

//        User user = new User("ArnoldLifter456", "Arnold S.");
//        postgreSQL.createUser(user, "password");
//        Workout w1 = new Workout(null, user.getUsername_id(), "02/20/2000", "Push", "Strength", "This was a super hard workout");
//        postgreSQL.createWorkout(w1);
//        Exercise exercise = new Exercise(null, w1.getUsername_fk(), w1.getWorkout_id(), "Barbell Bench Press", "3", "12", "225", "FALSE", "Supersetted with dumbbells");
//        postgreSQL.createExercise(exercise);
//        postgreSQL.deleteExercise(exercise.getExercise_id());
//        postgreSQL.deleteExercise(exercise.getExercise_id());

//        User user = new User("ArnoldLifter123", "Arnold S.");
//        postgreSQL.createUser(user, "password");
//        Workout w1 = new Workout(null, user.getUsername_id(), "02/20/2000", "Push", "Strength", "This was a super hard workout");
//        Exercise e1 = new Exercise(null, w1.getUsername_fk(), w1.getWorkout_id(), "Barbell Bench Press", "3", "12", "225", "FALSE", "Supersetted with dumbbells");
//        postgreSQL.createWorkout(w1);
//        postgreSQL.deleteWorkout("5");

//        User user = new User("ActualBuffalo99", "wes");
////        postgreSQL.createUser(user, "wes");
//        postgreSQL.validateUserLogin("ActualBuffalo99", "wess");

    }
}
