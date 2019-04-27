package com.example.anil.laundry.reports;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.anil.laundry.R;
import com.example.anil.laundry.dashboard.Dashboard;

public class FinalReport extends AppCompatActivity {

    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_report);

        backButton();

    }

    private void backButton() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(FinalReport.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
}
