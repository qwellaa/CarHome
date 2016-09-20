package com.lanou3g.carhome.networkrequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 *
 */
public class VolleySingleton {

    private static VolleySingleton volleySingleton;
    // 将请求队列放到单例里
    private RequestQueue queue;

    private VolleySingleton(){
        queue = Volley.newRequestQueue(MyApp.getContext());
    }

    public static VolleySingleton getInstance(){

        if (volleySingleton == null) {
            synchronized (VolleySingleton.class){
                if (volleySingleton == null) {
                    volleySingleton = new VolleySingleton();
                }
            }
        }
        return volleySingleton;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    /**
     * 把请求加入到请求队列
     * @param request : 各种网络请求
     */
    public void addRequest(Request request){
        queue.add(request);
    }

}
