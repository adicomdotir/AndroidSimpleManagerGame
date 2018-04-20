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
import ir.adicom.app.soccermanagerapp.model.Team;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeagueFragment extends Fragment {


    public LeagueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StringBuilder sb = new StringBuilder();
        for (Team team : LocalData.teams) {
            sb.append(team.getName() + ", " + team.getOverral() + "\n");
        }
        TextView tvTest = (TextView) view.findViewById(R.id.tv_test);
        tvTest.setText(sb.toString());
    }
}
