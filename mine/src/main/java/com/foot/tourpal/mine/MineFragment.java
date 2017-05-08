package com.foot.tourpal.mine;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

import com.foot.tourpal.base.widget.PullScrollView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.LogUtils;
import com.jess.arms.utils.UiUtils;

public class MineFragment extends BaseFragment implements PullScrollView.OnTurnListener,View.OnClickListener {

    private static MineFragment fragment;
    private String tag = this.getClass().getSimpleName();
    private PullScrollView mScrollView;
    private ImageView mHeadImg;
    private TableLayout mMainLayout;
    private LinearLayout mSetting;
    private LinearLayout mDev;


    public static MineFragment newInstance() {
        if(fragment == null) {
            synchronized (MineFragment.class) {
                fragment = new MineFragment();
            }
        }
        return fragment;
    }
    
    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void initData() {
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
}
