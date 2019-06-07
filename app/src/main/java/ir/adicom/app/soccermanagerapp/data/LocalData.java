package ir.adicom.app.soccermanagerapp.data;

import ir.adicom.app.soccermanagerapp.model.Match;
import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.Team;

/**
 * Created by adicom on 4/14/18.
 */

public class LocalData {
    public static int weekIndex;
    public static int size;
    public static int playerSize;
    public static Team[] teams;
    public static Player[] players;
    public static  Match[] matches;

    public static void init() {
        weekIndex = 1;
        size = 8;
        playerSize = 18;
        teams = new Team[size];
        players = new Player[size * playerSize];
        matches = new Match[size * (size - 1)];
    }

    public static void create(String team) {
        teams[0] = new Team(1, team, "nothing", 0);

        for (int i = 1; i < size; i++) {
            int tnf = (int) (Math.random() * FirstData.TEAM_NAMES_FIRST.length);
            int tns = (int) (Math.random() * FirstData.TEAM_NAMES_SECOND.length);
            String teamName = FirstData.TEAM_NAMES_FIRST[tnf] + " " + FirstData.TEAM_NAMES_SECOND[tns];
            boolean exist = false;
            for (Team t : teams) {
                if (t != null && t.getName().equals(teamName)) {
                    exist = true;
                }
            }
            if (!exist) {
                teams[i] = new Team(i + 1, teamName, "nothing", 0);
            } else {
                i--;
            }
        }

        for (int i = 0; i < size; i++) {
            int overall = 0;
            for (int j = 0; j < playerSize; j++) {
                Player player = new Player();
                player.setId(i * playerSize + j + 1);
                player.setTeamId(i + 1);

                // Generate FULLNAME contain one FIRSTNAME and one LASTNAME
                int n = (int) (Math.random() * FirstData.FIRST_NAMES.length);
                String fullName = FirstData.FIRST_NAMES[n];
                n = (int) (Math.random() * FirstData.LAST_NAMES.length);
                fullName += " " + FirstData.LAST_NAMES[n];
                player.setAge((int) (Math.random() * 17 + 17));
                player.setAgeSublevel((int) (Math.random() * 111 + 1));
                player.setShirtNumber((int) (Math.random() * 99 + 1));

                player.setName(fullName);
                if (j < 2) {
                    player.setGoalkeeper((float) (Math.random() * 9) + 2);
                    player.setScoring((float) (Math.random() * 1) + 1);
                    player.setDefending((float) (Math.random() * 1) + 1);
                } else {
                    player.setGoalkeeper((float) (Math.random() * 1) + 1);
                    player.setScoring((float) (Math.random() * 9) + 2);
                    player.setDefending((float) (Math.random() * 9) + 2);
                }

                player.setStamina(8);
                player.setMorale(8);
                players[i * playerSize + j] = player;

                overall += (player.getGoalkeeper() + player.getScoring() + player.getDefending()) / 3;
            }
            teams[i].setOverral(overall / playerSize);
        }
    }

    public static void drawSchedule() {
        int[] schedule = new int[size];
        for (int i = 0; i < schedule.length; i++) {
            schedule[i] = i;
        }
        for (int i = 0; i < LocalData.size * 2 - 2; i++) {
            for (int j = 0; j < LocalData.size / 2; j++) {
                matches[(i * (LocalData.size / 2)) + j] = new Match();
                matches[(i * (LocalData.size / 2)) + j].setId((i * (LocalData.size / 2)) + j + 1);
                matches[(i * (LocalData.size / 2)) + j].setWeekId(i + 1);
                matches[(i * (LocalData.size / 2)) + j].setGoalTeamHome(-1);
                matches[(i * (LocalData.size / 2)) + j].setGoalTeamAway(-1);
                if (i < LocalData.size - 1) {
                    matches[(i * (LocalData.size / 2)) + j].setTeamAway(schedule[LocalData.size - 1 - j]);
                    matches[(i * (LocalData.size / 2)) + j].setTeamHome(schedule[j]);
                } else {
                    matches[(i * (LocalData.size / 2)) + j].setTeamHome(schedule[LocalData.size - 1 - j]);
                    matches[(i * (LocalData.size / 2)) + j].setTeamAway(schedule[j]);
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
