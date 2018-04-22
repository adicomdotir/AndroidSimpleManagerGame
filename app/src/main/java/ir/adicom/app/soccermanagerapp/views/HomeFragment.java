package ir.adicom.app.soccermanagerapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.data.LocalData;
import ir.adicom.app.soccermanagerapp.model.Match;
import ir.adicom.app.soccermanagerapp.model.Team;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        float overall = 0;
        int playerCount = 0;

        for (int i = 0; i < LocalData.players.length; i++) {
            if (LocalData.players[i].getTeamId() == 1) {
                playerCount++;
                overall += LocalData.players[i].getScoring() + LocalData.players[i].getGoalkeeper();
            }
        }
        overall /= 4;
        StringBuilder sb = new StringBuilder();
//        sb.append(team.getName() + "\n");
        sb.append("Overall: " + (int) overall + "\n");
        sb.append("Position: " + 1 + "\n");
        sb.append("Players Count: " + playerCount + "\n");
        sb.append("Week: " + (LocalData.weekIndex > LocalData.size * 2 - 2 ? "End" : LocalData.weekIndex) + "\n");
        for (int i = 0; i < LocalData.matches.length; i++) {
            if (LocalData.matches[i].getWeekId() == LocalData.weekIndex) {
                if (LocalData.matches[i].getTeamHome() == 0) {
                    sb.append("Next Opponent: " + LocalData.teams[LocalData.matches[i].getTeamAway()].getName());
                } else {
                    if (LocalData.matches[i].getTeamAway() == 0) {
                        sb.append("Next Opponent: " + LocalData.teams[LocalData.matches[i].getTeamHome()].getName());
                    }
                }
            }
        }

        final TextView txtHome = (TextView) view.findViewById(R.id.txt_home);
        txtHome.setText(sb.toString());

        final Button btnGame = (Button) view.findViewById(R.id.btn_game);
        if (LocalData.weekIndex > LocalData.size * 2 - 2) {
            btnGame.setEnabled(false);
        }
        final Fragment fragment = this;
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LocalData.weekIndex <= LocalData.size * 2 - 2) {
                    gameProcess();
                    LocalData.weekIndex++;
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(fragment).attach(fragment).commit();
                }
            }
        });
    }

    private void gameProcess() {
        for (Match m : LocalData.matches) {
            if (m.getWeekId() == LocalData.weekIndex) {
                int goalHome = (int) Math.floor(Math.random() * 5);
                int goalAway = (int) Math.floor(Math.random() * 5);
                m.setGoalTeamHome(goalHome);
                m.setGoalTeamAway(goalAway);
                updateTeamInfo(m);
            }
        }
    }

    private void updateTeamInfo(Match match) {
        Team teamHome = LocalData.teams[match.getTeamHome()];
        Team teamAway = LocalData.teams[match.getTeamAway()];
        if (match.getGoalTeamHome() > match.getGoalTeamAway()) {
            // Home Team
            teamHome.setGame(teamHome.getGame() + 1);
            teamHome.setWin(teamHome.getWin() + 1);
            teamHome.setGa(teamHome.getGa() + match.getGoalTeamAway());
            teamHome.setGf(teamHome.getGf() + match.getGoalTeamHome());
            teamHome.setPts(teamHome.getPts() + 3);

            // Away Team
            teamAway.setGame(teamAway.getGame() + 1);
            teamAway.setLose(teamAway.getLose() + 1);
            teamAway.setGa(teamAway.getGa() + match.getGoalTeamHome());
            teamAway.setGf(teamAway.getGf() + match.getGoalTeamAway());
        } else if (match.getGoalTeamHome() < match.getGoalTeamAway()) {
            // Away Team
            teamAway.setGame(teamAway.getGame() + 1);
            teamAway.setWin(teamAway.getWin() + 1);
            teamAway.setGa(teamAway.getGa() + match.getGoalTeamHome());
            teamAway.setGf(teamAway.getGf() + match.getGoalTeamAway());
            teamAway.setPts(teamAway.getPts() + 3);

            // Home Team
            teamHome.setGame(teamHome.getGame() + 1);
            teamHome.setLose(teamHome.getLose() + 1);
            teamHome.setGa(teamHome.getGa() + match.getGoalTeamAway());
            teamHome.setGf(teamHome.getGf() + match.getGoalTeamHome());
        } else {
            // Home Team
            teamHome.setGame(teamHome.getGame() + 1);
            teamHome.setDraw(teamHome.getDraw() + 1);
            teamHome.setGa(teamHome.getGa() + match.getGoalTeamAway());
            teamHome.setGf(teamHome.getGf() + match.getGoalTeamHome());
            teamHome.setPts(teamHome.getPts() + 1);
            // Away Team
            teamAway.setGame(teamAway.getGame() + 1);
            teamAway.setDraw(teamAway.getDraw() + 1);
            teamAway.setGa(teamAway.getGa() + match.getGoalTeamHome());
            teamAway.setGf(teamAway.getGf() + match.getGoalTeamAway());
            teamAway.setPts(teamAway.getPts() + 1);
        }
    }
}
