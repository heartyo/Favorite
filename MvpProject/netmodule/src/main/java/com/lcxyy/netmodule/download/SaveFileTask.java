package com.lcxyy.netmodule.download;

import android.os.AsyncTask;

import com.lcxyy.netmodule.callback.IRequest;
import com.lcxyy.netmodule.callback.ISuccess;
import com.lcxyy.netmodule.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private final IRequest request;
    private final ISuccess success;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.request = request;
        this.success = success;
    }

    @Override
    protected File doInBackground(Object... objects) {
         String downloadDir = (String) objects[0];
         String extension = (String) objects[1];
        final ResponseBody requestBody = (ResponseBody) objects[2];
        final String name = (String) objects[3];
        final InputStream is = requestBody.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (success != null) {
            success.onSuccess(file.getPath());

        }
        if (request != null) {
            request.onRequestEnd();
        }
    }


}
