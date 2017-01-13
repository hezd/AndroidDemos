package com.hezd.example;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hezd on 2017/1/13.
 */

public class CategoryPagerAdapter extends FragmentPagerAdapter {
    private List<CategoryFragment> mPagerList;

    public CategoryPagerAdapter(FragmentManager fm,List<CategoryFragment> list) {
        super(fm);
        mPagerList = list;
    }

    @Override
    public int getCount() {
        return mPagerList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mPagerList.get(position);
    }

}
