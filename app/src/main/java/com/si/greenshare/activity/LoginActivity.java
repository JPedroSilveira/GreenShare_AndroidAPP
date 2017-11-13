package com.si.greenshare.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.si.greenshare.AuthHelper;
import com.si.greenshare.activity.register.RegisterActivity;
import com.si.greenshare.internal_data_base.UserAuth;
import com.si.greenshare.pojo.User;
import com.si.greenshare.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

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
                finish();
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
        this.btLogin = (Button) findViewById(R.id.bt_login);
        this.btRegister = (Button) findViewById(R.id.bt_register);
        this.etEmail = (EditText) findViewById(R.id.et_email);
        this.etPassword = (EditText) findViewById(R.id.et_password);
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
                        Toast.makeText(context, "Logado com sucesso!", Toast.LENGTH_LONG).show();
                        if(saveUserAuth()){
                            toMenuActivity();
                            finish();
                        }
                    }else{
                        deleteSavedUserAuth();
                        Toast.makeText(context, "Login inválido!", Toast.LENGTH_LONG).show();
                        if(isFirstTime){
                            setContentView(R.layout.activity_login);
                            getSupportActionBar().hide();
                            startComponents();
                            startButtons();
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Falha ao consultar servidor, tente novamente.", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private boolean saveUserAuth(){
        return this.authHelper.saveUserAuth(this.userAuth);
    }

    private void deleteSavedUserAuth(){
        this.authHelper.deleteSavedUserAuth();
    }

}
