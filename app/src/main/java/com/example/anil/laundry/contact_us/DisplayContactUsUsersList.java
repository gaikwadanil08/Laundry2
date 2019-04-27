package com.example.anil.laundry.contact_us;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.anil.laundry.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayContactUsUsersList extends AppCompatActivity {

    TextView tv_name, tv_email, tv_mobile, tv_message;
    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact_us_userslist);

    }
}


