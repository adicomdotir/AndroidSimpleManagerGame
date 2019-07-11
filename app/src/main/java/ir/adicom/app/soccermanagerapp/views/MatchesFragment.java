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
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import ir.adicom.app.soccermanagerapp.App;
import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.adapters.MatchAdapter;
import ir.adicom.app.soccermanagerapp.adapters.PlayersAdapter;
import ir.adicom.app.soccermanagerapp.data.LocalData;
import ir.adicom.app.soccermanagerapp.model.Match;
import ir.adicom.app.soccermanagerapp.model.MatchDao;
import ir.adicom.app.soccermanagerapp.model.MatchViewModel;
import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.PlayerDao;

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

        mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IRANSansMobile(FaNum).ttf");

        int len = 4;
        float week = len;
        MatchDao matchDao = ((App) getActivity().getApplication()).getDaoSession().getMatchDao();
//        List<Match> matches = matchDao.loadAll();
        MatchViewModel[] matchViewModels = new MatchViewModel[(App.size - 1) * 2];

        for (int i = 0; i < (App.size - 1) * 2; i++) {
            QueryBuilder<Match> qb = matchDao.queryBuilder();
            qb.where(MatchDao.Properties.WeekId.eq(i + 1));
            matchViewModels[i] = new MatchViewModel();
            matchViewModels[i].setMatches(qb.list());
            matchViewModels[i].setWeekId(i + 1);
        }

        MatchAdapter adapter = new MatchAdapter(getContext(), getFragmentManager(), matchViewModels);
        ListView lvMatches = (ListView) view.findViewById(R.id.lv_matches);
        lvMatches.setAdapter(adapter);


//        for (Match m : matches) {
//            if (week % len == 0) {
////                createTextView("", Color.GREEN);
////                createTextView("هفته " + (int)(week / len), Color.BLACK);
//                String temp = m.getTeamHome().getName();
//                if (m.getGoalTeamHome() != -1 && m.getGoalTeamAway() != -1) {
//                    temp += " " + m.getGoalTeamHome();
//                    temp += "-";
//                    temp += m.getGoalTeamAway() + " ";
//                } else {
//                    temp += " x-x ";
//                }
//                temp += m.getTeamAway().getName();
////                createTextView(temp, Color.BLACK);
//            } else {
//                String temp = m.getTeamHome().getName();
//                if (m.getGoalTeamHome() != -1 && m.getGoalTeamAway() != -1) {
//                    temp += " " + m.getGoalTeamHome();
//                    temp += "-";
//                    temp += m.getGoalTeamAway() + " ";
//                } else {
//                    temp += " x-x ";
//                }
//                temp += m.getTeamAway().getName();
////                createTextView(temp, Color.BLACK);
//            }
//            week++;
//        }
    }
}
