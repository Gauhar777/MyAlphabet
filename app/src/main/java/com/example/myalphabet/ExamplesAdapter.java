package com.example.myalphabet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ExamplesAdapter extends FragmentPagerAdapter {
    public ExamplesAdapter (FragmentManager mgr) {
        super(mgr);
    }
    @Override
    public Fragment getItem(int i) {
        return(ExampleWordFragment.newInstance(i));
    }

    @Override
    public int getCount() {
        return(34);
    }
}
