package ir.adicom.app.soccermanagerapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.Random;

import ir.adicom.app.soccermanagerapp.App;
import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.data.TempTable;
import ir.adicom.app.soccermanagerapp.model.Event;
import ir.adicom.app.soccermanagerapp.model.EventDao;
import ir.adicom.app.soccermanagerapp.model.EventDetail;
import ir.adicom.app.soccermanagerapp.model.EventDetailDao;
import ir.adicom.app.soccermanagerapp.model.Match;
import ir.adicom.app.soccermanagerapp.model.MatchDao;
import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.PlayerDao;
import ir.adicom.app.soccermanagerapp.model.Table;
import ir.adicom.app.soccermanagerapp.model.TableDao;
import ir.adicom.app.soccermanagerapp.tasks.PlayerAgeTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private Button btnGame;
    private Fragment fragment;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.home);

        TextView tvWeek = (TextView) view.findViewById(R.id.tv_week);
        tvWeek.setText("هفته " + (App.weekIndex));

        StringBuilder sb = new StringBuilder();
        sb.append(App.teamName + "\n");
        sb.append("رتبه تیم: " + TempTable.getTeamRank(App.teamId) + "\n");
        sb.append("تعداد بازیکنان: " + 11 + "\n");

        MatchDao matchDao = ((App) getActivity().getApplication()).getDaoSession().getMatchDao();
        if (App.weekIndex <= App.size * 2 - 2) {
            QueryBuilder<Match> qb = matchDao.queryBuilder();
            qb.where(MatchDao.Properties.WeekId.eq(App.weekIndex))
                    .or(MatchDao.Properties.TeamAwayId.eq(App.teamId), MatchDao.Properties.TeamHomeId.eq(App.teamId));
            List<Match> matches = qb.list();
            sb.append("حریف بعدی: ");
            if (matches.get(0).getTeamHomeId() == App.teamId) {

                sb.append(matches.get(0).getTeamAway().getName() + "(" + TempTable.getTeamRank(matches.get(0).getTeamAwayId()) + ")");
            } else {
                sb.append(matches.get(0).getTeamHome().getName() + "(" + TempTable.getTeamRank(matches.get(0).getTeamHomeId()) + ")");
            }

            if (App.weekIndex > 1) {
                qb = matchDao.queryBuilder();
                qb.where(MatchDao.Properties.WeekId.eq(App.weekIndex - 1))
                        .or(MatchDao.Properties.TeamAwayId.eq(App.teamId), MatchDao.Properties.TeamHomeId.eq(App.teamId));
                matches = qb.list();
                sb.append("\n");
                sb.append("بازی گذشته: ");
                sb.append(matches.get(0).getTeamHome().getName());
                sb.append(" " + matches.get(0).getGoalTeamHome() + "-" + matches.get(0).getGoalTeamAway() + " ");
                sb.append(matches.get(0).getTeamAway().getName());
            }
        }

        final TextView txtHome = (TextView) view.findViewById(R.id.txt_home);
        txtHome.setText(sb.toString());

        btnGame = (Button) view.findViewById(R.id.btn_game);
