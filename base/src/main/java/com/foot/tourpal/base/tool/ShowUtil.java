package com.foot.tourpal.base.tool;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.foot.tourpal.base.R;

/**
 * Created by ZhangPu on 2017/4/10.
 */

public class ShowUtil {

    private static ShowUtil showUtil = null;
    private static Toast sToast;
    private Context context;

    public static ShowUtil getInstance(Context context) {
        if (showUtil == null) {
            showUtil = new ShowUtil(context);
        } else {
            showUtil.setContext(context);
        }
        return showUtil;
    }

    public ShowUtil(Context context) {
        super();
        this.context = context;
    }

    private void setContext(Context context) {
        this.context = context;
    }


    /**
     * Show一个Toast出来.see{@link #showToast(String)}
     *
     * @param resId int | ResourceID
     */
    public Toast showToast(int resId) {
        return showToast(resId,Toast.LENGTH_SHORT);
    }

    public Toast showToast(int resId,int duration){
        return showToast(showUtil.context.getResources().getString(resId),duration);
    }

    public Toast showToast(String content){
        return showToast(content,Toast.LENGTH_SHORT);
    }
    /**
     * showToast
     *
     * @param content String | Toast文案内容
     * @return Toast | 如果在子线程调用这个方法,将会返回一个null
     */
    public Toast showToast(String content,int duration) {
        if (showUtil.context != null) {

            if (Looper.myLooper() != Looper.getMainLooper()) {
                showToastOnMainThread(content, duration);
            } else {
                View layout = LayoutInflater.from(showUtil.context).inflate(R.layout.toast, null);
                TextView tv = (TextView) layout.findViewById(R.id.toast_context);
                tv.setText(content);
                cancelToast();
                sToast = new Toast(showUtil.context);
                sToast.setDuration(duration);
                sToast.setGravity(Gravity.CENTER, 0, 0);
                sToast.setView(layout);
                sToast.show();
                return sToast;
            }
            return null;
        }
        return null;
    }

    /**
     * 切换到主线程showToast
     *
     * @param content String
     */
    private void showToastOnMainThread(final String content, final int duration) {
        if (showUtil.context!= null) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        showToast(content,duration);
                    }
                });
            } else {
                showToast(content,duration);
            }
        }
    }

    /**
     * 取消吐司显示
     */
    public void cancelToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }
}
