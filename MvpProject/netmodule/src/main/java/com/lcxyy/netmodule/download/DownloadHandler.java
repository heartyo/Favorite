package com.lcxyy.netmodule.download;

import android.os.AsyncTask;

import com.lcxyy.netmodule.RestCreator;
import com.lcxyy.netmodule.callback.IError;
import com.lcxyy.netmodule.callback.IFailure;
import com.lcxyy.netmodule.callback.IRequest;
import com.lcxyy.netmodule.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadHandler {
    private final String mUrl;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest mRequest;
    private final ISuccess mSuccess;
    private final IFailure mFailure;
    private final IError mError;
    private final String mDownload_dir;
    private final String mExtension;
    private final String mName;

    public DownloadHandler(String url, IRequest request, ISuccess success, IFailure failure, IError error, String downloadDir, String extension, String name) {
        this.mUrl = url;
        this.mRequest = request;
        this.mSuccess = success;
        this.mFailure = failure;
        this.mError = error;
        this.mDownload_dir = downloadDir;
        this.mExtension = extension;
        this.mName = name;
    }

    public final void handDownload() {
        if (mRequest != null) {
            mRequest.onRequestStart();
        }
        RestCreator.getRestService().download(mUrl, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    SaveFileTask saveFileTask = new SaveFileTask(mRequest, mSuccess);
                    saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mDownload_dir, mExtension, body, mName);
                    if (saveFileTask.isCancelled()) {
                        if (mRequest != null) {
                            mRequest.onRequestEnd();
                        }
                    }
                } else {
                    if (mError != null) {
                        mError.onError(response.code(), response.message());
                    }
                }
                RestCreator.getParams().clear();

            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mFailure != null) {
                    mFailure.onFailure();
                    RestCreator.getParams().clear();
                }
            }
        });
    }
}
