package ir.adicom.app.soccermanagerapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ir.adicom.app.soccermanagerapp.R;
import ir.adicom.app.soccermanagerapp.model.Player;

/**
 * Created by adicom on 4/20/18.
 */

public class PlayersAdapter extends ArrayAdapter<Player> {

    public PlayersAdapter(Context context, Player[] players) {
        super(context, 0, players);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Player player = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_player, parent, false);
        }

        TextView tvPlayerName = (TextView) convertView.findViewById(R.id.tv_player_name);
        TextView tvPlayerAge = (TextView) convertView.findViewById(R.id.tv_player_age);
        TextView tvPlayerScoring = (TextView) convertView.findViewById(R.id.tv_player_scoring);
        TextView tvPlayerGk = (TextView) convertView.findViewById(R.id.tv_player_gk);
        tvPlayerName.setText(player.getName());
        tvPlayerAge.setText("Age: " + player.getAge());
        tvPlayerScoring.setText("SC: " + (int) player.getScoring());
        tvPlayerGk.setText("GK: " + (int) player.getGoalkeeper());

        return convertView;
    }
}
