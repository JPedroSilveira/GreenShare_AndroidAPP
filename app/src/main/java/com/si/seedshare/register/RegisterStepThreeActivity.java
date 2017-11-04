package com.si.seedshare.register;

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

import com.si.seedshare.LoginActivity;
import com.si.seedshare.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import pojo.User;

public class RegisterStepThreeActivity extends AppCompatActivity {

    private User user;
    private Typeface font;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step_three);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        this.font = Typeface.createFromAsset(getAssets(), "font_agengSans.ttf");
        startComponents();
        defineFonts();
        setUserWithBundleContent();
        startButtons();
    }

    private void startComponents() {
        this.etEmail = (EditText) findViewById(R.id.et_email);
        this.etPassword = (EditText) findViewById(R.id.et_password);
        this.etPasswordConfirm = (EditText) findViewById(R.id.et_password_confirm);
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvDateOfBirth = (TextView) findViewById(R.id.tv_date_of_birth);
        this.tvEmailRequest = (TextView) findViewById(R.id.tv_email_request);
        this.tvPasswordRequest = (TextView) findViewById(R.id.tv_password_request);
        this.tvPasswordConfirm = (TextView) findViewById(R.id.tv_password_confirm);
        this.tvNameTitle = (TextView) findViewById(R.id.tv_dateOfBirth_title);
        this.tvDateOfBirthTitle = (TextView) findViewById(R.id.tv_date_of_birth_title);
        this.tvEmailAlert = (TextView) findViewById(R.id.tv_email_alert);
        this.tvPasswordAlert = (TextView) findViewById(R.id.tv_password_alert);
        this.btNext = (Button) findViewById(R.id.bt_next);
    }

    private void defineFonts(){
        this.etEmail.setTypeface(this.font);
        this.etPassword.setTypeface(this.font);
        this.etPasswordConfirm.setTypeface(this.font);
        this.tvName.setTypeface(this.font);
        this.tvDateOfBirth.setTypeface(this.font);
        this.tvEmailRequest.setTypeface(this.font);
        this.tvPasswordRequest.setTypeface(this.font);
        this.tvPasswordConfirm.setTypeface(this.font);
        this.tvNameTitle.setTypeface(this.font);
        this.tvDateOfBirthTitle.setTypeface(this.font);
        this.tvPasswordAlert.setTypeface(this.font);
        this.tvEmailAlert.setTypeface(this.font);
        this.btNext.setTypeface(this.font);
    }

    private void setUserWithBundleContent(){
        Bundle bundle = getIntent().getExtras();
        this.user = new User();
        Date dateOfBirth = new Date();
        dateOfBirth.setTime(bundle.getLong("DateOfBirth"));
        this.user = new User();
        this.user.setName(bundle.getString("Name"));
        this.user.setDateOfBirth(dateOfBirth);
        if(!this.user.validName() || this.user.getDateOfBirth() == null){
            toLoginActivity();
            Toast.makeText(RegisterStepThreeActivity.this,R.string.invalid_user, Toast.LENGTH_SHORT).show();
            finish();
        }else{
            setTextViewNameAndEmail();
        }
    }

    private void setTextViewNameAndEmail(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.tvName.setText(this.user.getName());
        this.tvDateOfBirth.setText(dateFormat.format(this.user.getDateOfBirth()));
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
                if(password.equals(confirmPassword) && user.validEmail() && user.validPassword()) {
                    toRegisterConfirmActivity();
                }else {
                    if(!user.validEmail()) {
                        etEmail.setText("");
                        Toast.makeText(RegisterStepThreeActivity.this, R.string.invalidEmail, Toast.LENGTH_SHORT).show();
                    }
                    if(!user.validPassword()) {
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
        parameters.putLong("DateOfBirth", this.user.getDateOfBirth().getTime());
        parameters.putString("Password", this.user.getPassword());
        parameters.putString("Email", this.user.getEmail());
        itRegisterConfirmActivity.putExtras(parameters);
        startActivity(itRegisterConfirmActivity);
    }
}
