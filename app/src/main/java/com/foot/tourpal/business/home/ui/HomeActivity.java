package com.foot.tourpal.business.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.foot.tourpal.R;
import com.foot.tourpal.business.home.presenter.HomePresenter;
import com.foot.tourpal.business.home.contract.HomeContarct;
import com.foot.tourpal.business.record.RecordFragment;
import com.foot.tourpal.business.login.ui.LoginFragment;
import com.foot.tourpal.di.component.DaggerHomeComponent;
import com.foot.tourpal.di.module.HomeModule;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContarct.View, ViewPager.OnPageChangeListener{

    private FragmentPagerAdapter fragmentPagerAdapter;
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
        //fragments.add(MineFragment.newInstance());
        fragments.add(LoginFragment.newInstance());
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
}