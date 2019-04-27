package com.example.anil.laundry.splash_screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.anil.laundry.login_page.Login;
import com.example.anil.laundry.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        threadTimer();

    }

    private void threadTimer() {
        Thread thread = new Thread() {
            public void run()
            {
                try{
                    sleep(2000);
                }catch(InterruptedException e)
                {
                    e.printStackTrace();
                }finally
                {
                    Intent intent = new Intent(SplashActivity.this, Login.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}
