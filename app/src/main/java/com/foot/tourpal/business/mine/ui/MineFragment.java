package com.foot.tourpal.business.mine.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.foot.tourpal.R;
import com.foot.tourpal.base.framework.AppCache;
import com.foot.tourpal.base.widget.PullScrollView;
import com.foot.tourpal.business.login.model.entity.LoginResponse;
import com.foot.tourpal.business.mine.component.DaggerMineComponent;
import com.foot.tourpal.business.mine.contract.MineContract;
import com.foot.tourpal.business.mine.module.MineModule;
import com.foot.tourpal.business.mine.presenter.MinePresenter;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.LogUtils;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View, PullScrollView.OnTurnListener,View.OnClickListener {

    private static MineFragment fragment;
    private String tag = this.getClass().getSimpleName();
    private PullScrollView mScrollView;
    private ImageView mHeadImg;
    private TableLayout mMainLayout;
    private LinearLayout mSetting;
    private LinearLayout mDev;
    private AppComponent appComponent;
    private ImageLoader imageLoader;
    @BindView(R.id.user_avatar)
    CircleImageView userAvatar;

    public static MineFragment newInstance() {
        if(fragment == null) {
            //synchronized (MineFragment.class)
            {
                fragment = new MineFragment();
            }
        }
        return fragment;
    }
    
    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerMineComponent
                .builder()
                .appComponent(appComponent)
                .mineModule(new MineModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mScrollView = (PullScrollView) getActivity().findViewById(R.id.scroll_view);
        mHeadImg = (ImageView) getActivity().findViewById(R.id.background_img);

        mMainLayout = (TableLayout) getActivity().findViewById(R.id.table_layout);
        mScrollView.setHeader(mHeadImg);
        mScrollView.setOnTurnListener(this);

        mSetting = (LinearLayout) getActivity().findViewById(R.id.ll_set);
        mDev = (LinearLayout) getActivity().findViewById(R.id.ll_dev);

        mDev.setOnClickListener(this);


        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.leftMargin = 30;
        layoutParams.bottomMargin = 10;
        layoutParams.topMargin = 10;

        for (int i = 0; i < 30; i++) {
            TableRow tableRow = new TableRow(getActivity());
            TextView textView = new TextView(getActivity());
            textView.setText("Test pull down scroll view " + i);
            textView.setTextSize(20);
            textView.setPadding(15, 15, 15, 15);

            tableRow.addView(textView, layoutParams);
            if (i % 2 != 0) {
                tableRow.setBackgroundColor(Color.LTGRAY);
            } else {
                tableRow.setBackgroundColor(Color.WHITE);
            }

            final int n = i;
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Click item " + n, Toast.LENGTH_SHORT).show();
                }
            });

            mMainLayout.addView(tableRow);
        }
        appComponent = ((App)getActivity().getApplicationContext()).getAppComponent();
        imageLoader = appComponent.imageLoader();
        if (isAdded() && isVisible())
            if (AppCache.instance().isLogined()) {
                LoginResponse loginResponse = DataHelper.getDeviceData(getActivity(), "loginResult");
                if(loginResponse != null && !TextUtils.isEmpty(loginResponse.getData().getUserAvatar())){
                    Timber.d(loginResponse.getData().getUserAvatar());
                    imageLoader.loadImage(appComponent.appManager().getCurrentActivity() == null
                                    ? appComponent.application() : appComponent.appManager().getCurrentActivity(),
                            GlideImageConfig
                                    .builder()
                                    .url(loginResponse.getData().getUserAvatar())
                                    .imageView(userAvatar)
                                    .build());
                }
            } else {
                Intent intent = new Intent();
                intent.setData(Uri.parse("App://www.foot.com/LoginActivity"));
                UiUtils.startActivity(getActivity(), intent);
            }
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void onTurn() {

    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if( isVisibleToUser && isVisible() ) {
//            Intent intent = new Intent();
//            intent.setData(Uri.parse("App://www.foot.com/LoginActivity"));
//            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ll_dev) {
            //UiUtils.makeText(getActivity(), "开发者");
            Intent intent = new Intent();
            intent.setData(Uri.parse("App://www.foot.com/DevActivity"));
            UiUtils.startActivity(intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        imageLoader.clear(appComponent.application(), GlideImageConfig.builder()
                .imageViews(userAvatar)
                .build());
        mDev = null;
        mSetting = null;
        mMainLayout = null;
        mHeadImg = null;
        mScrollView = null;
        fragment = null;
        userAvatar = null;
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
}
