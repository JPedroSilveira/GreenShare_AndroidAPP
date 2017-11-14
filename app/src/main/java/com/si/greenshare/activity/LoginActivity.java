package com.si.greenshare.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.si.greenshare.AuthHelper;
import com.si.greenshare.activity.register.RegisterActivity;
import com.si.greenshare.helpers.IsHelperActivity;
import com.si.greenshare.internal_data_base.UserAuth;
import com.si.greenshare.pojo.User;
import com.si.greenshare.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends IsHelperActivity {

    private Button btLogin, btRegister;
    private EditText etEmail, etPassword;
    private UserAuth userAuth;
    private AuthHelper authHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.authHelper = new AuthHelper(getBaseContext());
        loadSavedUserAuth();
        verifyLogin(true);
    }

    private void startButtons() {
        this.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                verifyLogin(email, password);
            }
        });
        this.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterActivity();
            }
        });
    }

    private void toMenuActivity() {
        Intent itMenuActivity = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(itMenuActivity);
    }

    private void toRegisterActivity() {
        Intent itRegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(itRegisterActivity);
    }

    private void startComponents() {
        this.btLogin =  findViewById(R.id.bt_login);
        this.btRegister =  findViewById(R.id.bt_register);
        this.etEmail =  findViewById(R.id.et_email);
        this.etPassword = findViewById(R.id.et_password);
    }

    private void loadSavedUserAuth(){
        this.userAuth = this.authHelper.getAuthentication();
    }

    private void verifyLogin(String email, String password){
        this.userAuth = new UserAuth(email, password);
        verifyLogin(false);
    }

    private void verifyLogin(final boolean isFirstTime) {
        if(this.userAuth != null){
            Call<User> call = this.authHelper.verifyLogin(this.userAuth);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Context context = getApplicationContext();
                    if(response.isSuccessful()){
                        showToast(context, getString(R.string.valid_login));
                        if(saveUserAuth()){
                            toMenuActivity();
                        }
                    }else{
                        deleteSavedUserAuth();
                        showToast(context, getString(R.string.invalid_login));
                        if(isFirstTime){
                            startActivity();
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Context context = getApplicationContext();
                    showToast(context, getString(R.string.login_failed));
                }
            });
        }else{
            startActivity();
        }
    }

    private boolean saveUserAuth(){
        return this.authHelper.saveUserAuth(this.userAuth);
    }

    private void deleteSavedUserAuth(){
        this.authHelper.deleteSavedUserAuth();
    }

    private void startActivity(){
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if(isNotNull(actionBar)){
            actionBar.hide();
        }
        startComponents();
        startButtons();
    }
}
