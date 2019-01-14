package com.lcxyy.netmodule;

import android.content.Context;

import com.lcxyy.netmodule.callback.IError;
import com.lcxyy.netmodule.callback.IFailure;
import com.lcxyy.netmodule.callback.IRequest;
import com.lcxyy.netmodule.callback.ISuccess;
import com.lcxyy.netmodule.ui.LoadStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {
    private String mUrl = null;

    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private IRequest mIReqquest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoadStyle mLoadStyle = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIReqquest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoadStyle loadStyle) {
        this.mContext = context;
        this.mLoadStyle = loadStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoadStyle = LoadStyle.BallClipRotateIndicator;
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIReqquest
                , mISuccess, mIFailure, mIError, mBody,
                mContext, mLoadStyle);
    }
}
