package com.foot.tourpal.business.record;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.foot.tourpal.R;
import com.foot.tourpal.base.framework.AppCache;
import com.foot.tourpal.business.record.component.DaggerRecordComponent;
import com.foot.tourpal.business.record.contract.RecordContract;
import com.foot.tourpal.business.record.module.RecordModule;
import com.foot.tourpal.business.record.presenter.RecordPresenter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.LogUtils;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import timber.log.Timber;

public class RecordFragment extends BaseFragment<RecordPresenter> implements RecordContract.View, View.OnClickListener {

    private static RecordFragment fragment;

    private String tag = this.getClass().getSimpleName();

    private TextureMapView mapView;
    private AMap aMap;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption mOption = new AMapLocationClientOption();
    @BindView(R.id.bt_start)
    Button startBt;
    @BindView(R.id.bt_stop)
    Button stopBt;
    @BindView(R.id.ll_type)
    LinearLayout typeLayout;
    @BindView(R.id.bt_type_1)
    Button typeBt1;
    @BindView(R.id.bt_type_2)
    Button typeBt2;
    @BindView(R.id.bt_type_3)
    Button typeBt3;
    @BindView(R.id.bt_type_4)
    Button typeBt4;
    @BindView(R.id.bt_type_5)
    Button typeBt5;
    @BindView(R.id.bt_type_6)
    Button typeBt6;
    @BindView(R.id.bt_type_7)
    Button typeBt7;
    @BindView(R.id.bt_type_8)
    Button typeBt8;
    @BindView(R.id.bt_type_9)
    Button typeBt9;
    @BindView(R.id.bt_type_10)
    Button typeBt10;
    @BindView(R.id.bt_type_11)
    Button typeBt11;
    @BindView(R.id.bt_type_12)
    Button typeBt12;

    public static RecordFragment newInstance() {
        if (fragment == null) {
            synchronized (RecordFragment.class) {
                fragment = new RecordFragment();
            }
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        mapView = (TextureMapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        initLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mapView = null;
        aMap = null;
        locationClient = null;
        mOption = null;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerRecordComponent
                .builder()
                .appComponent(appComponent)
                .recordModule(new RecordModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_record, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        startBt.setOnClickListener(this);
        stopBt.setOnClickListener(this);
        typeBt1.setOnClickListener(this);
        typeBt2.setOnClickListener(this);
        typeBt3.setOnClickListener(this);
        typeBt4.setOnClickListener(this);
        typeBt5.setOnClickListener(this);
        typeBt6.setOnClickListener(this);
        typeBt7.setOnClickListener(this);
        typeBt8.setOnClickListener(this);
        typeBt9.setOnClickListener(this);
        typeBt10.setOnClickListener(this);
        typeBt11.setOnClickListener(this);
        typeBt12.setOnClickListener(this);
        if (DataHelper.getBooleanSF(this.getActivity(), AppCache.instance().KEY_IS_RECORDING)) {
            startBt.setVisibility(View.GONE);
            stopBt.setVisibility(View.VISIBLE);
        } else {
            startBt.setVisibility(View.VISIBLE);
            stopBt.setVisibility(View.GONE);
        }
    }

    @Override
    public void setData(Object data) {

    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(aMapLocation -> {
            if (null != aMapLocation && aMapLocation.getLatitude() != 0 && aMapLocation.getLongitude() != 0) {
                LatLng now = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                Timber.i(TAG + String.format("init loc, lat=%f, lon=%f", now.latitude, now.longitude));
                aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        now, 15, 0, 0)));
                locationClient.stopLocation();
            } else {
                LogUtils.warnInfo(tag, "location failed");
            }
        });
        locationClient.startLocation();
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {

        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
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
    public void onClick(View v) {
        if (v.getId() == R.id.bt_start) {
            typeLayout.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.bt_stop) {
            startBt.setVisibility(View.VISIBLE);
            stopBt.setVisibility(View.GONE);
            DataHelper.setBooleanSF(this.getActivity(), AppCache.instance().KEY_IS_RECORDING, false);
        } else {
            showMessage(((Button)v).getText() + "");
            typeLayout.setVisibility(View.GONE);
            startBt.setVisibility(View.GONE);
            stopBt.setVisibility(View.VISIBLE);
            DataHelper.setBooleanSF(this.getActivity(), AppCache.instance().KEY_IS_RECORDING, true);
        }
    }
}
