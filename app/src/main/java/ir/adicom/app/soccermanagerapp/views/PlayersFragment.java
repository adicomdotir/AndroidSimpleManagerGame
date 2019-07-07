package ir.adicom.app.soccermanagerapp.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import ir.adicom.app.soccermanagerapp.App;
import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.adapters.PlayersAdapter;
import ir.adicom.app.soccermanagerapp.data.LocalData;
import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.PlayerDao;
import ir.adicom.app.soccermanagerapp.model.TeamDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment {


    public PlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TeamDao teamDao = ((App) getActivity().getApplication()).getDaoSession().getTeamDao();
        PlayerDao playerDao = ((App) getActivity().getApplication()).getDaoSession().getPlayerDao();
        Long teamId = teamDao.loadAll().get(0).getId();

        QueryBuilder<Player> qb = playerDao.queryBuilder();
        qb.where(PlayerDao.Properties.TeamId.eq(teamId));
        List<Player> playerList = qb.list();

        StringBuilder sb = new StringBuilder();

        Player[] players = new Player[playerList.size()];
        players = playerList.toArray(players);
        PlayersAdapter adapter = new PlayersAdapter(getContext(), players);

        ListView lvPlayers = (ListView) view.findViewById(R.id.lv_players);
        lvPlayers.setAdapter(adapter);
    }
}
