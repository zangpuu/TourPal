package com.foot.tourpal.business.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.foot.tourpal.R;
import com.foot.tourpal.app.EventBusTags;
import com.foot.tourpal.base.framework.AppCache;
import com.foot.tourpal.business.home.component.DaggerHomeComponent;
import com.foot.tourpal.business.home.contract.HomeContarct;
import com.foot.tourpal.business.home.module.HomeModule;
import com.foot.tourpal.business.home.presenter.HomePresenter;
import com.foot.tourpal.business.login.ui.LoginFragment;
import com.foot.tourpal.business.mine.ui.MineFragment;
import com.foot.tourpal.business.record.RecordFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContarct.View, ViewPager.OnPageChangeListener{

    private FragmentStatePagerAdapter fragmentPagerAdapter;
    private List<Fragment> fragments;
    private MenuItem prevMenuItem;
    private RxPermissions rxPermissions;

    @BindView(R.id.VP_main)
    HomeTabViewPager viewPager;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_record:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_mine:
                    viewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        rxPermissions = new RxPermissions(this);
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.requestPermission();
        viewPager = (HomeTabViewPager) findViewById(R.id.VP_main);
        fragments = new ArrayList<>();
        //fragments.add(DiscoveryFragment.newInstance());
        fragments.add(RecordFragment.newInstance());
        if(AppCache.instance().isLogined()) {
            fragments.add(MineFragment.newInstance());
        }else{
            fragments.add(LoginFragment.newInstance());
        }
        fragmentPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(this);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        } else {
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public MenuInflater getMenuInflater() {
        return super.getMenuInflater();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.rxPermissions = null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {
        UiUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    public RxPermissions getRxPermissions() {
        return rxPermissions;
    }

    @Subscriber(tag = EventBusTags.LOGIN)
    private void loginResult(boolean isLogined) {
        Timber.d(TAG + " isLogin " + isLogined);
        if(isLogined) {
            viewPager.setCurrentItem(0);
            fragments.remove(1);
            fragments.add(MineFragment.newInstance());
            fragmentPagerAdapter.notifyDataSetChanged();
        }
    }
}
