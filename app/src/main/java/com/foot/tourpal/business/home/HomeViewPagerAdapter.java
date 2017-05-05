package com.foot.tourpal.business.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangPu on 2017/4/10.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {


    private  List<Fragment> fragments;
    private  FragmentManager fm;
    private List<String> tags = new ArrayList<>();

    public HomeViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        return fragment;

    }

    @Override
    public int getCount() {

        return fragments.size();
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
        tags.add(makeFragmentName(container.getId(), getItemId(position)));
        Fragment fragment = ((Fragment) super.instantiateItem(container, position));
        return fragment;

    }



    public String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    public void upDate(int position, String print, int orderid, double fee, String thirdId, String tableNo) {
        Fragment findFragment = fm.findFragmentByTag(tags.get(position));
        if (findFragment == null) {
            return;
        }
        notifyDataSetChanged();

    }


    @Override
    public int getItemPosition(Object object) {
        if (object != null && object instanceof Fragment) {
            if (fragments.indexOf(object) >= 0) {
                return super.getItemPosition(object);
            } else {
                return POSITION_NONE;
            }
        } else {
            return POSITION_NONE;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
