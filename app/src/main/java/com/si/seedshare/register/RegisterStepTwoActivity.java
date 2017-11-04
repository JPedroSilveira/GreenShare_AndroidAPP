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

import com.si.seedshare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import pojo.User;


public class RegisterStepTwoActivity extends AppCompatActivity {

    private Typeface font;
    private EditText etName, etBirthDay;
    private TextView tvQuestionOne, tvQuestionTwo;
    private Button btNext;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step_two);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        this.font = Typeface.createFromAsset(getAssets(), "font_agengSans.ttf");
        startComponents();
        defineFonts();
        startButtons();
    }

    private void startComponents() {
        this.etName = (EditText) findViewById(R.id.et_name);
        this.etBirthDay = (EditText) findViewById(R.id.et_birthday);
        this.tvQuestionOne = (TextView) findViewById(R.id.tv_email_request);
        this.tvQuestionTwo = (TextView) findViewById(R.id.tv_question_two);
        this.btNext = (Button) findViewById(R.id.bt_next);
    }

    private void defineFonts(){
        this.etName.setTypeface(this.font);
        this.etBirthDay.setTypeface(this.font);
        this.tvQuestionOne.setTypeface(this.font);
        this.tvQuestionTwo.setTypeface(this.font);
        this.btNext.setTypeface(this.font);
    }

    private void startButtons(){
        this.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date dateOfBirth;
                Calendar calendar = Calendar.getInstance();
                try {
                    dateOfBirth = df.parse(etBirthDay.getText().toString());
                    calendar.setTime(dateOfBirth);
                    user = new User();
                    user.setName(etName.getText().toString());
                    user.setDateOfBirth(calendar.getTime());
                    if(user.validName()){
                        toRegisterStepThreeActivity();
                    }else{
                        etName.setText("");
                        Toast.makeText(RegisterStepTwoActivity.this,R.string.invalid_name, Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    etBirthDay.setText("");
                    Toast.makeText(RegisterStepTwoActivity.this,R.string.invalid_birthday, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void toRegisterStepThreeActivity(){
        Intent itRegisterStepThreeActivity = new Intent(RegisterStepTwoActivity.this, RegisterStepThreeActivity.class);
        Bundle parameters = new Bundle();
        parameters.putString("Name",this.user.getName());
        parameters.putLong("DateOfBirth", this.user.getDateOfBirth().getTime());
        itRegisterStepThreeActivity.putExtras(parameters);
        startActivity(itRegisterStepThreeActivity);
    }
}
