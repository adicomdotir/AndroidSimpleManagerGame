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
        String name = FirstData.teamNames[(int) (Math.random() * FirstData.teamNames.length)];
        team.setName(name);
        team.setNickname(name.charAt(0) + "");
        System.out.println(team.getName());
        return team;
    }

    public Player generatePlayer(int teamId) {
        Player player = new Player();
        player.setTeamId(teamId);
//        player.setPower((float) Math.random() * 10 + 1);
        String name = FirstData.firstNames[(int) (Math.random() * FirstData.firstNames.length)] +
                " " + FirstData.lastNames[(int) (Math.random() * FirstData.lastNames.length)];
        player.setName(name);

        return player;
    }
}
