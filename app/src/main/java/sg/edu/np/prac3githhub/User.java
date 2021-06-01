package sg.edu.np.prac3githhub;

public class User {
    public String name;
    public int id;
    public String desc;
    public boolean followed;

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public User(String name, int id, String desc, boolean followed) {
        this.name = name;
        this.id = id;
        this.desc = desc;
        this.followed = followed;
    }
}
