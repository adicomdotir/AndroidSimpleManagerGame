package ir.adicom.app.soccermanagerapp.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.data.LocalData;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment {


    public PlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LocalData.players.length; i++) {
            if (LocalData.players[i].getTeamId() == 1) {
                sb.append(LocalData.players[i].getName());
                sb.append(" " + LocalData.players[i].getAge());
                sb.append(" " + (int) LocalData.players[i].getScoring());
                sb.append(" " + (int) LocalData.players[i].getGoalkeeper());
                sb.append("\n");
            }
        }

        TextView txtPlayers = (TextView) view.findViewById(R.id.txt_players);
        txtPlayers.setText(sb);
    }
}
