package com.si.greenshare.activity.register;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.si.greenshare.activity.LoginActivity;
import com.si.greenshare.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.si.greenshare.pojo.User;

public class RegisterConfirm extends AppCompatActivity {

    private Typeface font;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvDateOfBirth;
    private TextView tvNameTitle;
    private TextView tvEmailTitle;
    private TextView tvDateOfBirthTitle;
    private Button btConfirm;
    private Button btReset;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_confirm);
        this.font = Typeface.createFromAsset(getAssets(), "font_agengSans.ttf");
        startComponents();
        defineFonts();
        setUserWithBundleContent();
        startButtons();
    }

    private void startComponents(){
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvEmail = (TextView) findViewById(R.id.tv_email);
        this.tvDateOfBirth = (TextView) findViewById(R.id.tv_date_of_birth);
        this.tvNameTitle = (TextView) findViewById(R.id.tv_name_title);
        this.tvEmailTitle = (TextView) findViewById(R.id.tv_email_title);
        this.tvDateOfBirthTitle = (TextView) findViewById(R.id.tv_date_of_birth_title);
        this.btConfirm = (Button) findViewById(R.id.bt_confirm);
        this.btReset = (Button) findViewById(R.id.bt_reset);
    }

    private void defineFonts(){
        this.tvName.setTypeface(this.font);
        this.tvEmail.setTypeface(this.font);
        this.tvDateOfBirth.setTypeface(this.font);
        this.tvDateOfBirthTitle.setTypeface(this.font);
        this.tvNameTitle.setTypeface(this.font);
        this.tvEmailTitle.setTypeface(this.font);
        this.btConfirm.setTypeface(this.font);
        this.btReset.setTypeface(this.font);
    }

    private void startButtons(){
        this.btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.isValid()){
                    registerUserOnApi();
                }else{
                    clearTextViews();
                    toLoginActivity();
                    Toast.makeText(RegisterConfirm.this,R.string.invalid_user, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        this.btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextViews();
                Toast.makeText(RegisterConfirm.this,R.string.resetMessage, Toast.LENGTH_SHORT).show();
                toLoginActivity();
            }
        });
    }

    private void clearTextViews(){
        this.tvDateOfBirth.setText("");
        this.tvEmail.setText("");
        this.tvName.setText("");
    }

    private void registerUserOnApi(){
        Toast.makeText(this,"Cadastrado", Toast.LENGTH_SHORT).show();
        clearTextViews();
    }

    private void setUserWithBundleContent(){
        Bundle bundle = getIntent().getExtras();
        this.user = new User();
        Date dateOfBirth = new Date();
        dateOfBirth.setTime(bundle.getLong("DateOfBirth"));
        this.user = new User();
        this.user.setName(bundle.getString("Name"));
        if(!this.user.isValid()){
            toLoginActivity();
            Toast.makeText(this,R.string.invalid_user, Toast.LENGTH_SHORT).show();
            finish();
        }else{
            setTextViewsUser();
        }
    }

    private void setTextViewsUser(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.tvName.setText(this.user.getName());
        this.tvEmail.setText(this.user.getEmail());
    }

    private void toLoginActivity(){
        Intent itLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(itLoginActivity);
    }
}