//        if (App.weekIndex > App.size * 2) {
//            btnGame.setEnabled(false);
//        }
        fragment = this;
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePlayerAge();
            }
        });

        updateCalendarColor();
    }

    public void onClickContinue() {
        if (App.day % 7 == 0) {
            if (App.weekIndex <= (App.size - 1) * 2) {
                gameProcess();
                App.weekIndex++;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(fragment).attach(fragment).commit();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, new GameFragment());
                ft.commit();
                createOrUpdateTempTableData();
            } else {
                if (App.weekIndex == App.size * 2 && App.day == 112) {
                    btnGame.setEnabled(false);
                }
                App.weekIndex++;
            }
        } else if (App.day % 7 == 6 && App.weekIndex <= (App.size - 1) * 2) {
            btnGame.setText("انجام بازی");
        } else if (App.day % 7 == 6 && App.day == 111) {
            btnGame.setText("فصل جدید");
        }
        App.day++;
        updateCalendarColor();
    }

    private void updatePlayerAge() {
        PlayerDao playerDao = ((App) getActivity().getApplication()).getDaoSession().getPlayerDao();
        List<Player> players = playerDao.loadAll();
        PlayerAgeTask playerAgeTask = new PlayerAgeTask(this, playerDao);
        playerAgeTask.execute(players);
    }

    private void createOrUpdateTempTableData() {
        TableDao tableDao = ((App) getActivity().getApplication()).getDaoSession().getTableDao();
        List<Table> tables = tableDao.queryBuilder().orderDesc(TableDao.Properties.Pts, TableDao.Properties.Gd).list();
        for (int i = 0; i < tables.size(); i++) {
            TempTable.myMap.put(tables.get(i).getTeamId(), i + 1);
        }
    }

    private void updateCalendarColor() {
        TextView tv;
        int x = App.day % 7;
        switch (x) {
            case 1:
                tv = (TextView) getActivity().findViewById(R.id.tv_day1);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tv = (TextView) getActivity().findViewById(R.id.tv_day7);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 2:
                tv = (TextView) getActivity().findViewById(R.id.tv_day2);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tv = (TextView) getActivity().findViewById(R.id.tv_day1);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 3:
                tv = (TextView) getActivity().findViewById(R.id.tv_day3);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tv = (TextView) getActivity().findViewById(R.id.tv_day2);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 4:
                tv = (TextView) getActivity().findViewById(R.id.tv_day4);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tv = (TextView) getActivity().findViewById(R.id.tv_day3);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 5:
                tv = (TextView) getActivity().findViewById(R.id.tv_day5);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tv = (TextView) getActivity().findViewById(R.id.tv_day4);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 6:
                tv = (TextView) getActivity().findViewById(R.id.tv_day6);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tv = (TextView) getActivity().findViewById(R.id.tv_day5);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 0:
                tv = (TextView) getActivity().findViewById(R.id.tv_day7);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tv = (TextView) getActivity().findViewById(R.id.tv_day6);
                tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    private void gameProcess() {
        Random gRandom = new Random(System.currentTimeMillis());
        MatchDao matchDao = ((App) getActivity().getApplication()).getDaoSession().getMatchDao();
        PlayerDao playerDao = ((App) getActivity().getApplication()).getDaoSession().getPlayerDao();
        QueryBuilder<Match> qb = matchDao.queryBuilder();
        qb.where(MatchDao.Properties.WeekId.eq(App.weekIndex));
        List<Match> matches = qb.list();

        EventDao eventDao = ((App) getActivity().getApplication()).getDaoSession().getEventDao();
        EventDetailDao eventDetailDao = ((App) getActivity().getApplication()).getDaoSession().getEventDetailDao();
        List<EventDetail> eventDetails = eventDetailDao.loadAll();

        for (Match match : matches) {
            long awayTeamId = match.getTeamAwayId();
            QueryBuilder<Player> playerQueryBuilder = playerDao.queryBuilder();
            playerQueryBuilder.where(PlayerDao.Properties.TeamId.eq(awayTeamId));
            List<Player> awayPlayers = playerQueryBuilder.list();

            long homeTeamId = match.getTeamHomeId();
            playerQueryBuilder = playerDao.queryBuilder();
            playerQueryBuilder.where(PlayerDao.Properties.TeamId.eq(homeTeamId));
            List<Player> homePlayers = playerQueryBuilder.list();

            int homeGoal = 0, awayGoal = 0;
            for (int i = 1; i <= 5; i++) {
                // Home Event
                Player homePlayer = homePlayers.get(gRandom.nextInt(10) + 1);
                int edHome = gRandom.nextInt(eventDetails.size());
                Event eventHome = new Event();
                int playerChance = gRandom.nextInt((int) homePlayer.getScoring());
                int gkChance = gRandom.nextInt((int) awayPlayers.get(0).getGoalkeeper());
                Log.e(App.TAG, homePlayer.getScoring() + "[" + playerChance + "-" + gkChance + "]" + awayPlayers.get(0).getGoalkeeper());
                if (playerChance > gkChance) {
                    homeGoal++;
                    eventHome.setIsGoal(true);
                }
                eventHome.setEventDetailId(eventDetails.get(edHome).getId());
                eventHome.setMatchId(match.getId());
                eventHome.setPlayerId(homePlayer.getId());
                eventHome.setTeamId(match.getTeamHomeId());
                eventDao.insert(eventHome);
                // Away Event
                Player awayPlayer = awayPlayers.get(gRandom.nextInt(10) + 1);
                int edAway = gRandom.nextInt(eventDetails.size());
                Event eventAway = new Event();
                playerChance = gRandom.nextInt((int) awayPlayer.getScoring());
                gkChance = gRandom.nextInt((int) homePlayers.get(0).getGoalkeeper());
                if (playerChance > gkChance) {
                    awayGoal++;
                    eventAway.setIsGoal(true);
                }
                eventAway.setEventDetailId(eventDetails.get(edAway).getId());
                eventAway.setMatchId(match.getId());
                eventAway.setPlayerId(awayPlayer.getId());
                eventAway.setTeamId(match.getTeamAwayId());
                eventDao.insert(eventAway);
            }

            match.setGoalTeamHome(homeGoal);
            match.setGoalTeamAway(awayGoal);
            matchDao.update(match);
            updateTeamInfo(match);
        }
    }

    private void updateTeamInfo(Match match) {
        TableDao tableDao = ((App) getActivity().getApplication()).getDaoSession().getTableDao();
        Table homeTable = tableDao.load(match.getTeamHomeId());
        Table awayTable = tableDao.load(match.getTeamAwayId());

        if (match.getGoalTeamHome() > match.getGoalTeamAway()) {
            // Home Team
            homeTable.setWin(homeTable.getWin() + 1);
            homeTable.setGa(homeTable.getGa() + match.getGoalTeamAway());
            homeTable.setGf(homeTable.getGf() + match.getGoalTeamHome());
            homeTable.setGd(homeTable.getGd() + match.getGoalTeamHome() - match.getGoalTeamAway());
            homeTable.setPts(homeTable.getPts() + 3);
            // Away Team
            awayTable.setLose(awayTable.getLose() + 1);
            awayTable.setGa(awayTable.getGa() + match.getGoalTeamHome());
            awayTable.setGf(awayTable.getGf() + match.getGoalTeamAway());
            awayTable.setGd(awayTable.getGd() - match.getGoalTeamHome() + match.getGoalTeamAway());
        } else if (match.getGoalTeamHome() < match.getGoalTeamAway()) {
            // Away Team
            awayTable.setWin(awayTable.getWin() + 1);
            awayTable.setGa(awayTable.getGa() + match.getGoalTeamHome());
            awayTable.setGf(awayTable.getGf() + match.getGoalTeamAway());
            awayTable.setGd(awayTable.getGd() - match.getGoalTeamHome() + match.getGoalTeamAway());
            awayTable.setPts(awayTable.getPts() + 3);
            // Home Team
            homeTable.setLose(homeTable.getLose() + 1);
            homeTable.setGa(homeTable.getGa() + match.getGoalTeamAway());
            homeTable.setGf(homeTable.getGf() + match.getGoalTeamHome());
            homeTable.setGd(homeTable.getGd() + match.getGoalTeamHome() - match.getGoalTeamAway());
        } else {
            // Home Team
            homeTable.setDraw(homeTable.getDraw() + 1);
            homeTable.setGa(homeTable.getGa() + match.getGoalTeamAway());
            homeTable.setGf(homeTable.getGf() + match.getGoalTeamHome());
            homeTable.setPts(homeTable.getPts() + 1);
            // Away Team
            awayTable.setDraw(awayTable.getDraw() + 1);
            awayTable.setGa(awayTable.getGa() + match.getGoalTeamHome());
            awayTable.setGf(awayTable.getGf() + match.getGoalTeamAway());
            awayTable.setPts(awayTable.getPts() + 1);
        }

        tableDao.update(homeTable);
        tableDao.update(awayTable);
    }
}
