package com.example.bj.superdemo.ui.mvpdemo_a.views.modle;

/**
 * Created by BJ on 2016/11/21.
 * model bean类
 */

public class UserInfo {
    private int UserAge;
    private String UserName;
    private String UserSex;

    public UserInfo(int userAge, String userName, String userSex) {
        UserAge = userAge;
        UserName = userName;
        UserSex = userSex;
    }

    public UserInfo() {

    }

    public int getUserAge() {
        return UserAge;
    }

    public void setUserAge(int userAge) {
        UserAge = userAge;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }
}
