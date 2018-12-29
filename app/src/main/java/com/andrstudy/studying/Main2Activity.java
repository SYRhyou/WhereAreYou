package com.andrstudy.studying;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private Button myLocation;
    private Button eGroup;
    private Button mGroup;
    private Button jGroup;
    private TextView nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myLocation = (Button)findViewById(R.id.myLocation);
        nick = (TextView) findViewById(R.id.nick);

        myLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MapsActivity.class);
                Main2Activity.this.startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String message = "Welcome, " + userID + "님";
        nick.setText(message);
    }
}
