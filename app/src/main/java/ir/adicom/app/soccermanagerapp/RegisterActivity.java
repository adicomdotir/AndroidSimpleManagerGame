package ir.adicom.app.soccermanagerapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ir.adicom.app.soccermanagerapp.data.DatabaseHandler;
import ir.adicom.app.soccermanagerapp.data.GenerateOperator;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;
    private EditText edtName, edtNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar_layout);

        databaseHandler = new DatabaseHandler(getApplicationContext());

        edtName = (EditText) findViewById(R.id.edt_team_name);
        edtNickname = (EditText) findViewById(R.id.edt_team_nickname);

        Button btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.length() != 0 && edtNickname.length() != 0) {
                    databaseHandler.deleteAllTable();
                    new GenerateOperator().init(databaseHandler);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    edtName.setHintTextColor(Color.RED);
                    edtNickname.setHintTextColor(Color.RED);
                }
            }
        });
    }
}
