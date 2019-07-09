package ir.adicom.app.soccermanagerapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import ir.adicom.app.soccermanagerapp.App;
import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.model.Event;
import ir.adicom.app.soccermanagerapp.model.EventDao;
import ir.adicom.app.soccermanagerapp.model.Match;
import ir.adicom.app.soccermanagerapp.model.MatchDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MatchDao matchDao = ((App) getActivity().getApplication()).getDaoSession().getMatchDao();
        QueryBuilder<Match> qb = matchDao.queryBuilder();
        qb.where(MatchDao.Properties.WeekId.eq(App.weekIndex - 1));
        List<Match> matches = qb.list();
        Match match = null;
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i).getTeamAwayId() == App.teamId || matches.get(i).getTeamHomeId() == App.teamId) {
                match = matches.get(i);
                break;
            }
        }

        EventDao eventDao = ((App) getActivity().getApplication()).getDaoSession().getEventDao();
        QueryBuilder<Event> eventQueryBuilder = eventDao.queryBuilder();
        eventQueryBuilder.where(EventDao.Properties.MatchId.eq(match.getId()));
        List<Event> events = eventQueryBuilder.list();

        StringBuilder sb = new StringBuilder();
        sb.append(match.getTeamHome().getName());
        sb.append(" " + match.getGoalTeamHome());
        sb.append("-" + match.getGoalTeamAway() + " ");
        sb.append(match.getTeamAway().getName() + "\n");
        for (Event event : events) {
            String temp = event.getEventDetail().getTitle();
            temp = temp.replace("x", event.getPlayer().getName());
            if (event.getIsGoal()) {
                temp += " و گل";
            } else {
                temp += " و توپ به بیرون میره";
            }
            sb.append(temp + "\n");
        }

        TextView tv = (TextView) view.findViewById(R.id.tv_game);
        tv.setText(sb.toString());
    }
}
