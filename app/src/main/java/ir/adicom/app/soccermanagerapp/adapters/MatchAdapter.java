package ir.adicom.app.soccermanagerapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.model.Match;
import ir.adicom.app.soccermanagerapp.model.MatchViewModel;
import ir.adicom.app.soccermanagerapp.views.GameFragment;

/**
 * Created by adicom on 4/20/18.
 */

public class MatchAdapter extends ArrayAdapter<MatchViewModel> implements View.OnClickListener {
    private FragmentManager mFragmentManager;

    public MatchAdapter(Context context, FragmentManager fragmentManager, MatchViewModel[] matchViewModels) {
        super(context, 0, matchViewModels);
        mFragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MatchViewModel matchViewModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_match, parent, false);
        }

        TextView tvWeek = (TextView) convertView.findViewById(R.id.tv_week);
        tvWeek.setText("هفته " + matchViewModel.getWeekId());

        List<Match> matches = matchViewModel.getMatches();
        TextView tvLeft = (TextView) convertView.findViewById(R.id.tv_g1_left);
        TextView tvCenter = (TextView) convertView.findViewById(R.id.tv_g1_center);
        TextView tvRight = (TextView) convertView.findViewById(R.id.tv_g1_right);
        tvLeft.setText(matches.get(0).getTeamAway().getName());
        String res = "x-x";
        if (matches.get(0).getGoalTeamAway() >= 0 || matches.get(0).getGoalTeamHome() >= 0) {
            res = matches.get(0).getGoalTeamAway() + "-" + matches.get(0).getGoalTeamHome();
        }
        tvCenter.setText(res);
        tvCenter.setTag(matches.get(0).getId());
        tvCenter.setOnClickListener(this);
        tvRight.setText(matches.get(0).getTeamHome().getName());

        tvLeft = (TextView) convertView.findViewById(R.id.tv_g2_left);
        tvCenter = (TextView) convertView.findViewById(R.id.tv_g2_center);
        tvRight = (TextView) convertView.findViewById(R.id.tv_g2_right);
        tvLeft.setText(matches.get(1).getTeamAway().getName());
        res = "x-x";
        if (matches.get(1).getGoalTeamAway() >= 0 || matches.get(1).getGoalTeamHome() >= 0) {
            res = matches.get(1).getGoalTeamAway() + "-" + matches.get(1).getGoalTeamHome();
        }
        tvCenter.setText(res);
        tvCenter.setTag(matches.get(1).getId());
        tvCenter.setOnClickListener(this);
        tvRight.setText(matches.get(1).getTeamHome().getName());

        tvLeft = (TextView) convertView.findViewById(R.id.tv_g3_left);
        tvCenter = (TextView) convertView.findViewById(R.id.tv_g3_center);
        tvRight = (TextView) convertView.findViewById(R.id.tv_g3_right);
        tvLeft.setText(matches.get(2).getTeamAway().getName());
        res = "x-x";
        if (matches.get(2).getGoalTeamAway() >= 0 || matches.get(2).getGoalTeamHome() >= 0) {
            res = matches.get(2).getGoalTeamAway() + "-" + matches.get(2).getGoalTeamHome();
        }
        tvCenter.setText(res);
        tvCenter.setTag(matches.get(2).getId());
        tvCenter.setOnClickListener(this);
        tvRight.setText(matches.get(2).getTeamHome().getName());

        tvLeft = (TextView) convertView.findViewById(R.id.tv_g4_left);
        tvCenter = (TextView) convertView.findViewById(R.id.tv_g4_center);
        tvRight = (TextView) convertView.findViewById(R.id.tv_g4_right);
        tvLeft.setText(matches.get(3).getTeamAway().getName());
        res = "x-x";
        if (matches.get(3).getGoalTeamAway() >= 0 || matches.get(3).getGoalTeamHome() >= 0) {
            res = matches.get(3).getGoalTeamAway() + "-" + matches.get(3).getGoalTeamHome();
        }
        tvCenter.setText(res);
        tvCenter.setTag(matches.get(3).getId());
        tvCenter.setOnClickListener(this);
        tvRight.setText(matches.get(3).getTeamHome().getName());

        return convertView;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        GameFragment gameFragment = new GameFragment();
        bundle.putLong("match_id", (Long) v.getTag());
        gameFragment.setArguments(bundle);
        ft.replace(R.id.content_main, gameFragment);
        ft.commit();
    }
}
