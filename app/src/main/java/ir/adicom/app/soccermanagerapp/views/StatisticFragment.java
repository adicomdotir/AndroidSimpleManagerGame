package ir.adicom.app.soccermanagerapp.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import ir.adicom.app.soccermanagerapp.adapters.StatisticAdapter;
import ir.adicom.app.soccermanagerapp.model.Event;
import ir.adicom.app.soccermanagerapp.model.EventDao;
import ir.adicom.app.soccermanagerapp.model.MatchDao;
import ir.adicom.app.soccermanagerapp.model.StatisticViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends Fragment {
    private List<Event> eventList;

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
        eventList = eventQueryBuilder.where(EventDao.Properties.IsGoal.eq(true)).list();
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

        StatisticViewModel[] models = new StatisticViewModel[topScorer.size()];
        int count = 0;
        for (Long id : topScorer.keySet()) {
            StatisticViewModel model = getStatisticFromId(id, topScorer.get(id));
            models[count] = model;
            count++;
        }

        StatisticAdapter adapter = new StatisticAdapter(getContext(), models);
        ListView lvMatches = (ListView) view.findViewById(R.id.lv_matches);
        lvMatches.setAdapter(adapter);
    }

    private StatisticViewModel getStatisticFromId(Long id, int goalNumber) {
        StatisticViewModel model = new StatisticViewModel();
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getPlayerId().equals(id)) {
                model.setGoalNumber(goalNumber);
                model.setPlayerName(eventList.get(i).getPlayer().getName());
                model.setTeamName(eventList.get(i).getTeam().getName());
                break;
            }
        }
        return model;
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
