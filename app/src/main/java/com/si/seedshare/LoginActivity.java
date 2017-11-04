package com.si.seedshare;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.si.seedshare.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;
    private Button btRegister;
    private Typeface font;
    private TextView tvRegisterQuestion;
    private TextView tvAppName;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        this.font = Typeface.createFromAsset(getAssets(), "font_agengSans.ttf");
        startComponents();
        defineFonts();
        startButtons();
    }
    private void startButtons(){
        this.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                cleanEditTexts();
                toMenuActivity();
                finish();
            }
        });
        this.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanEditTexts();
                toRegisterActivity();
            }
        });
    }
    private void toMenuActivity(){
        Intent itMenuActivity = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(itMenuActivity);
    }
    private void toRegisterActivity(){
        Intent itRegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(itRegisterActivity);
    }
    private void cleanEditTexts(){
        this.etEmail.setText("");
        this.etPassword.setText("");
    }
    private void startComponents() {
        this.btLogin = (Button) findViewById(R.id.bt_login);
        this.btRegister = (Button) findViewById(R.id.bt_register);
        this.tvRegisterQuestion = (TextView) findViewById(R.id.tv_register_question);
        this.tvAppName = (TextView) findViewById(R.id.tv_appname);
        this.etEmail = (EditText) findViewById(R.id.et_email);
        this.etPassword = (EditText) findViewById(R.id.et_password);
    }
    private void defineFonts(){
        this.btLogin.setTypeface(this.font);
        this.btRegister.setTypeface(this.font);
        this.tvRegisterQuestion.setTypeface(this.font);
        this.tvAppName.setTypeface(this.font);
        this.etPassword.setTypeface(this.font);
        this.etEmail.setTypeface(this.font);
    }
}
