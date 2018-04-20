package ir.adicom.app.soccermanagerapp.model;

/**
 * Created by adicom on 4/20/18.
 */

public class Matches {
    private int id;
    private int teamHome;
    private int teamAway;
    private int goalTeamHome;
    private int goalTeamAway;
    private int weekId;

    public Matches() {}

    public Matches(int goalTeamAway, int goalTeamHome, int id, int teamAway, int teamHome, int weekId) {
        this.goalTeamAway = goalTeamAway;
        this.goalTeamHome = goalTeamHome;
        this.id = id;
        this.teamAway = teamAway;
        this.teamHome = teamHome;
        this.weekId = weekId;
    }

    public int getGoalTeamAway() {
        return goalTeamAway;
    }

    public void setGoalTeamAway(int goalTeamAway) {
        this.goalTeamAway = goalTeamAway;
    }

    public int getGoalTeamHome() {
        return goalTeamHome;
    }

    public void setGoalTeamHome(int goalTeamHome) {
        this.goalTeamHome = goalTeamHome;
    }

    public int getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(int teamAway) {
        this.teamAway = teamAway;
    }

    public int getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(int teamHome) {
        this.teamHome = teamHome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }
}
