package ir.adicom.app.soccermanagerapp.model;

/**
 * Created by adicom on 2/23/18.
 */

public class Player {
    private int id;
    private int teamId;
    private String name;
    private float power;
    private int injury;
    private int morale;

    public Player() {}

    public Player(int id, int injury, int morale, String name, float power, int teamId) {
        this.id = id;
        this.injury = injury;
        this.morale = morale;
        this.name = name;
        this.power = power;
        this.teamId = teamId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInjury() {
        return injury;
    }

    public void setInjury(int injury) {
        this.injury = injury;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
