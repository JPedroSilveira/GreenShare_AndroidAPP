package pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jpedr.
 */

public class User implements Serializable{
    private Date dateOfBirth;
    private String email;
    private String password;
    private String name;

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean validEmail(){
        return this.email != null && this.email.contains("@") && !this.email.isEmpty() && this.email.replaceAll(" ","") != "";
    }

    public boolean validPassword(){
        return this.password != null && this.password.replaceAll(" ","") != "" && this.password.length()>=6;
    }

    public boolean validName(){
        return this.name!=null && this.name.replaceAll(" ","") != "" && !this.name.isEmpty();
    }

    public boolean isValid(){
        return validName() && validEmail() && validPassword();
    }
}
