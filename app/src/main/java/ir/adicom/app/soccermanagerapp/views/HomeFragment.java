package ir.adicom.app.soccermanagerapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.data.LocalData;
import ir.adicom.app.soccermanagerapp.model.Match;
import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.Team;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


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

//        int playerCount = 0;
//
//        for (int i = 0; i < LocalData.players.length; i++) {
//            if (LocalData.players[i].getTeamId() == 1) {
//                playerCount++;
//            }
//        }
//        int previousWeek = LocalData.weekIndex - 1;
//
//        TextView tvWeek = (TextView) view.findViewById(R.id.tv_week);
//        tvWeek.setText("هفته " + (LocalData.weekIndex));
//
//        StringBuilder sb = new StringBuilder();
////        sb.append(team.getName() + "\n");
//        sb.append("قدرت: " + LocalData.teams[0].getOverral() + "\n");
//        sb.append("رتبه تیم: " + 1 + "\n");
//        sb.append("تعداد بازیکنان: " + playerCount + "\n");
//        for (int i = 0; i < LocalData.matches.length; i++) {
//            if (LocalData.matches[i].getWeekId() == LocalData.weekIndex) {
//                if (LocalData.matches[i].getTeamHome() == 0) {
//                    sb.append("حریف بعدی: " + LocalData.teams[LocalData.matches[i].getTeamAway()].getName());
//                } else {
//                    if (LocalData.matches[i].getTeamAway() == 0) {
//                        sb.append("حریف بعدی: " + LocalData.teams[LocalData.matches[i].getTeamHome()].getName());
//                    }
//                }
//                if (previousWeek >= 1 && LocalData.matches[i].getWeekId() == previousWeek) {
//                    sb.append(LocalData.teams[LocalData.matches[i].getTeamHome()].getName() + " " +
//                            LocalData.matches[i].getGoalTeamHome() + "-" + LocalData.matches[i].getGoalTeamAway() + " " +
//                            LocalData.teams[LocalData.matches[i].getTeamAway()].getName());
//                }
//            }
//        }
//        for (int i = 0; i < LocalData.matches.length; i++) {
//            if (LocalData.matches[i].getWeekId() == previousWeek) {
//                if (LocalData.matches[i].getTeamHome() == 0 || LocalData.matches[i].getTeamAway() == 0) {
//                    sb.append("\nبازی گذشته : " + LocalData.teams[LocalData.matches[i].getTeamHome()].getName() + " " +
//                            LocalData.matches[i].getGoalTeamHome() + "-" + LocalData.matches[i].getGoalTeamAway() + " " +
//                            LocalData.teams[LocalData.matches[i].getTeamAway()].getName());
//                }
//            }
//        }
//
//        final TextView txtHome = (TextView) view.findViewById(R.id.txt_home);
//        txtHome.setText(sb.toString());
//
//        final Button btnGame = (Button) view.findViewById(R.id.btn_game);
//        if (LocalData.weekIndex > LocalData.size * 2 - 2) {
//            btnGame.setEnabled(false);
//        }
//        final Fragment fragment = this;
//        btnGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (LocalData.day % 7 == 0) {
//                    if (LocalData.weekIndex <= LocalData.size * 2 - 2) {
//                        gameProcess();
//                        LocalData.weekIndex++;
//                        FragmentTransaction ft = getFragmentManager().beginTransaction();
//                        ft.detach(fragment).attach(fragment).commit();
//                    }
//                } else if (LocalData.day % 7 == 6) {
//                    btnGame.setText("انجام بازی");
//                }
//                LocalData.day++;
//                updateCalendarColor();
//            }
//        });
//
//        updateCalendarColor();
    }

    private void updateCalendarColor() {
        TextView tv;
        int x = LocalData.day % 7;
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
//        Random gRandom = new Random(System.currentTimeMillis());
//
//        for (Match match : LocalData.matches) {
//            if (match.getWeekId() == LocalData.weekIndex) {
//                int awayTeamId = match.getTeamAway() + 1;
//                List<Player> awayPlayers = new ArrayList<>();
//                int homeTeamId = match.getTeamHome() + 1;
//                List<Player> homePlayers = new ArrayList<>();
//                for (int i = 0; i < LocalData.players.length; i++) {
//                    if (LocalData.players[i].getTeamId() == awayTeamId) {
//                        awayPlayers.add(LocalData.players[i]);
//                    } else if (LocalData.players[i].getTeamId() == homeTeamId) {
//                        homePlayers.add(LocalData.players[i]);
//                    }
//                }
//
//                int homeGoal = 0, awayGoal = 0;
//                for (int i = 1; i <= 5; i++) {
//                    // Home Event
//                    if (homePlayers.get(i).getScoring() > awayPlayers.get(0).getGoalkeeper()) {
//                        homeGoal++;
//                    }
//                    // Away Event
//                    if (awayPlayers.get(i).getScoring() > homePlayers.get(0).getGoalkeeper()) {
//                        awayGoal++;
//                    }
//                }
//
//                match.setGoalTeamHome(homeGoal);
//                match.setGoalTeamAway(awayGoal);
//
//                updateTeamInfo(match);
//            }
//        }
    }

    private void updateTeamInfo(Match match) {
//        Team teamHome = LocalData.teams[match.getTeamHome()];
//        Team teamAway = LocalData.teams[match.getTeamAway()];
//        if (match.getGoalTeamHome() > match.getGoalTeamAway()) {
//            // Home Team
//            teamHome.setGame(teamHome.getGame() + 1);
//            teamHome.setWin(teamHome.getWin() + 1);
//            teamHome.setGa(teamHome.getGa() + match.getGoalTeamAway());
//            teamHome.setGf(teamHome.getGf() + match.getGoalTeamHome());
//            teamHome.setPts(teamHome.getPts() + 3);
//            // Away Team
//            teamAway.setGame(teamAway.getGame() + 1);
//            teamAway.setLose(teamAway.getLose() + 1);
//            teamAway.setGa(teamAway.getGa() + match.getGoalTeamHome());
//            teamAway.setGf(teamAway.getGf() + match.getGoalTeamAway());
//        } else if (match.getGoalTeamHome() < match.getGoalTeamAway()) {
//            // Away Team
//            teamAway.setGame(teamAway.getGame() + 1);
//            teamAway.setWin(teamAway.getWin() + 1);
//            teamAway.setGa(teamAway.getGa() + match.getGoalTeamHome());
//            teamAway.setGf(teamAway.getGf() + match.getGoalTeamAway());
//            teamAway.setPts(teamAway.getPts() + 3);
//            // Home Team
//            teamHome.setGame(teamHome.getGame() + 1);
//            teamHome.setLose(teamHome.getLose() + 1);
//            teamHome.setGa(teamHome.getGa() + match.getGoalTeamAway());
//            teamHome.setGf(teamHome.getGf() + match.getGoalTeamHome());
//        } else {
//            // Home Team
//            teamHome.setGame(teamHome.getGame() + 1);
//            teamHome.setDraw(teamHome.getDraw() + 1);
//            teamHome.setGa(teamHome.getGa() + match.getGoalTeamAway());
//            teamHome.setGf(teamHome.getGf() + match.getGoalTeamHome());
//            teamHome.setPts(teamHome.getPts() + 1);
//            // Away Team
//            teamAway.setGame(teamAway.getGame() + 1);
//            teamAway.setDraw(teamAway.getDraw() + 1);
//            teamAway.setGa(teamAway.getGa() + match.getGoalTeamHome());
//            teamAway.setGf(teamAway.getGf() + match.getGoalTeamAway());
//            teamAway.setPts(teamAway.getPts() + 1);
//        }
    }
}
