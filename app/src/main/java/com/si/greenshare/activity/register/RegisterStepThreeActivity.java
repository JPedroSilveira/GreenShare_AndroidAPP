package com.si.greenshare.activity.register;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.si.greenshare.activity.LoginActivity;
import com.si.greenshare.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.si.greenshare.helpers.IsHelperActivity;
import com.si.greenshare.pojo.User;

public class RegisterStepThreeActivity extends IsHelperActivity {

    private TextView tvName;
    private TextView tvDateOfBirth;
    private TextView tvEmailRequest;
    private TextView tvPasswordRequest;
    private TextView tvPasswordConfirm;
    private TextView tvNameTitle;
    private TextView tvDateOfBirthTitle;
    private TextView tvEmailAlert;
    private TextView tvPasswordAlert;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPasswordConfirm;
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
        this.etEmail = (EditText) findViewById(R.id.et_email);
        this.etPassword = (EditText) findViewById(R.id.et_password);
        this.etPasswordConfirm = (EditText) findViewById(R.id.et_password_confirm);
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvDateOfBirth = (TextView) findViewById(R.id.tv_date_of_birth);
        this.tvEmailRequest = (TextView) findViewById(R.id.tv_question_name);
        this.tvPasswordRequest = (TextView) findViewById(R.id.tv_password_request);
        this.tvPasswordConfirm = (TextView) findViewById(R.id.tv_password_confirm);
        this.tvNameTitle = (TextView) findViewById(R.id.tv_dateOfBirth_title);
        this.tvDateOfBirthTitle = (TextView) findViewById(R.id.tv_date_of_birth_title);
        this.tvEmailAlert = (TextView) findViewById(R.id.tv_email_alert);
        this.tvPasswordAlert = (TextView) findViewById(R.id.tv_password_alert);
        this.btNext = (Button) findViewById(R.id.bt_next);
    }

    private void getUserOfBundleContent(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.user = new Gson().fromJson(extras.getString("User"), User.class);
        }else{
            Toast.makeText(RegisterStepThreeActivity.this,R.string.unexpectedErrorBundle, Toast.LENGTH_SHORT).show();
            toLoginActivity();
        }
    }

    private void toLoginActivity(){
        Intent itLoginActivity = new Intent(RegisterStepThreeActivity.this, LoginActivity.class);
        startActivity(itLoginActivity);
    }

    private void startButtons(){
        this.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etPasswordConfirm.getText().toString();
                user.setEmail(email);
                user.setPassword(password);
                if(password.equals(confirmPassword) && user.isValid()) {
                    toRegisterConfirmActivity();
                }else {
                    if(!user.isValid()) {
                        etEmail.setText("");
                        Toast.makeText(RegisterStepThreeActivity.this, R.string.invalidEmail, Toast.LENGTH_SHORT).show();
                    }
                    if(!user.isValid()) {
                        etPassword.setText("");
                        Toast.makeText(RegisterStepThreeActivity.this, R.string.invalidPassword, Toast.LENGTH_SHORT).show();
                    }
                    if(!password.equals(confirmPassword)){
                        etPassword.setText("");
                        etPasswordConfirm.setText("");
                        Toast.makeText(RegisterStepThreeActivity.this, R.string.invalidaConfirmPassword, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void toRegisterConfirmActivity(){
        Intent itRegisterConfirmActivity = new Intent(this, RegisterConfirm.class);
        Bundle parameters = new Bundle();
        parameters.putString("Name",this.user.getName());
        parameters.putString("Password", this.user.getPassword());
        parameters.putString("Email", this.user.getEmail());
        itRegisterConfirmActivity.putExtras(parameters);
        startActivity(itRegisterConfirmActivity);
    }
}
