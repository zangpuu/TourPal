package com.foot.tourpal.business.home;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ZhangPu on 2017/5/2.
 */

public class HomeTabViewPager extends ViewPager {

    public HomeTabViewPager(Context context) {
        super(context);
    }

    public HomeTabViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        return v.getClass().getName().equals("com.baidu.mapapi.map.MapView")
                || v.getClass().getName().equals("com.amap.api.maps.MapView")
                || v.getClass().getName().equals("com.amap.api.maps.TextureMapView")
                || super.canScroll(v, checkV, dx, x, y);
    }
}
