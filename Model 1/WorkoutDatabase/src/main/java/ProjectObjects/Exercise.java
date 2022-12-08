package ProjectObjects;

import java.util.ArrayList;

public class Exercise {

    private String exercise_id;
    private String username_fk;
    private String workout_fk;
    private String exercise;
    private String sets;
    private String reps;
    private String weight;
    private String single;
    private String notes;

    public Exercise() {
    }

    public Exercise(String exercise_id, String username_fk, String workout_fk, String exercise, String sets, String reps, String weight, String single, String notes) {
        this.exercise_id = exercise_id;
        this.username_fk = username_fk;
        this.workout_fk = workout_fk;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.single = single;
        this.notes = notes;
    }

    public ArrayList<String> getValues(){
        ArrayList<String> fields = new ArrayList<>();
        fields.add(username_fk);
        fields.add(workout_fk);
        fields.add(exercise);
        fields.add(sets);
        fields.add(reps);
        fields.add(weight);
        fields.add(single);
        fields.add(notes);
        return fields;
    }

    public String getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(String exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getUsername_fk() {
        return username_fk;
    }

    public void setUsername_fk(String username_fk) {
        this.username_fk = username_fk;
    }

    public String getWorkout_fk() {
        return workout_fk;
    }

    public void setWorkout_fk(String workout_fk) {
        this.workout_fk = workout_fk;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
