package com.example.anil.laundry.why_us;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.anil.laundry.R;

public class WhyUsDisplayList extends AppCompatActivity {

    TextInputLayout input_layout_heading, input_layout_desc;
    TextInputEditText et_AddWhyUsHeading, et_AddWhyUsDesc;
    Button btn_addWhyUs;
    String heading, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_why_us_users_list);

        findAllIds();

        validateSaveButton();

    }

    private void findAllIds() {
        input_layout_heading = (TextInputLayout) findViewById(R.id.input_layout_heading);
        input_layout_desc = (TextInputLayout) findViewById(R.id.input_layout_desc);

        et_AddWhyUsHeading = (TextInputEditText) findViewById(R.id.et_AddWhyUs);
        et_AddWhyUsDesc = (TextInputEditText) findViewById(R.id.et_AddWhyUsDesc);

        btn_addWhyUs = (Button) findViewById(R.id.btn_addWhyUs);
    }

    private boolean validateHeading() {
        if (et_AddWhyUsHeading.getText().toString().trim().isEmpty()) {
            input_layout_heading.setError(getString(R.string.err_msg_heading));
            requestFocus(et_AddWhyUsHeading);
            return false;
        } else {
            input_layout_heading.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDesc() {
        if (et_AddWhyUsDesc.getText().toString().trim().isEmpty()) {
            input_layout_desc.setError(getString(R.string.err_msg_desc));
            requestFocus(et_AddWhyUsDesc);
            return false;
        } else {
            input_layout_desc.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void validateSaveButton() {

        btn_addWhyUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateHeading()) {
                    et_AddWhyUsHeading.setError("Please Enter Heading");
                } else if (!validateDesc()) {
                    et_AddWhyUsDesc.setError("Please Enter Description");
                } else {

                    Intent intent = new Intent(WhyUsDisplayList.this, WhyUs.class);
                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });
    }
}