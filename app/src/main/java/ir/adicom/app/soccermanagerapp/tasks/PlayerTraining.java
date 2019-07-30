package ir.adicom.app.soccermanagerapp.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;

import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.PlayerDao;
import ir.adicom.app.soccermanagerapp.views.HomeFragment;

/**
 *
 * Created by adicom on 7/30/19.
 */

public class PlayerTraining extends AsyncTask<List<Player>, String, Boolean> {
    private PlayerDao playerDao;
    private ProgressDialog dialog;
    private HomeFragment homeFragment;

    public PlayerTraining(HomeFragment fragmentActivity, PlayerDao playerDao) {
        this.playerDao = playerDao;
        this.dialog = new ProgressDialog(fragmentActivity.getContext());
        this.homeFragment = fragmentActivity;
    }

    @Override
    protected Boolean doInBackground(List<Player>... param) {
        List<Player> players = param[0];
        for (Player player: players) {
            float inc = calculateTraining(player);
            player.setScoring(inc + player.getScoring());
            playerDao.update(player);
        }
        return true;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected void onPostExecute(Boolean b) {
        super.onPostExecute(b);
        dialog.dismiss();
    }

    private float calculateTraining(Player player) {
        return (56 * 2 / (player.getAge() + player.getScoring())) / 40;
    }
}
