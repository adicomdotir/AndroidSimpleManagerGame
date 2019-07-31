package ir.adicom.app.soccermanagerapp.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ir.adicom.app.soccermanagerapp.App;
import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.model.Event;
import ir.adicom.app.soccermanagerapp.model.EventDao;
import ir.adicom.app.soccermanagerapp.model.MatchDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends Fragment {


    public StatisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EventDao eventDao = ((App) getActivity().getApplication()).getDaoSession().getEventDao();
        QueryBuilder<Event> eventQueryBuilder = eventDao.queryBuilder();
        List<Event> eventList = eventQueryBuilder.where(EventDao.Properties.IsGoal.eq(true)).list();
        Map<Long, Integer> topScorer = new HashMap<>();
        for (int i = 0; i < eventList.size(); i++) {
            Event tempEvent = eventList.get(i);
            if (topScorer.containsKey(tempEvent.getPlayerId())) {
                Integer goal = topScorer.get(tempEvent.getPlayerId());
                topScorer.put(tempEvent.getPlayerId(), goal + 1);
            } else {
                topScorer.put(tempEvent.getPlayerId(), 1);
            }
        }
        topScorer = sortMapByValue(topScorer);

        TextView tv = (TextView) view.findViewById(R.id.tv_statistic);
        StringBuilder sb = new StringBuilder();
        for (Long id : topScorer.keySet()) {
            sb.append(id + ": " + topScorer.get(id) + "\n");
        }
        tv.setText(sb.toString());
    }

    private Map<Long, Integer> sortMapByValue(Map<Long, Integer> inputMap) {

        Set<Map.Entry<Long, Integer>> set = inputMap.entrySet();
        List<Map.Entry<Long, Integer>> list = new ArrayList<>(set);

        Collections.sort(list, new Comparator<Map.Entry<Long, Integer>>() {
            @Override
            public int compare(Map.Entry<Long, Integer> o1, Map.Entry<Long, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());  //Ascending order
            }
        });

        Map<Long, Integer> sortedMap = new LinkedHashMap<>();

        for (Map.Entry<Long, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
