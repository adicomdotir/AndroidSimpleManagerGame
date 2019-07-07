package ir.adicom.app.soccermanagerapp.data;

import java.util.ArrayList;
import java.util.List;

import ir.adicom.app.soccermanagerapp.App;
import ir.adicom.app.soccermanagerapp.model.Match;
import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.PlayerDao;
import ir.adicom.app.soccermanagerapp.model.Team;
import ir.adicom.app.soccermanagerapp.model.TeamDao;

/**
 * Created by adicom on 4/14/18.
 */

public class LocalData {
    public  int weekIndex;
    public static int day = 1;
    public  int size;
    public  int playerSize;
    public  Team[] teams;
    public  Player[] players;
    public  List<Player> playerList = new ArrayList<>();
    public  Match[] matches;
    private App app;

    public  void init(App application) {
        this.app = application;
        day = 1;
        weekIndex = 1;
        size = 8;
        playerSize = 11;
        teams = new Team[size];
        players = new Player[size * playerSize];
        matches = new Match[size * (size - 1)];
    }

    public  void create(String team) {
        TeamDao teamDao = app.getDaoSession().getTeamDao();
        PlayerDao playerDao = app.getDaoSession().getPlayerDao();

        teams[0] = new Team();
        teams[0].setName(team);
        teams[0].setDiv(0);
        teams[0].setGroup(0);
        teamDao.insert(teams[0]);

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
                teams[i] = new Team();
                teams[i].setName(teamName);
                teams[i].setDiv(0);
                teams[i].setGroup(0);
                teamDao.insert(teams[i]);
            } else {
                i--;
            }
        }

        List<Team> teamList = teamDao.loadAll();
        for (int i = 0; i < teamList.size(); i++) {
            for (int j = 0; j < playerSize; j++) {
                Player player = new Player();
                player.setTeamId(teamList.get(i).getId());

                // Generate FULLNAME contain one FIRSTNAME and one LASTNAME
                int n = (int) (Math.random() * FirstData.FIRST_NAMES.length);
                String fullName = FirstData.FIRST_NAMES[n];
                n = (int) (Math.random() * FirstData.LAST_NAMES.length);
                fullName += " " + FirstData.LAST_NAMES[n];
                player.setAge((int) (Math.random() * 17 + 17));
                player.setAgeSub((int) (Math.random() * 111 + 1));
                player.setShirtNumber((int) (Math.random() * 99 + 1));

                player.setName(fullName);
                if (j < 1) {
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

                playerDao.insert(player);
            }
        }
    }

    public  void drawSchedule() {

        TeamDao teamDao = app.getDaoSession().getTeamDao();
        List<Team> teamList = teamDao.loadAll();

        long[] schedule = new long[size];
        for (int i = 0; i < schedule.length; i++) {
            schedule[i] = teamList.get(i).getId();
        }
        for (int i = 0; i < size * 2 - 2; i++) {
            for (int j = 0; j < size / 2; j++) {
                matches[(i * (size / 2)) + j] = new Match();
                matches[(i * (size / 2)) + j].setWeekId(i + 1);
                matches[(i * (size / 2)) + j].setGoalTeamHome(-1);
                matches[(i * (size / 2)) + j].setGoalTeamAway(-1);
                if (i < size - 1) {
                    matches[(i * (size / 2)) + j].setTeamAwayId(schedule[size - 1 - j]);
                    matches[(i * (size / 2)) + j].setTeamHomeId(schedule[j]);
                } else {
                    matches[(i * (size / 2)) + j].setTeamHomeId(schedule[size - 1 - j]);
                    matches[(i * (size / 2)) + j].setTeamAwayId(schedule[j]);
                }
            }
            long temp = -1;
            for (int k = 1; k < schedule.length; k++) {
                if (k == 1) {
                    temp = schedule[k];
                    schedule[k] = schedule[schedule.length - 1];
                } else {
                    long t = schedule[k];
                    schedule[k] = temp;
                    temp = t;
                }
            }
        }
    }
}
