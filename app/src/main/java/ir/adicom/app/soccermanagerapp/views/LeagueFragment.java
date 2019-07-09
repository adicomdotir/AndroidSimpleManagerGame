package ir.adicom.app.soccermanagerapp.views;

import android.graphics.Color;
import android.graphics.Typeface;
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

import java.util.List;

import ir.adicom.app.soccermanagerapp.App;
import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.data.LocalData;
import ir.adicom.app.soccermanagerapp.model.Table;
import ir.adicom.app.soccermanagerapp.model.TableDao;
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

        TableDao tableDao = ((App) getActivity().getApplication()).getDaoSession().getTableDao();
        List<Table> tables = tableDao.queryBuilder().orderDesc(TableDao.Properties.Pts, TableDao.Properties.Gd).list();
//        sort(teams);

        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table);
        for (int i = 0; i < tables.size(); i++) {
            TableRow row = new TableRow(view.getContext());
            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IRANSansMobile(FaNum).ttf");

            TextView tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + tables.get(i).getPts());
            tv.setTypeface(tf);
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + tables.get(i).getGd());
            tv.setTypeface(tf);
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + tables.get(i).getGa());
            tv.setTypeface(tf);
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + tables.get(i).getGf());
            tv.setTypeface(tf);
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + tables.get(i).getLose());
            tv.setTypeface(tf);
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + tables.get(i).getDraw());
            tv.setTypeface(tf);
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + tables.get(i).getWin());
            tv.setTypeface(tf);
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText("" + (tables.get(i).getWin() + tables.get(i).getDraw() + tables.get(i).getLose()));
            tv.setTypeface(tf);
            row.addView(tv);

            tv = new TextView(view.getContext());
            textViewSetColor(tv, i);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            tv.setText(tables.get(i).getTeam().getName());
            tv.setTypeface(tf);
            row.addView(tv);

            tableLayout.addView(row);
        }

    }

    private void textViewSetColor(TextView tv, int index) {
        if (index == 0) {
            tv.setTextColor(Color.parseColor("#22AA22"));
        } else if (index == App.size - 1 || index == App.size - 2) {
            tv.setTextColor(Color.RED);
        } else if (index == App.size - 3 || index == App.size - 4) {
            tv.setTextColor(Color.parseColor("#FF9900"));
        } else {
            tv.setTextColor(Color.BLACK);
        }
    }
}
