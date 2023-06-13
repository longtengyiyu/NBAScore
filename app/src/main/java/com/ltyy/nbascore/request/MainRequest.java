package com.ltyy.nbascore.request;

import com.ltyy.nbascore.api.Api;
import com.ltyy.nbascore.bean.Schedule;
import com.ltyy.nbascore.bean.Score;
import com.ltyy.nbascore.utils.DataUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public class MainRequest {
    private Disposable disposable;

    private volatile static MainRequest INSTANCE = null;

    private MainRequest(){}

    public static MainRequest getInstance(){
        if (INSTANCE == null){
            synchronized (MainRequest.class){
                if (INSTANCE == null){
                    INSTANCE = new MainRequest();
                }
            }
        }
        return INSTANCE;
    }

    public void onRequest(long time, final OnScheduleResponse response){
        stopDispose();
        String date = DataUtils.getCurrentDateFormat(System.currentTimeMillis(), "yyyy-MM-dd");
        Observable.timer(time, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                // 订阅时的操作
                disposable = d;
            }

            @Override
            public void onNext(Long value) {
                // 定时执行的任务
//                Api.getInstance().getItemById(id, response);

                Api.getInstance().getSchedule(date, "zh_CN", "+8", response);
            }

            @Override
            public void onError(Throwable e) {
                // 发生错误时的操作
                onRequest(10* 60 * 1000, response);
            }

            @Override
            public void onComplete() {
                // 完成时的操作
            }
        });
    }

    public void loadData(final OnScheduleResponse response){
        String date = DataUtils.getCurrentDateFormat(System.currentTimeMillis(), "yyyy-MM-dd");
        Api.getInstance().getSchedule(date, "zh_CN", "+8", response);
    }

    public void stopDispose(){
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    public interface OnListResponse{
        void list(List<Score> list);

        void onListEmpty();
    }

    public interface OnItemResponse {
        void item(Score score);

        void onItemEmpty();
    }

    public interface OnScheduleResponse {
        void schedule(Schedule schedule);

        void onError();
    }
}
