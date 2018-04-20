package ir.adicom.app.soccermanagerapp.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
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

        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table);
        for (int i = 0; i < LocalData.SIZE; i++) {
            TableRow row = new TableRow(view.getContext());

            TextView tv = new TextView(view.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText(LocalData.teams[i].getName());
            row.addView(tv);

            tv = new TextView(view.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + LocalData.teams[i].getGame());
            row.addView(tv);

            tv = new TextView(view.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + LocalData.teams[i].getWin());
            row.addView(tv);

            tv = new TextView(view.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + LocalData.teams[i].getDraw());
            row.addView(tv);

            tv = new TextView(view.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + LocalData.teams[i].getLose());
            row.addView(tv);

            tv = new TextView(view.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + LocalData.teams[i].getGf());
            row.addView(tv);

            tv = new TextView(view.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + LocalData.teams[i].getGa());
            row.addView(tv);

            tv = new TextView(view.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + (LocalData.teams[i].getGf() - LocalData.teams[i].getGa()));
            row.addView(tv);

            tv = new TextView(view.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + LocalData.teams[i].getPts());
            row.addView(tv);

            tableLayout.addView(row);
        }

    }
}
