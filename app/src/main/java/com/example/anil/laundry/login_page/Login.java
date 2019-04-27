package com.example.anil.laundry.login_page;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.anil.laundry.dashboard.Dashboard;
import com.example.anil.laundry.forgot_password.ForgotPassword;
import com.example.anil.laundry.R;
import com.example.anil.laundry.terms_conditions.TermsConditions;
import com.example.anil.laundry.register.Register;

public class Login extends AppCompatActivity {

    TextInputLayout input_layout_mobile, input_layout_password;
    TextInputEditText et_mobile, et_password;
    Button btn_login, btn_register;
    TextView tv_forgot_pass, tv_TC;

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findAllIds();

        register();
        termsConditions();
        forgotPassword();
    }

    private void findAllIds() {

        input_layout_mobile = findViewById(R.id.input_layout_mobile);
        input_layout_password = findViewById(R.id.input_layout_password);

        et_mobile = findViewById(R.id.et_mobile);
        et_password = findViewById(R.id.et_password);

        tv_forgot_pass = findViewById(R.id.tv_forgot_pass);
        tv_TC = findViewById(R.id.tv_TC);

        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
    }

    private void forgotPassword() {
        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private void termsConditions() {
        tv_TC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, TermsConditions.class);
                startActivity(intent);
            }
        });
    }

    private void register() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public void onLogin(View view) {
        if (!validateMobile()) {
            et_mobile.setError("");
        } else if (!validatePassword()) {
            et_password.setError("");
        } else {

            Intent intent = new Intent(Login.this, Dashboard.class);
            startActivity(intent);
        }
    }

    private boolean validateMobile() {
        String mobile = et_mobile.getText().toString().trim();
        if (mobile.isEmpty()) {
            input_layout_mobile.setError(getString(R.string.err_msg_mobile));
            requestFocus(et_mobile);
            return false;
        } else {
            input_layout_mobile.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        String password = et_password.getText().toString().trim();
        if (password.isEmpty()) {
            input_layout_password.setError(getString(R.string.err_msg_password));
            requestFocus(et_password);
            return false;
        } else {
            input_layout_password.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void vibrate() {
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibrator.vibrate(500);
        }
    }
}
