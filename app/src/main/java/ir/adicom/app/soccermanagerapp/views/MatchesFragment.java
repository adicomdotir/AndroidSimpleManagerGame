package ir.adicom.app.soccermanagerapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        TextView tvMatches = (TextView) view.findViewById(R.id.tv_matches);
        StringBuilder sb = new StringBuilder();
        int len = 4;
        float week = len;
        MatchDao matchDao = ((App) getActivity().getApplication()).getDaoSession().getMatchDao();
        List<Match> matches = matchDao.loadAll();
        for (Match m : matches) {
            if (week % len == 0) {
                sb.append("\n");
                sb.append("هفته " + (int)(week / len));
                sb.append("\n");
                sb.append(m.getTeamHome().getName());
                if (m.getGoalTeamHome() != -1 && m.getGoalTeamAway() != -1) {
                    sb.append(" " + m.getGoalTeamHome());
                    sb.append("-");
                    sb.append(m.getGoalTeamAway() + " ");
                } else {
                    sb.append(" x-x ");
                }
                sb.append(m.getTeamAway().getName());
            } else {
                sb.append(m.getTeamHome().getName());
                if (m.getGoalTeamHome() != -1 && m.getGoalTeamAway() != -1) {
                    sb.append(" " + m.getGoalTeamHome());
                    sb.append("-");
                    sb.append(m.getGoalTeamAway() + " ");
                } else {
                    sb.append(" x-x ");
                }
                sb.append(m.getTeamAway().getName());
            }
            sb.append("\n");
            week++;
        }
        tvMatches.setText(sb.toString());
    }
}
