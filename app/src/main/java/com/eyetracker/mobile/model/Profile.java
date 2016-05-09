package com.eyetracker.mobile.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fabia on 5/9/2016.
 */
public class Profile {

    @SerializedName("first_name")
    private String firstName = null;

    @SerializedName("last_name")
    private String lastName = null;

    @SerializedName("email")
    private String email = null;

    @SerializedName("avatar")
    private String avatar = null;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
