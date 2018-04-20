package ir.adicom.app.soccermanagerapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.data.LocalData;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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
        sb.append("Week: " + LocalData.weekIndex + "\n");
        for (int i = 0; i < LocalData.MATCHES.length; i++) {
            if (LocalData.MATCHES[i].getWeekId() == LocalData.weekIndex) {
                if (LocalData.MATCHES[i].getTeamHome() == 0) {
                    sb.append("Next Opponent: " + LocalData.teams[LocalData.MATCHES[i].getTeamAway()].getName());
                } else {
                    if (LocalData.MATCHES[i].getTeamAway() == 0) {
                        sb.append("Next Opponent: " + LocalData.teams[LocalData.MATCHES[i].getTeamHome()].getName());
                    }
                }
            }
        }

        TextView txtHome = (TextView) view.findViewById(R.id.txt_home);
        txtHome.setText(sb.toString());

        Button btnGame = (Button) view.findViewById(R.id.btn_game);
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
