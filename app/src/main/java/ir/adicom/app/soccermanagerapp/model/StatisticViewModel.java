package ir.adicom.app.soccermanagerapp.model;

/**
 *
 * Created by adicom on 8/2/19.
 */

public class StatisticViewModel {
    private int goalNumber;
    private String playerName;
    private String teamName;

    public StatisticViewModel() {}

    public StatisticViewModel(int goalNumber, String playerName, String teamName) {
        this.goalNumber = goalNumber;
        this.playerName = playerName;
        this.teamName = teamName;
    }

    public int getGoalNumber() {
        return goalNumber;
    }

    public void setGoalNumber(int goalNumber) {
        this.goalNumber = goalNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
