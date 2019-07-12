package ir.adicom.app.soccermanagerapp.data;

import java.util.List;

import ir.adicom.app.soccermanagerapp.App;
import ir.adicom.app.soccermanagerapp.model.Match;
import ir.adicom.app.soccermanagerapp.model.MatchDao;
import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.PlayerDao;
import ir.adicom.app.soccermanagerapp.model.Table;
import ir.adicom.app.soccermanagerapp.model.TableDao;
import ir.adicom.app.soccermanagerapp.model.Team;
import ir.adicom.app.soccermanagerapp.model.TeamDao;

/**
 * Created by adicom on 4/14/18.
 */

public class LocalData {
    public  int playerSize;
    public  Team[] teams;
    private App app;

    public  void init(App application) {
        this.app = application;
        playerSize = 11;
        teams = new Team[App.size];
//        players = new Player[size * playerSize];
//        matches = new Match[size * (size - 1)];
    }

    public  void create(String team) {
        TeamDao teamDao = app.getDaoSession().getTeamDao();
        PlayerDao playerDao = app.getDaoSession().getPlayerDao();
        TableDao tableDao = app.getDaoSession().getTableDao();

        teams[0] = new Team();
        teams[0].setName(team);
        teams[0].setDiv(0);
        teams[0].setGroup(0);
        teamDao.insert(teams[0]);

        for (int i = 1; i < App.size; i++) {
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
        App.teamId = teamList.get(0).getId();
        App.teamName = teamList.get(0).getName();
        for (int i = 0; i < teamList.size(); i++) {
            Table table = new Table();
            table.setTeamId(teamList.get(i).getId());
            table.setWin(0);
            table.setLose(0);
            table.setDraw(0);
            table.setGf(0);
            table.setGa(0);
            table.setPts(0);
            table.setDiv(1);
            table.setGroup(1);
            tableDao.insert(table);

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
                player.setShirtNumber(j + 1);

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
        MatchDao matchDao = app.getDaoSession().getMatchDao();
        TeamDao teamDao = app.getDaoSession().getTeamDao();
        List<Team> teamList = teamDao.loadAll();
        Match match;
        long[] schedule = new long[App.size];
        for (int i = 0; i < schedule.length; i++) {
            schedule[i] = teamList.get(i).getId();
        }
        for (int week = 0; week < (App.size - 1) * 2; week++) {
            for (int matchCounter = 0; matchCounter < App.size / 2; matchCounter++) {
                match = new Match();
                match.setWeekId(week + 1);
                match.setGoalTeamHome(-1);
                match.setGoalTeamAway(-1);
                if (week < App.size - 1) {
                    match.setTeamAwayId(schedule[App.size - 1 - matchCounter]);
                    match.setTeamHomeId(schedule[matchCounter]);
                } else {
                    match.setTeamHomeId(schedule[App.size - 1 - matchCounter]);
                    match.setTeamAwayId(schedule[matchCounter]);
                }
                matchDao.insert(match);
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
