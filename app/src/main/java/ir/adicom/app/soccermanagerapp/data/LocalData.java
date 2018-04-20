package ir.adicom.app.soccermanagerapp.data;

import ir.adicom.app.soccermanagerapp.model.Match;
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
    public static final Match[] MATCHES = new Match[SIZE * (SIZE - 1)];


    public static void create(String team) {
        teams[0] = new Team(1, team, "nothing", 0);

        for (int i = 1; i < SIZE; i++) {
            int n = (int) (Math.random() * FirstData.TEAM_NAMES.length);
            String teamName = FirstData.TEAM_NAMES[n];
            teams[i] = new Team(i + 1, teamName, "nothing", 0);
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < PLAYER_SIZE; j++) {
                Player player = new Player();
                player.setId(i * PLAYER_SIZE + j + 1);
                player.setTeamId(i + 1);
                int n = (int) (Math.random() * FirstData.FIRST_NAMES.length);
                String fullName = FirstData.FIRST_NAMES[n];
                n = (int) (Math.random() * FirstData.LAST_NAMES.length);
                fullName += " " + FirstData.LAST_NAMES[n];
                player.setName(fullName);
                player.setAge((int) (Math.random() * 17 + 17));
                player.setGoalkeeper((float) (Math.random() * 20) + 1);
                player.setScoring((float) (Math.random() * 20) + 1);
                players[i * PLAYER_SIZE + j] = player;
            }
        }
    }

    public static void drawSchedule() {
        int[] schedule = new int[SIZE];
        for (int i = 0; i < schedule.length; i++) {
            schedule[i] = i;
        }
        for (int i = 0; i < LocalData.SIZE * 2 - 2; i++) {
            for (int j = 0; j < LocalData.SIZE / 2; j++) {
                MATCHES[(i * (LocalData.SIZE / 2)) + j] = new Match();
                MATCHES[(i * (LocalData.SIZE / 2)) + j].setId((i * (LocalData.SIZE / 2)) + j + 1);
                MATCHES[(i * (LocalData.SIZE / 2)) + j].setWeekId(i + 1);
                MATCHES[(i * (LocalData.SIZE / 2)) + j].setGoalTeamHome(-1);
                MATCHES[(i * (LocalData.SIZE / 2)) + j].setGoalTeamAway(-1);
                if (i < LocalData.SIZE - 1) {
                    MATCHES[(i * (LocalData.SIZE / 2)) + j].setTeamAway(schedule[LocalData.SIZE - 1 - j]);
                    MATCHES[(i * (LocalData.SIZE / 2)) + j].setTeamHome(schedule[j]);
                } else {
                    MATCHES[(i * (LocalData.SIZE / 2)) + j].setTeamHome(schedule[LocalData.SIZE - 1 - j]);
                    MATCHES[(i * (LocalData.SIZE / 2)) + j].setTeamAway(schedule[j]);
                }
            }
            int temp = -1;
            for (int k = 1; k < schedule.length; k++) {
                if (k == 1) {
                    temp = schedule[k];
                    schedule[k] = schedule[schedule.length - 1];
                } else {
                    int t = schedule[k];
                    schedule[k] = temp;
                    temp = t;
                }
            }
        }
    }
}
