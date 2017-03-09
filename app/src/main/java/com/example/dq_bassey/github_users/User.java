package com.example.dq_bassey.github_users;
import java.io.Serializable;

public class User implements Serializable {
    public String getUserName() {
        return userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    private static final long serialVersionUID = -7060210544600464481L;
    private String userName;
    private String photoUrl;
    private String profileUrl;
}
