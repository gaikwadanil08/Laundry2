package com.example.anil.laundry.cloth_price_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.anil.laundry.cloth_price_list.kids.ClothPriceList_Kids;
import com.example.anil.laundry.cloth_price_list.man.ClothPriceList_Man;
import com.example.anil.laundry.cloth_price_list.other.ClothPriceList_Other;
import com.example.anil.laundry.cloth_price_list.woman.ClothPriceList_Woman;

public class ClothPriceListAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ClothPriceListAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.mNumOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                return new ClothPriceList_Man();
            case 1:
                return new ClothPriceList_Woman();
            case 2:
                return new ClothPriceList_Kids();
            case 3:
                return new ClothPriceList_Other();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
