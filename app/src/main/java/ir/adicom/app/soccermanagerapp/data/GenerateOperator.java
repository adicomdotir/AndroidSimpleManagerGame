package ir.adicom.app.soccermanagerapp.data;

import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.Team;

/**
 * Created by adicom on 2/23/18.
 */

public class GenerateOperator {
    private DatabaseHandler databaseHandler;

    public void init(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
        for (int i = 0; i < 22; i++) {
            databaseHandler.addPlayer(generatePlayer(1));
        }
        for (int i = 2; i < 9; i++) {
            databaseHandler.addTeam(generateTeam(i));
        }
    }

    public Team generateTeam(int teamId) {
        for (int i = 0; i < 22; i++) {
            databaseHandler.addPlayer(generatePlayer(teamId));
        }
        Team team = new Team();
        String name = LocalData.teamFirstPart[(int) (Math.random() * LocalData.teamFirstPart.length)] +
                " " + LocalData.teamLastPart[(int) (Math.random() * LocalData.teamLastPart.length)];
        team.setName(name);
        team.setNickname(name.charAt(0) + "");
        System.out.println(team.getName());
        return team;
    }

    public Player generatePlayer(int teamId) {
        Player player = new Player();
        player.setTeamId(teamId);
        player.setPower((float) Math.random() * 80 + 19);
        String name = LocalData.firstNames[(int) (Math.random() * LocalData.firstNames.length)] +
                " " + LocalData.lastNames[(int) (Math.random() * LocalData.lastNames.length)];
        player.setName(name);
        System.out.println(player.getName() + " , " + player.getPower());
        return player;
    }
}
