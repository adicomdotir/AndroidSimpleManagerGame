package ir.adicom.app.soccermanagerapp.model;

/**
 * Created by adicom on 2/23/18.
 */

public class Team {
    private int id;
    private String name;
    private String nickname;

    public Team() {}

    public Team(int id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
