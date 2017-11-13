package com.si.greenshare;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.si.greenshare.communication.WSUser;
import com.si.greenshare.internal_data_base.UserAuth;
import com.si.greenshare.internal_data_base.UserAuthController;
import com.si.greenshare.pojo.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by joao.silva.
 */

public class AuthHelper {
    private UserAuthController userAuthController;
    private Context context;
    private UserAuth userAuth;

    private AuthHelper(){};
    public AuthHelper(Context context){
        super();
        this.context = context;
        this.userAuthController = new UserAuthController(this.context);
    }

    public UserAuth getAuthentication(){
        return this.userAuthController.getUserAuth();
    }

    public Call<User> verifyLogin(UserAuth userAuth){
        return verifyLogin(userAuth.getEmail(), userAuth.getPassword());
    }

    public Call<User> verifyLogin(String email, String password) {
        this.userAuth = new UserAuth(email,password);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSUser.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        WSUser apiService = retrofit.create(WSUser.class);
        String auth = email + ":" + password;
        String authHeader = "Basic " + Base64.encodeToString(auth.getBytes(), Base64.NO_WRAP);
        Call<User> call = apiService.authLogin(authHeader);
        return call;
    }

    public boolean saveUserAuth(UserAuth userAuth){
        return saveUserAuth(userAuth.getEmail(), userAuth.getPassword());
    }


    public boolean saveUserAuth(String email, String password){
        if(userAuth != null){
            boolean success = this.userAuthController.insertUserAuth(userAuth.getEmail(), userAuth.getPassword());
            if(!success){
                Toast.makeText(this.context, "Erro inesperado ao salvar usuário, tente novamente.", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    public void deleteSavedUserAuth(){
        this.userAuthController.deleteUserAuth();
    }
}
