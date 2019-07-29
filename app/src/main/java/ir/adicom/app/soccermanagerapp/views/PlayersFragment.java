package ir.adicom.app.soccermanagerapp.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import ir.adicom.app.soccermanagerapp.App;
import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.adapters.PlayersAdapter;
import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.PlayerDao;
import ir.adicom.app.soccermanagerapp.model.TeamDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment {
    private Long teamId;

    public PlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        teamId = this.getArguments().getLong("team_id", 0);
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.players);

        TeamDao teamDao = ((App) getActivity().getApplication()).getDaoSession().getTeamDao();
        PlayerDao playerDao = ((App) getActivity().getApplication()).getDaoSession().getPlayerDao();

        Bundle bundle = getArguments();
        if (bundle != null) teamId = bundle.getLong("team_id");

        if (teamId == null) {
            teamId = teamDao.loadAll().get(0).getId();
        }
        QueryBuilder<Player> qb = playerDao.queryBuilder();
        qb.where(PlayerDao.Properties.TeamId.eq(teamId));
        List<Player> playerList = qb.list();

        Player[] players = new Player[playerList.size()];
        players = playerList.toArray(players);
        PlayersAdapter adapter = new PlayersAdapter(getContext(), players);

        ListView lvPlayers = (ListView) view.findViewById(R.id.lv_players);
        lvPlayers.setAdapter(adapter);

        TextView tvTeam = (TextView) view.findViewById(R.id.tv_team);
        tvTeam.setText(playerList.get(0).getTeam().getName());
    }
}
