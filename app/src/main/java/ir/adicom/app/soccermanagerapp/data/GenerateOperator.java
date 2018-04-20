package ir.adicom.app.soccermanagerapp.data;

import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.Team;

/**
 * Created by adicom on 2/23/18.
 */

public class GenerateOperator {

    public void init() {
    }

    public Team generateTeam(int teamId) {
        Team team = new Team();
        String name = FirstData.TEAM_NAMES[(int) (Math.random() * FirstData.TEAM_NAMES.length)];
        team.setName(name);
        team.setNickname(name.charAt(0) + "");
        System.out.println(team.getName());
        return team;
    }

    public Player generatePlayer(int teamId) {
        Player player = new Player();
        player.setTeamId(teamId);
//        player.setPower((float) Math.random() * 10 + 1);
        String name = FirstData.FIRST_NAMES[(int) (Math.random() * FirstData.FIRST_NAMES.length)] +
                " " + FirstData.LAST_NAMES[(int) (Math.random() * FirstData.LAST_NAMES.length)];
        player.setName(name);

        return player;
    }
}
