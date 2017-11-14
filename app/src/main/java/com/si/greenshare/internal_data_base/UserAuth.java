package com.si.greenshare.internal_data_base;

import android.util.Base64;

/**
 * Created by joao.silva.
 */

public class UserAuth {

    private String password;
    private String email;

    public UserAuth() {

    }

    public UserAuth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getAuthHeader(){
        return "Basic " + Base64.encodeToString((this.email + ":" + this.password).getBytes(), Base64.NO_WRAP);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
