package com.lcxyy.netmodule;

import android.content.Context;

import com.lcxyy.netmodule.callback.IError;
import com.lcxyy.netmodule.callback.IFailure;
import com.lcxyy.netmodule.callback.IRequest;
import com.lcxyy.netmodule.callback.ISuccess;
import com.lcxyy.netmodule.callback.RequestCallbacks;
import com.lcxyy.netmodule.download.DownloadHandler;
import com.lcxyy.netmodule.ui.LatteLoader;
import com.lcxyy.netmodule.ui.LoadStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Multipart;

public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    private LoadStyle LOAD_STYLE;
    private Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name,
                      IRequest resquest,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoadStyle loadStyle
    ) {
        this.URL = url;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = resquest;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOAD_STYLE = loadStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOAD_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOAD_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.PutRaw(URL, BODY);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, LOAD_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);

    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params is must null");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params is must null");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    public final void delete() {
        request(HttpMethod.DELETE);

    }

    public final void download() {
        new DownloadHandler(URL,
                REQUEST,
                SUCCESS,FAILURE,ERROR,DOWNLOAD_DIR,EXTENSION,NAME).handDownload();
    }
}
