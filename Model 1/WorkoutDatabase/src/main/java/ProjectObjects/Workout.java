package ProjectObjects;

public class Workout {

    private String workout_id;
    private String username_fk;
    private String date;
    private String type;
    private String target;
    private String notes;

    public Workout() {
    }

    public Workout(String workout_id, String username_fk, String date, String type, String target, String notes) {
        this.workout_id = workout_id;
        this.username_fk = username_fk;
        this.date = date;
        this.type = type;
        this.target = target;
        this.notes = notes;
    }

    public String getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(String workout_id) {
        this.workout_id = workout_id;
    }

    public String getUsername_fk() {
        return username_fk;
    }

    public void setUsername_fk(String username_fk) {
        this.username_fk = username_fk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}


