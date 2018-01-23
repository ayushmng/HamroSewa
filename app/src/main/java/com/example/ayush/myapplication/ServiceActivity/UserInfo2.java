package com.example.ayush.myapplication.ServiceActivity;

/**
 * Created by Ayush on 1/10/2018.
 */

public class UserInfo2 {

    private String Id, Name, Amount;

    public UserInfo2(String id, String name) {

        this.Id = id;
        this.Name = name;
//        this.Amount = amount;

    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getAmount() {
        return Amount;
    }
}
