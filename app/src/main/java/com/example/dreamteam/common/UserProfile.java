package com.example.dreamteam.common;

public class UserProfile {

    private static UserProfile userProfile;

    public static UserProfile getInstance() {
        if (userProfile == null) {
            userProfile = new UserProfile();
        }
        return userProfile;
    }

    public static UserProfile getNewInstance() {
        SharedPreferenceUtil.clear();
        SharedPreferenceUtil.save();

        userProfile = new UserProfile();
        return userProfile;
    }
    private static final String USERMOBILE = "u_user_mobile";
    private static final String USEREMAIL = "u_user_email";
    private static final String USEREDREAMTEAMID = "u_user_dream_team_id";

    public void setUserMobile(String name) {
        saveField(USERMOBILE, name);
    }public void setUserId(String name) {
        saveField(USEREDREAMTEAMID, name);
    }
    public void setUserEmail(String name) {
        saveField(USEREMAIL, name);
    }
    private void saveField(String name, String value) {
        SharedPreferenceUtil.putValue(name, value);
        SharedPreferenceUtil.save();
    }
}
