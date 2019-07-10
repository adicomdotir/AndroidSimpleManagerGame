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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ir.adicom.app.soccermanagerapp.App;
import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.data.LocalData;
import ir.adicom.app.soccermanagerapp.model.Match;
import ir.adicom.app.soccermanagerapp.model.MatchDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends Fragment {

    private LinearLayout mLinearLayout;
    private Typeface mTypeface;

    public MatchesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matchs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLinearLayout =  (LinearLayout) view.findViewById(R.id.ll_match);
        mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IRANSansMobile(FaNum).ttf");

        int len = 4;
        float week = len;
        MatchDao matchDao = ((App) getActivity().getApplication()).getDaoSession().getMatchDao();
        List<Match> matches = matchDao.loadAll();
        for (Match m : matches) {
            if (week % len == 0) {
                createTextView("", Color.GREEN);
                createTextView("هفته " + (int)(week / len), Color.BLACK);
                String temp = m.getTeamHome().getName();
                if (m.getGoalTeamHome() != -1 && m.getGoalTeamAway() != -1) {
                    temp += " " + m.getGoalTeamHome();
                    temp += "-";
                    temp += m.getGoalTeamAway() + " ";
                } else {
                    temp += " x-x ";
                }
                temp += m.getTeamAway().getName();
                createTextView(temp, Color.BLACK);
            } else {
                String temp = m.getTeamHome().getName();
                if (m.getGoalTeamHome() != -1 && m.getGoalTeamAway() != -1) {
                    temp += " " + m.getGoalTeamHome();
                    temp += "-";
                    temp += m.getGoalTeamAway() + " ";
                } else {
                    temp += " x-x ";
                }
                temp += m.getTeamAway().getName();
                createTextView(temp, Color.BLACK);
            }
            week++;
        }
    }

    private void createTextView(String message, int color) {
        TextView tv = new TextView(getContext());
        tv.setTypeface(mTypeface);
        tv.setGravity(Gravity.CENTER);
        tv.setText(message);
        tv.setTextColor(color);
        tv.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        mLinearLayout.addView(tv);
    }
}
