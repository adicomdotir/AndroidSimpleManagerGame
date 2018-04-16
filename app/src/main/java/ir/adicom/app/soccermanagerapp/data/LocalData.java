package ir.adicom.app.soccermanagerapp.data;

import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.Team;

/**
 * Created by adicom on 4/14/18.
 */

public class LocalData {
    public static final int SIZE = 4;
    public static final int PLAYER_SIZE = 10;
    public static Team[] teams = new Team[SIZE];
    public static Player[] players = new Player[SIZE * PLAYER_SIZE];

    public static void create(String team) {
        teams[0] = new Team(1, team, "nothing", 0);

        for (int i = 1; i < SIZE; i++) {
            int n = (int) (Math.random() * FirstData.teamNames.length);
            String teamName = FirstData.teamNames[n];
            teams[i] = new Team(i + 1, teamName, "nothing", 0);
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < PLAYER_SIZE; j++) {
                Player player = new Player();
                player.setId(i * PLAYER_SIZE + j + 1);
                player.setTeamId(i + 1);
                int n = (int) (Math.random() * FirstData.firstNames.length);
                String fullName = FirstData.firstNames[n];
                n = (int) (Math.random() * FirstData.lastNames.length);
                fullName += " " + FirstData.lastNames[n];
                player.setName(fullName);
                player.setAge((int) (Math.random() * 17 + 17));
                player.setGoalkeeper((float) (Math.random() * 20) + 1);
                player.setScoring((float) (Math.random() * 20) + 1);
                players[i * PLAYER_SIZE + j] = player;
            }
        }
    }
}
