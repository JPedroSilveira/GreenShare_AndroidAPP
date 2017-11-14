package com.si.greenshare.activity.register;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.si.greenshare.R;
import com.si.greenshare.helpers.IsHelperActivity;

public class RegisterActivity extends IsHelperActivity {

    private Button btStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        if(isNotNull(actionBar)){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        startComponents();
        startButtons();
    }

    private void startComponents() {
        this.btStart = findViewById(R.id.bt_start);
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
        Intent itRegisterStepTwoActivity = new Intent(this, RegisterStepTwoActivity.class);
        startActivity(itRegisterStepTwoActivity);
    }
}
