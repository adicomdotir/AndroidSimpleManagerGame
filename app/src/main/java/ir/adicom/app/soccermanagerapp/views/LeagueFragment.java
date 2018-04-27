package ir.adicom.app.soccermanagerapp.views;

import android.graphics.Color;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Team[] teams = LocalData.teams.clone();
        sort(teams);

        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table);
        for (int i = 0; i < LocalData.size; i++) {
            TableRow row = new TableRow(view.getContext());

            TextView tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText(teams[i].getName());
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + teams[i].getGame());
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + teams[i].getWin());
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + teams[i].getDraw());
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + teams[i].getLose());
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + teams[i].getGf());
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + teams[i].getGa());
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + (teams[i].getGf() - teams[i].getGa()));
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + teams[i].getPts());
            row.addView(tv);

            tableLayout.addView(row);
        }

    }

    private void textViewSetColor(TextView tv, int index) {
        if (index == 0) {
            tv.setTextColor(Color.parseColor("#22AA22"));
        } else if (index == LocalData.size - 1 || index == LocalData.size - 2) {
            tv.setTextColor(Color.RED);
        } else if (index == LocalData.size - 3 || index == LocalData.size - 4) {
            tv.setTextColor(Color.parseColor("#FF9900"));
        } else {
            tv.setTextColor(Color.BLACK);
        }
    }

    private void sort(Team[] teams) {
        // For points
        for (int i = 0; i < teams.length; i++) {
            for (int j = i + 1; j < teams.length; j++) {
                if (teams[i].getPts() < teams[j].getPts()) {
                    Team temp = teams[i];
                    teams[i] = teams[j];
                    teams[j] = temp;
                }
            }
        }
        // For goal differance
        for (int i = 0; i < teams.length; i++) {
            for (int j = i + 1; j < teams.length; j++) {
                if (teams[i].getPts() == teams[j].getPts()) {
                    if (teams[i].getGf() - teams[i].getGa() < teams[j].getGf() - teams[j].getGa()) {
                        Team temp = teams[i];
                        teams[i] = teams[j];
                        teams[j] = temp;
                    }
                }
            }
        }
        // For goal forward
        for (int i = 0; i < teams.length; i++) {
            for (int j = i + 1; j < teams.length; j++) {
                if (teams[i].getPts() == teams[j].getPts()) {
                    if (teams[i].getGf() - teams[i].getGa() == teams[j].getGf() - teams[j].getGa()) {
                        if (teams[i].getGf() < teams[j].getGf()) {
                            Team temp = teams[i];
                            teams[i] = teams[j];
                            teams[j] = temp;
                        }
                    }
                }
            }
        }
    }
}
