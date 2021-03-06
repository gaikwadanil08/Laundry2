package com.example.anil.laundry.pickup_delivery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anil.laundry.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FragmentPickup extends Fragment {
    View v;
    TextView tv_today_date, tv_tomorrow_date, tv_3rd_day_date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.pickup,container,false);

        findAllIds();

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date thirdDay = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String todayAsString = dateFormat.format(today);
        String tomorrowAsString = dateFormat.format(tomorrow);
        String thirdDayAsString = dateFormat.format(thirdDay);

        tv_today_date.setText(todayAsString);
        tv_tomorrow_date.setText(tomorrowAsString);
        tv_3rd_day_date.setText(thirdDayAsString);

        return v;
    }

    private void findAllIds() {
        tv_today_date = v.findViewById(R.id.tv_today_date);
        tv_tomorrow_date = v.findViewById(R.id.tv_tomorrow_date);
        tv_3rd_day_date = v.findViewById(R.id.tv_3rd_day_date);
    }
}
