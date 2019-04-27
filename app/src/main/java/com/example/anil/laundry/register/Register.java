package com.example.anil.laundry.register;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anil.laundry.login_page.DatabaseWorker;
import com.example.anil.laundry.login_page.Login;
import com.example.anil.laundry.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    TextInputLayout input_layout_first_name, input_layout_last_name, input_layout_email, input_layout_password,
            input_layout_re_password, input_layout_mobile;

    TextInputEditText et_first_name, et_last_name, et_email, et_password, et_re_password, et_mobile;
    TextView tv_map, tv_login, tv_InternetConnection;
    ImageView iv_back;
    Button btn_register;
    Button btn_register_popup_submit, btn_register_popup_cancel;
    PopupWindow popupWindow;
    RelativeLayout rel_register1;

    String mobile_number, password1, re_password;
    String str_first_name, str_last_name, str_email, str_mobile, str_password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findAllIds();
        //  registerPopupSubmit();
        backButton();
        loginButton();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null) {
            tv_InternetConnection.setVisibility(View.INVISIBLE);
            btn_register.setEnabled(true);
        }
        else {
            btn_register.setEnabled(false);
            btn_register.setError("Network Not Found");
            Toast.makeText(getApplicationContext(), "Network Not Found", Toast.LENGTH_LONG).show();
        }

    }

    private void loginButton() {
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void backButton() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void findAllIds() {

        tv_InternetConnection = findViewById(R.id.tv_InternetConnection);

        input_layout_first_name = findViewById(R.id.input_layout_first_name);
        input_layout_last_name = findViewById(R.id.input_layout_last_name);
        input_layout_email = findViewById(R.id.input_layout_email);
        input_layout_password = findViewById(R.id.input_layout_password);
        input_layout_re_password = findViewById(R.id.input_layout_re_password);
        input_layout_mobile = findViewById(R.id.input_layout_mobile);

        et_first_name = findViewById(R.id.et_first_name);
        et_last_name = findViewById(R.id.et_last_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_re_password = findViewById(R.id.et_re_password);
        et_mobile = findViewById(R.id.et_mobile);

        tv_login = findViewById(R.id.tv_login);
        btn_register = findViewById(R.id.btn_register);
        iv_back = findViewById(R.id.iv_back);

        btn_register_popup_submit = findViewById(R.id.btn_register_popup_submit);
        btn_register_popup_cancel = findViewById(R.id.btn_register_popup_cancel);
        rel_register1 = findViewById(R.id.rel_register1);
    }

    private boolean validateFirstName() {
        if (et_first_name.getText().toString().trim().isEmpty()) {
            input_layout_first_name.setError(getString(R.string.err_msg_first_name));
            requestFocus(et_first_name);
            return false;
        } else {
            input_layout_first_name.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateLastName() {
        if (et_last_name.getText().toString().trim().isEmpty()) {
            input_layout_last_name.setError(getString(R.string.err_msg_last_name));
            requestFocus(et_last_name);
            return false;
        } else {
            input_layout_last_name.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        String email = et_email.getText().toString().trim();
        if (email.isEmpty() || !validEmail(email)) {
            input_layout_email.setError(getString(R.string.err_msg_email));
            requestFocus(et_email);
            return false;
        } else {
            input_layout_email.setErrorEnabled(false);
        }
        return true;
    }

    public static boolean validatePassword(String p) {
        Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15})");

        Matcher match = pattern.matcher(p);
        return (match.find() && match.group().equals(p));

    }

    public static boolean validateRePassword(String re) {
        Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15})");

        Matcher match = pattern.matcher(re);
        return (match.find() && match.group().equals(re));

    }

    public static boolean validateMobile(String s) {
        Pattern p = Pattern.compile("^[7-9][0-9]{9}$");
        Matcher m = p.matcher(s);

        return (m.find() && m.group().equals(s));
    }

    private static boolean validEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_first_name:
                    validateFirstName();
                    break;
                case R.id.et_last_name:
                    validateLastName();
                    break;
                case R.id.et_email:
                    validateEmail();
                    break;
                case R.id.et_password:
                    validatePassword(password1);
                    break;
                case R.id.et_re_password:
                    validateRePassword(re_password);
                    break;
                case R.id.et_mobile:
                    validateMobile(mobile_number);
                    break;
            }
        }
    }

    /*public void registerPopupSubmit() {
        btn_register_popup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) Register.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                @SuppressLint("InflateParams") View customView = layoutInflater.inflate(R.layout.popup_register,null);

                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(rel_register1, Gravity.CENTER, 0, 0);

                btn_register_popup_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }*/

    public void onRegister(View view) {
        mobile_number = Objects.requireNonNull(et_mobile.getText()).toString().trim();
        password1 = Objects.requireNonNull(et_password.getText()).toString().trim();
        re_password = Objects.requireNonNull(et_re_password.getText()).toString().trim();

        if (!validateFirstName()) {
            et_first_name.setError("");
        } else if (!validateLastName()) {
            et_last_name.setError("");
        } else if (!validateEmail()) {
            et_email.setError("");
        } else if (!validatePassword(password1)) {
            et_re_password.setError("Password not valid,minimum 8 character with 1 Uppercase letter,1 digit &amp; 1 special symbol");
            requestFocus(et_password);
        } else if (!validateRePassword(re_password)) {
            if (!validateRePassword(re_password) == (validatePassword(password1))) {
                et_re_password.setError("Password not match ");
                requestFocus(et_re_password);
            }
        } else if (!validateMobile(mobile_number)) {
            et_mobile.setError("Enter valid mobile number");
            requestFocus(et_mobile);
        } else {

            registerUser();

        }
    }
    private void registerUser() {

        str_first_name = et_first_name.getText().toString().trim();
        str_last_name = et_last_name.getText().toString().trim();
        str_email = et_password.getText().toString().trim();
        str_mobile = et_mobile.getText().toString().trim();
        str_password = et_email.getText().toString().trim();
        String type = "register";

        DatabaseWorker databaseWorker = new DatabaseWorker();
        databaseWorker.execute(type, str_first_name, str_last_name, str_email, str_mobile, str_password);
        finish();

/*
        progressDialog.setMessage("Registering....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Intent intent = new Intent(Register.this, Login.class);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("FirstName", str_first_name);
                params.put("LastName", str_last_name);
                params.put("Email", str_email);
                params.put("Mobile", str_mobile);
                params.put("Password", str_password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/
    }
}
