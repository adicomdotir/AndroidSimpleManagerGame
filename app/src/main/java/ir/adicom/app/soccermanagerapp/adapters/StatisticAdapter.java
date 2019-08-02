package ir.adicom.app.soccermanagerapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.model.StatisticViewModel;
import ir.adicom.app.soccermanagerapp.model.StatisticViewModel;

/**
 *
 * Created by Y.P on 4/20/18.
 */

public class StatisticAdapter extends ArrayAdapter<StatisticViewModel> {

    public StatisticAdapter(Context context, StatisticViewModel[] players) {
        super(context, 0, players);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StatisticViewModel statisticViewModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_statistic, parent, false);
        }

        TextView tvPlayerName = (TextView) convertView.findViewById(R.id.tv_player_name);
        TextView tvNumber = (TextView) convertView.findViewById(R.id.tv_goal_number);
        TextView tvTeamName = (TextView) convertView.findViewById(R.id.tv_team_name);

        tvPlayerName.setText(statisticViewModel.getPlayerName());
        tvNumber.setText("" + statisticViewModel.getGoalNumber());
        tvTeamName.setText(statisticViewModel.getTeamName());

        return convertView;
    }
}
