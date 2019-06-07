package ir.adicom.app.soccermanagerapp.model;

/**
 * Created by adicom on 2/23/18.
 */

public class Player {
    private int id;
    private int teamId;
    private String name;
    private float scoring;
    private float goalkeeper;
    private float defending;
    private int injury;
    private int morale;
    private int stamina;
    private int age;
    private int ageSublevel;
    private int shirtNumber;

    public Player() {}

    public Player(int id, String name, int teamId) {
        this.id = id;
        this.name = name;
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

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public float getGoalkeeper() {
        return goalkeeper;
    }

    public void setGoalkeeper(float goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    public float getScoring() {
        return scoring;
    }

    public void setScoring(float scoring) {
        this.scoring = scoring;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getDefending() {
        return defending;
    }

    public void setDefending(float defending) {
        this.defending = defending;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getAgeSublevel() {
        return ageSublevel;
    }

    public void setAgeSublevel(int ageSublevel) {
        this.ageSublevel = ageSublevel;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }
}
