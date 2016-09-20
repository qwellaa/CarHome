package com.lanou3g.carhome.networkrequest;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 *
 * 可以直接从网络请求解析成实体类, 然后返回
 */
public class GsonRequest<T> extends Request<T>{
    private final Response.Listener<T> mListener;
    private Class<T> mTClass;

    public GsonRequest(int method, String url,
                       Class<T> clazz, // 为了Gson 解析时使用
                       Response.Listener<T> mListener,
                       Response.ErrorListener listener) {
        super(method, url, listener);
        this.mListener = mListener;
        this.mTClass = clazz;
    }

    public GsonRequest(String url,
                       Class<T> clazz, // 为了Gson 解析时使用
                       Response.Listener<T> mListener,
                       Response.ErrorListener listener){
        this(Method.GET, url, clazz, mListener, listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        Gson gson = new Gson();
        return Response.success(gson.fromJson(parsed, mTClass), HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
