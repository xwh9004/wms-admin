package com.wms.admin.auth;

public class UserInfoContext {

    private static ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    public static void set(UserInfo userInfo){
        userInfoThreadLocal.set(userInfo);
    }

    public static UserInfo getUserInfo(){
        return userInfoThreadLocal.get();
    }


    public static String getUsername(){
        return userInfoThreadLocal.get().getUsername();
    }

}
