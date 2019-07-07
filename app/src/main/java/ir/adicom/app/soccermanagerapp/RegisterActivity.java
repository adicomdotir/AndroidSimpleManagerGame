package ir.adicom.app.soccermanagerapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ir.adicom.app.soccermanagerapp.data.FirstData;
import ir.adicom.app.soccermanagerapp.data.LocalData;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private Spinner mSpinnerTeams;
    private String mSelectedTeam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar_layout);

        mSpinnerTeams = (Spinner) findViewById(R.id.sp_teams);
        List<String> teams = generateSampleTeam();

//        Arrays.sort(FirstData.TEAM_NAMES);
//        teams.addAll(Arrays.asList(FirstData.TEAM_NAMES));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, teams);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerTeams.setAdapter(adapter);
        mSpinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedTeam = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSelectedTeam = null;
            }
        });

        Button btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedTeam == null || mSelectedTeam.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Please select a team!!", Toast.LENGTH_SHORT).show();
                } else {
                    ((App) getApplication()).getDaoSession().getTeamDao().deleteAll();
                    ((App) getApplication()).getDaoSession().getPlayerDao().deleteAll();
                    ((App) getApplication()).getDaoSession().getMatchDao().deleteAll();
                    LocalData localData = new LocalData();
                    localData.init((App) getApplication());
                    localData.create(mSelectedTeam);
                    localData.drawSchedule();
                    finish();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }
            }
        });

    }

    private List<String> generateSampleTeam() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int tnf = (int) (Math.random() * FirstData.TEAM_NAMES_FIRST.length);
            int tns = (int) (Math.random() * FirstData.TEAM_NAMES_SECOND.length);
            String name = FirstData.TEAM_NAMES_FIRST[tnf] + " " + FirstData.TEAM_NAMES_SECOND[tns];
            stringList.add(name);
        }
        return stringList;
    }
}
