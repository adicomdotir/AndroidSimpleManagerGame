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
        sb.append("Players Count: " + playerCount);

        TextView txtHome = (TextView) view.findViewById(R.id.txt_home);
        txtHome.setText(sb.toString());
    }
}
