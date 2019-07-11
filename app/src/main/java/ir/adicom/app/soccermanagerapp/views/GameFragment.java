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

    private LinearLayout mLinearLayout;
    private Typeface mTypeface;

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
        Match match = null;
        long matchId = 0;

        Bundle bundle = getArguments();
        if (bundle != null) matchId = bundle.getLong("match_id");
        if (matchId != 0) {
            match = matchDao.load(matchId);
        } else {
            QueryBuilder<Match> qb = matchDao.queryBuilder();
            qb.where(MatchDao.Properties.WeekId.eq(App.weekIndex - 1));
            List<Match> matches = qb.list();
            for (int i = 0; i < matches.size(); i++) {
                if (matches.get(i).getTeamAwayId() == App.teamId || matches.get(i).getTeamHomeId() == App.teamId) {
                    match = matches.get(i);
                    break;
                }
            }
        }

        EventDao eventDao = ((App) getActivity().getApplication()).getDaoSession().getEventDao();
        QueryBuilder<Event> eventQueryBuilder = eventDao.queryBuilder();
        eventQueryBuilder.where(EventDao.Properties.MatchId.eq(match.getId()));
        List<Event> events = eventQueryBuilder.list();

        mLinearLayout =  (LinearLayout) view.findViewById(R.id.ll_game);
        mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IRANSansMobile(FaNum).ttf");

        StringBuilder sb = new StringBuilder();
        if (match.getGoalTeamHome() != -1 && match.getGoalTeamAway() != -1) {
            sb.append(match.getTeamHome().getName());
            sb.append(" " + match.getGoalTeamHome());
            sb.append("-" + match.getGoalTeamAway() + " ");
            sb.append(match.getTeamAway().getName() + "\n");
        } else {
            sb.append(match.getTeamHome().getName());
            sb.append(" x-x ");
            sb.append(match.getTeamAway().getName() + "\n");
            sb.append("این بازی برگزار نشده است");
        }
        createTextView(sb.toString(), Color.BLACK);

        for (Event event : events) {
            String temp = event.getEventDetail().getTitle();
            temp = temp.replace("x", event.getPlayer().getName());
            if (event.getIsGoal()) {
                temp += " و گل";
                createTextView(temp, Color.GREEN);
            } else {
                temp += " و توپ به بیرون میره";
                createTextView(temp, Color.RED);
            }
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
