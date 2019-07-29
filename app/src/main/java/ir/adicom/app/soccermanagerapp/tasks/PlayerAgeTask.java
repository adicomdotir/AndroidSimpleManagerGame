package ir.adicom.app.soccermanagerapp.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import java.util.List;

import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.PlayerDao;
import ir.adicom.app.soccermanagerapp.views.HomeFragment;

/**
 *
 * Created by adicom on 7/30/19.
 */

public class PlayerAgeTask extends AsyncTask<List<Player>, String, Boolean> {
    private PlayerDao playerDao;
    private ProgressDialog dialog;
    private HomeFragment homeFragment;

    public PlayerAgeTask(HomeFragment fragmentActivity, PlayerDao playerDao) {
        this.playerDao = playerDao;
        this.dialog = new ProgressDialog(fragmentActivity.getContext());
        this.homeFragment = fragmentActivity;
    }

    @Override
    protected Boolean doInBackground(List<Player>... param) {
        List<Player> players = param[0];
        for (int i = 0; i < players.size(); i++) {
            int ageSub = players.get(i).getAgeSub();
            int age = players.get(i).getAge();
            ageSub += 1;
            if (ageSub > 111) {
                ageSub = 0;
                age += 1;
            }
            players.get(i).setAgeSub(ageSub);
            players.get(i).setAge(age);
            playerDao.update(players.get(i));
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
        homeFragment.onClickContinue();
    }
}
