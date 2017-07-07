package com.foot.tourpal.base.mvp;

import android.support.annotation.Nullable;

import java.io.Serializable;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;


/**
 * 如果你服务器返回的数据固定为这种方式(字段名可根据服务器更改)
 * 替换范型即可重用BaseJson
 * Created by jess on 26/09/2016 15:19
 * Contact with jess.yan.effort@gmail.com
 */

public class BaseJson<T> extends ResponseBody implements Serializable {
    private T data;
    private int status;
    private String msg;

    public T getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        if (status == Api.RequestSuccess) {
            return true;
        } else {
            return false;
        }
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public long contentLength() {
        return 0;
    }

    @Override
    public BufferedSource source() {
        return null;
    }
}
