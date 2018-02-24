package ir.adicom.app.soccermanagerapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.adicom.app.soccermanagerapp.data.DatabaseHandler;
import ir.adicom.app.soccermanagerapp.model.Player;
import ir.adicom.app.soccermanagerapp.model.Team;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initNavInfo(navigationView);
    }

    private void initNavInfo(NavigationView navigationView) {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        Team team = db.getTeam(1);
        TextView txtNavTeamName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_team_name);
        txtNavTeamName.setText(team.getName());
        TextView txtNavTeamNickname = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_team_nickname);
        txtNavTeamNickname.setText(team.getNickname());

        float overall = 0;
        int playerCount = 0;
        List<Player> playerList = db.getAllPlayers(1);
        for (Player p : playerList) {
            playerCount++;
            overall += p.getPower();
        }
        overall /= (float) playerCount;
        StringBuilder sb = new StringBuilder();
        sb.append(team.getName() + "\n");
        sb.append("Overall: " + overall + "\n");
        sb.append("Position: " + 1 + "\n");
        sb.append("Players Count: " + playerCount);

        TextView txtHome = (TextView) findViewById(R.id.txt_home);
        txtHome.setText(sb.toString());
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_players) {

        } else if (id == R.id.nav_matches) {

        } else if (id == R.id.nav_league) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_contact) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
