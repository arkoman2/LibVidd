package com.bestteam.libvidd;

/**
 * Created by Arkadi on 24/12/2017.
 */

public class User
{
    String userId;
    String userName;
    String userGe;
    String password;
    boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public User() {

    }

    public User(String userId, String userName, String password, String userGe) {
        this.userId = userId;
        this.userName = userName;
        this.userGe = userGe;
        this.password = password;
        this.admin = false;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserGe() {
        return userGe;
    }
}
