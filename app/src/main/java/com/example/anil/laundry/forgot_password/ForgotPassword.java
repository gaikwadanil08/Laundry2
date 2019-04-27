package com.example.anil.laundry.forgot_password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.anil.laundry.R;
import com.example.anil.laundry.login_page.Login;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity {

    TextInputLayout input_layout_mobile, input_layout_OTP;
    TextInputLayout input_layout_password, input_layout_re_password;

    TextInputEditText et_mobile, et_OTP;
    TextInputEditText et_password, et_re_password;

    LinearLayout ll_reset_pass, ll_register;

    Button btn_register, btn_submit;
    ImageView iv_back;

    String mobile_no, pass, rePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        findAllIds();
        backButton();

    }

    private void backButton() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void findAllIds() {
        input_layout_mobile = (TextInputLayout) findViewById(R.id.input_layout_mobile);
        input_layout_OTP = (TextInputLayout) findViewById(R.id.input_layout_OTP);
        input_layout_password = (TextInputLayout) findViewById(R.id.input_layout_password);
        input_layout_re_password = (TextInputLayout) findViewById(R.id.input_layout_re_password);

        et_mobile = (TextInputEditText) findViewById(R.id.et_mobile);
        et_OTP = (TextInputEditText) findViewById(R.id.et_OTP);
        et_password = (TextInputEditText) findViewById(R.id.et_password);
        et_re_password = (TextInputEditText) findViewById(R.id.et_re_password);

        btn_register = (Button) findViewById(R.id.btn_register);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        iv_back = (ImageView) findViewById(R.id.iv_back);

        ll_reset_pass = (LinearLayout) findViewById(R.id.ll_reset_pass);
        ll_register = (LinearLayout) findViewById(R.id.ll_register);

    }

    public void resetPassword(View view) {

        mobile_no = Objects.requireNonNull(et_mobile.getText()).toString().trim();

        if (!validateMobile(mobile_no)) {
            et_mobile.setError("Please Enter Correct Mobile Number");
            requestFocus(et_mobile);
        } else if (Objects.requireNonNull(et_OTP.getText()).toString().trim().isEmpty()) {
            et_password.setError("Please Enter Correct OTP");
            requestFocus(et_OTP);
        } else {
            ll_reset_pass.setVisibility(View.VISIBLE);
            ll_register.setVisibility(View.GONE);
        }
    }

    public void submit(View view) {
        pass = Objects.requireNonNull(et_password.getText()).toString().trim();
        rePass = Objects.requireNonNull(et_re_password.getText()).toString().trim();

        ll_reset_pass.setVisibility(View.VISIBLE);

        if (!validatePassword(pass)) {
            et_re_password.setError("Password not valid,minimum 8 character with 1 Uppercase letter,1 digit &amp; 1 special symbol");
            requestFocus(et_password);
        } else if (!validateRePassword(rePass)) {
            if (!validateRePassword(rePass) == (validatePassword(pass))) {
                et_re_password.setError("Password not match ");
                requestFocus(et_re_password);
            }
        } else {
            Intent intent = new Intent(ForgotPassword.this, Login.class);
            Toast.makeText(getApplicationContext(), "Password Reset Successfully", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    public static boolean validateMobile(String s) {
        Pattern p = Pattern.compile("^[7-9][0-9]{9}$");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    public static boolean validatePassword(String p) {
        Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15})");
        Matcher match = pattern.matcher(p);
        return (match.find() && match.group().equals(p));
    }

    public static boolean validateRePassword(String rp) {
        Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15})");
        Matcher match = pattern.matcher(rp);
        return (match.find() && match.group().equals(rp));
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
