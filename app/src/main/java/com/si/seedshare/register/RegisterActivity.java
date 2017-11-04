package com.si.seedshare.register;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.si.seedshare.R;

public class RegisterActivity extends AppCompatActivity {

    private Typeface font;
    private TextView tvTitle, tvDescription;
    private Button btStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        this.font = Typeface.createFromAsset(getAssets(), "font_agengSans.ttf");
        startComponents();
        defineFonts();
        setUserWithBundleContent();
        startButtons();
    }

    private void startComponents() {
        this.tvTitle = (TextView) findViewById(R.id.tv_email_request);
        this.tvDescription = (TextView) findViewById(R.id.tv_description);
        this.btStart = (Button) findViewById(R.id.bt_start);
    }

    private void defineFonts(){
        this.tvTitle.setTypeface(this.font);
        this.tvDescription.setTypeface(this.font);
        this.btStart.setTypeface(this.font);
    }

    private void setUserWithBundleContent(){

    }

    private void startButtons(){
        this.btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterStepTwoActivity();
            }
        });
    }

    private void toRegisterStepTwoActivity(){
        Intent itRegisterStepTwoActivity = new Intent(RegisterActivity.this, RegisterStepTwoActivity.class);
        startActivity(itRegisterStepTwoActivity);
    }
}
