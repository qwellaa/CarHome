package com.lanou3g.carhome.networkrequest;

import android.os.Handler;
import android.os.Looper;

import com.lanou3g.carhome.search.SearchHistoryBean;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 数据库的 操作类
 */
public class DBTools {

    private static DBTools sDBTools;
    private LiteOrm mLiteOrm;
    // 线程池
    private ExecutorService threadPool;
    // 用来做线程切换的
    private Handler mHandler;

    private DBTools() {
        mLiteOrm = LiteOrm.newSingleInstance(MyApp.getContext(), "carHome.db");
        threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        // 构建handler的时候, 参数传
        // Looper.getMainLooper()
        // 这样可以保证, 该handler 一定属于主线程
        mHandler = new Handler(Looper.getMainLooper());
//        Message msg = mHandler.obtainMessage();
    }

    public static DBTools getInstance(){
        if (sDBTools == null) {
            synchronized (DBTools.class){
                if (sDBTools == null) {
                    sDBTools = new DBTools();
                }
            }
        }
        return sDBTools;
    }

    // 插入搜索历史数据
    public void insertSearchHistory(final SearchHistoryBean historyBean){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(historyBean);
            }
        }).start();
    }

    public void insertSearchHistory(final List<SearchHistoryBean> historyBeanList){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(historyBeanList);
            }
        }).start();

    }

//    // 判断去重复
//    private boolean isRepeatHistory(SearchHistoryBean historyBean){
//
//    }

    // 查询搜索历史
    public void getAllSearchHistory(final QueryListener<SearchHistoryBean> queryListener){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ArrayList<Person> persons = mLiteOrm.query(Person.class);
//                queryListener.onQuery(persons);
//            }
//        }).start();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                final ArrayList<SearchHistoryBean> beanArrayList = mLiteOrm.query(SearchHistoryBean.class);
                // handler 的post 方法 可以把一个runnable
                // 发送到主线程去执行
                mHandler.post(new HandlerRunnable<SearchHistoryBean>(beanArrayList, queryListener));
            }
        });
    }
    // 删除搜索历史
    public <T> void deleteAll(Class<T> tClass){
        if (tClass == null) {
            return;
        }
        mLiteOrm.delete(tClass);
    }

    // handler 使用 runnable
    class HandlerRunnable<T> implements Runnable{

        private List<T> mTList;
        private QueryListener<T> mTQueryListener;

        public HandlerRunnable(List<T> mTList, QueryListener<T> mTQueryListener) {
            this.mTList = mTList;
            this.mTQueryListener = mTQueryListener;
        }

        @Override
        public void run() {
            mTQueryListener.onQuery(mTList);
        }
    }

    // 查询完成后, 回调接口
    public interface QueryListener<T>{
        // 当查询完成后了, 将查到的数据作为data 返回给Activity等
        void onQuery(List<T> beanArrayList);
    }
}
