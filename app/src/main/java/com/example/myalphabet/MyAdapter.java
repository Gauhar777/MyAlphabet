package com.example.myalphabet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
    public MyAdapter(FragmentManager mgr) {
        super(mgr);
    }
    @Override
    public int getCount() {
        return(34);
    }
    @Override
    public Fragment getItem(int position) {
        return(DetailFragment.newInstance(position));
    }

}
