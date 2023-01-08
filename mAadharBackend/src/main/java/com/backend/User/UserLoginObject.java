package com.backend.User;

/**
 * Created by IntelliJ IDEA
 * User: Abhinav Bhardwaj
 * Date: 12/10/22
 * Time: 16:42
 */


public class UserLoginObject {
    private String citizenId;
    private String password;

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
