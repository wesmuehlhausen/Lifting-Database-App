package ProjectObjects;

public class User {

    private String username_id;
    private String name;

    public User(String username_id, String name) {
        this.username_id = username_id;
        this.name = name;
    }

    public String getUsername_id() {
        return username_id;
    }

    public void setUsername_id(String username_id) {
        this.username_id = username_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
