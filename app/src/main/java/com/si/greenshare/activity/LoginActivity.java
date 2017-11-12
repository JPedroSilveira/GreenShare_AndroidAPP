package com.si.greenshare.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.si.greenshare.activity.register.RegisterActivity;
import com.si.greenshare.communication.WSUser;
import com.si.greenshare.pojo.User;
import com.si.greenshare.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;
    private Button btRegister;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        startComponents();
        startButtons();
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
                cleanEditTexts();
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

    private void cleanEditTexts() {
        this.etEmail.setText("");
        this.etPassword.setText("");
    }

    private void startComponents() {
        this.btLogin = (Button) findViewById(R.id.bt_login);
        this.btRegister = (Button) findViewById(R.id.bt_register);
        this.etEmail = (EditText) findViewById(R.id.et_email);
        this.etPassword = (EditText) findViewById(R.id.et_password);
    }

    private void verifyLogin(String email, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSUser.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        WSUser apiService = retrofit.create(WSUser.class);
        String auth = email + ":" + password;
        String authHeader = "Basic " + Base64.encodeToString(auth.getBytes(), Base64.NO_WRAP);
        Call<User> call = apiService.authLogin(authHeader);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Context context = getApplicationContext();
                if(response.isSuccessful()){
                    Toast.makeText(context, "Logado com sucesso!", Toast.LENGTH_LONG).show();
                    cleanEditTexts();
                    toMenuActivity();
                }else{
                    Toast.makeText(context, "Login inválido!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Context context = getApplicationContext();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
