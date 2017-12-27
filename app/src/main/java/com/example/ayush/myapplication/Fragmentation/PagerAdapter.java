package com.example.ayush.myapplication.Fragmentation;

import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Ayush on 12/3/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int noOfTabs;

    public PagerAdapter(android.support.v4.app.FragmentManager fm, int NoOfTabs){
       super(fm);
        this.noOfTabs = NoOfTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0:
                    Tab1 tab1 = new Tab1();
                    return tab1;

            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;

            case 2:
                Tab3 tab3 = new Tab3();
                return tab3;

                default:
                    return null;
            }
        }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
