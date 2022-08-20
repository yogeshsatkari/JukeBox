package org.jukebox.model;

public class User {
    private int userId;
    private String userName;
    private String userPassword;

    //constructors

    public User(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }


    //getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUerPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
