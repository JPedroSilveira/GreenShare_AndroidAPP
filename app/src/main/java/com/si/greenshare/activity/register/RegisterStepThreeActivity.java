package com.si.greenshare.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.si.greenshare.activity.LoginActivity;
import com.si.greenshare.R;

import com.si.greenshare.helpers.IsHelperActivity;
import com.si.greenshare.pojo.User;

public class RegisterStepThreeActivity extends IsHelperActivity {

    private EditText etPassword, etPasswordConfirm;
    private TextView tvEmail;
    private Button btNext;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step_three);
        startComponents();
        getUserOfBundleContent();
        startButtons();
    }

    private void startComponents() {
        this.etPassword = findViewById(R.id.et_password);
        this.etPasswordConfirm = findViewById(R.id.et_password_confirm);
        this.btNext = findViewById(R.id.bt_next);
        this.tvEmail = findViewById(R.id.tv_email_title);
    }

    private void getUserOfBundleContent() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.user = (User)getIntent().getSerializableExtra("User");
            if(isNotNull(this.user)){
                if (!user.isValidEmail() || !user.isValidName() || !user.isValidPhoneNumber()) {
                    showToast(getApplicationContext(), getString(R.string.unexpectedErrorReturnActivity));
                    toStepTwoActivity();
                }
                tvEmail.setText(this.user.getEmail());
            }else{
                showToast(getApplicationContext(), getString(R.string.unexpectedErrorReturnActivity));
                toStepTwoActivity();
            }
        } else {
            showToast(getApplicationContext(), getString(R.string.unexpectedErrorReturnActivity));
            toLoginActivity();
        }
    }

    private void toLoginActivity() {
        Intent itLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(itLoginActivity);
    }

    private void toStepTwoActivity() {
        Intent itStepTwoActivity = new Intent(this, RegisterStepTwoActivity.class);
        startActivity(itStepTwoActivity);
    }

    private void startButtons() {
        this.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String confirmPassword = etPasswordConfirm.getText().toString();
                user.setPassword(password);
                if (password.equals(confirmPassword) && user.isValid()) {
                    if (user.isValidPassword()) {
                        etPassword.setBackground(getDrawableInvalidBackgroundField());
                        showToast(getApplicationContext(), concatStringList(user.getValidationErrors()));
                    } else {
                        validBackgroundFields();
                        toRegisterStepFourActivity();
                    }
                } else {
                    etPasswordConfirm.setBackground(getDrawableInvalidBackgroundField());
                    showToast(getApplicationContext(), getString(R.string.invalidPassword));
                }
            }
        });
    }

    private void toRegisterStepFourActivity() {
        Intent itRegisterStepFourActivity = new Intent(this, RegisterStepFourActivity.class);
        itRegisterStepFourActivity.putExtra("User", this.user);
        startActivity(itRegisterStepFourActivity);
    }

    private void validBackgroundFields() {
        etPassword.setBackground(getDrawableValidBackgroundField());
        etPasswordConfirm.setBackground(getDrawableValidBackgroundField());
    }
}
