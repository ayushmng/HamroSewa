package com.example.ayush.myapplication.ServiceActivity;

import java.io.Serializable;

/**
 * Created by Ayush on 12/14/2017.
 */

public class UserInfo implements Serializable {  // serialiazable le search garda jun ma click garo teskae name milauna arko page ma use gareko ho...

    private String mImageUrl;
    private String mCreator;


    public UserInfo(String imageUrl, String creator) {
        this.mImageUrl = imageUrl;
        this.mCreator = creator;

    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCreator() {
        return mCreator;
    }

 }





