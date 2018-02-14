package com.example.ayush.myapplication.HospitalDepartment;

import java.io.Serializable;

/**
 * Created by Ayush on 12/14/2017.
 */

public class DepartInfo implements Serializable, Comparable {  // serialiazable le search garda jun ma click garo teskae name milauna arko page ma use gareko ho...

    private String mImageUrl;
    private String mCreator;


    public DepartInfo(String imageUrl, String creator) {
        this.mImageUrl = imageUrl;
        this.mCreator = creator;

    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCreator() {
        return mCreator;
    }


    @Override
    public int compareTo(Object another) {
//        if(((DepartInfo)another).getCreator() > mCreator){ // ya > sign int ko case ma matra use hunx n ahile string gareko xa
//            return 1;
//        }
        if(((DepartInfo)another).getCreator() == mCreator){
            return 0;
        }else{
            return -1;
        }
//        return 0;
    }

 }





