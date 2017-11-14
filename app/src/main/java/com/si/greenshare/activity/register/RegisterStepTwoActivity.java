package com.si.greenshare.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.si.greenshare.R;

import com.si.greenshare.helpers.IsHelperActivity;
import com.si.greenshare.pojo.User;


public class RegisterStepTwoActivity extends IsHelperActivity {

    private EditText etName, etEmail, etPhoneNumber;
    private Button btNext;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step_two);
        startComponents();
        startButtons();
    }

    private void startComponents() {
        this.etName = findViewById(R.id.et_name);
        this.etEmail = findViewById(R.id.et_email);
        this.etPhoneNumber = findViewById(R.id.et_phone_number);
        this.btNext = findViewById(R.id.bt_next);
    }

    private void startButtons() {
        this.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = true;
                user = new User();
                user.setName(etName.getText().toString());
                user.setEmail(etEmail.getText().toString());
                user.setPhoneNumber(etPhoneNumber.getText().toString());
                validBackgroundFields();
                if (!user.isValidEmail()) {
                    showToast(getApplicationContext(), concatStringList(user.getValidationErrors()), Toast.LENGTH_SHORT);
                    etEmail.setBackground(getDrawableInvalidBackgroundField());
                    success = false;
                }
                if (!user.isValidName()) {
                    showToast(getApplicationContext(), concatStringList(user.getValidationErrors()), Toast.LENGTH_SHORT);
                    etName.setBackground(getDrawableInvalidBackgroundField());
                    success = false;
                }
                if (!user.isValidPhoneNumber()) {
                    showToast(getApplicationContext(), concatStringList(user.getValidationErrors()), Toast.LENGTH_SHORT);
                    etPhoneNumber.setBackground(getDrawableInvalidBackgroundField());
                    success = false;
                }
                if (success) {
                    toRegisterStepThreeActivity();
                }
            }
        });
    }

    private void toRegisterStepThreeActivity() {
        Intent itRegisterStepThreeActivity = new Intent(this, RegisterStepThreeActivity.class);
        itRegisterStepThreeActivity.putExtra("User", this.user);
        startActivity(itRegisterStepThreeActivity);
    }

    private void validBackgroundFields() {
        etEmail.setBackground(getDrawableValidBackgroundField());
        etName.setBackground(getDrawableValidBackgroundField());
        etPhoneNumber.setBackground(getDrawableValidBackgroundField());
    }
}
