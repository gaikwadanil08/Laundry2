package com.example.anil.laundry.contact_us;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anil.laundry.about_us.AboutUs;
import com.example.anil.laundry.cloth_price_list.ClothPriceList;
import com.example.anil.laundry.download_app.DownloadApp;
import com.example.anil.laundry.how_it_works.HowItWorks;
import com.example.anil.laundry.login_page.DatabaseWorker;
import com.example.anil.laundry.login_page.Login;
import com.example.anil.laundry.our_service.OurServices;
import com.example.anil.laundry.R;
import com.example.anil.laundry.store_locator.StoreLocator;
import com.example.anil.laundry.terms_conditions.TermsConditions;
import com.example.anil.laundry.why_us.WhyUs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactUs extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageView iv_back;
    TextView tv_menu;

    TextInputLayout input_layout_name, input_layout_email, input_layout_mobile, input_layout_message;
    TextInputEditText et_name, et_email, et_mobile, et_message;
    Button btn_contact_us;

    String name, mobile, email, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        findAllIds();
        popupMenu();
        backButton();
        btnContactUs();
    }

    private void findAllIds() {
        input_layout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        input_layout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        input_layout_mobile = (TextInputLayout) findViewById(R.id.input_layout_mobile);
        input_layout_message = (TextInputLayout) findViewById(R.id.input_layout_message);

        et_name = (TextInputEditText) findViewById(R.id.et_name);
        et_email = (TextInputEditText) findViewById(R.id.et_email);
        et_mobile = (TextInputEditText) findViewById(R.id.et_mobile);
        et_message = (TextInputEditText) findViewById(R.id.et_message);

        btn_contact_us = (Button) findViewById(R.id.btn_contact);
    }

    private void backButton() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactUs.this, TermsConditions.class);
                startActivity(intent);
            }
        });

    }

    private void popupMenu() {
        tv_menu = (TextView) findViewById(R.id.tv_menu);
        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ContactUs.this, v);
                popup.setOnMenuItemClickListener(ContactUs.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });

    }

    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.price_list:
                Intent intent = new Intent(ContactUs.this, ClothPriceList.class);
                startActivity(intent);
                return true;
            case R.id.terms_cond:
                intent = new Intent(ContactUs.this, TermsConditions.class);
                startActivity(intent);
                return true;
            case R.id.why_us:
                intent = new Intent(ContactUs.this, WhyUs.class);
                startActivity(intent);
                return true;
            case R.id.download_app:
                intent = new Intent(ContactUs.this, DownloadApp.class);
                startActivity(intent);
                return true;
            case R.id.about_us:
                intent = new Intent(ContactUs.this, AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.our_services:
                intent = new Intent(ContactUs.this, OurServices.class);
                startActivity(intent);
                return true;
            case R.id.how_it_works:
                intent = new Intent(ContactUs.this, HowItWorks.class);
                startActivity(intent);
                return true;
            case R.id.store_locator:
                intent = new Intent(ContactUs.this, StoreLocator.class);
                startActivity(intent);
                return true;
            case R.id.contact_us:
                intent = new Intent(ContactUs.this, ContactUs.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    private boolean validateName() {
        if (et_name.getText().toString().trim().isEmpty()) {
            input_layout_name.setError(getString(R.string.err_msg_name));
            requestFocus(et_name);
            return false;
        } else {
            input_layout_name.setErrorEnabled(false);
        }
        return true;
    }

    public static boolean validateMobile(String s) {
        Pattern p = Pattern.compile("^[7-9][0-9]{9}$");
        Matcher m = p.matcher(s);

        return (m.find() && m.group().equals(s));

    }

    private static boolean validateEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateMessage() {
        if (et_message.getText().toString().trim().isEmpty()) {
            input_layout_message.setError(getString(R.string.err_msg_message));
            requestFocus(et_message);
            return false;
        } else {
            input_layout_message.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void btnContactUs() {
        btn_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String mobile = et_mobile.getText().toString().trim();
                String message = et_message.getText().toString().trim();

                String type = "contact_us";

                String mobile_no = et_mobile.getText().toString().trim();
                String email_id = et_email.getText().toString().trim();

                if (!validateName()) {
                    et_name.setError("Enter name ");
                } else if (!validateMobile(mobile_no)) {
                    et_mobile.setError("Enter Valid mobile no ");
                } else if (!validateEmail(email_id)) {
                    et_email.setError("Enter Email ID");
                } else if (!validateMessage()) {
                    et_message.setError("Type your message here ");
                } else {

                    Intent intent = new Intent(ContactUs.this, Login.class);

                    DatabaseWorker databaseWorker = new DatabaseWorker();
                    databaseWorker.execute(type, name, email, mobile, message);
                    finish();

                    startActivity(intent);
                }
            }
        });
    }
}