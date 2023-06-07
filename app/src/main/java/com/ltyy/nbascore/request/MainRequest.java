package com.ltyy.nbascore.request;

import com.ltyy.nbascore.api.Api;
import com.ltyy.nbascore.bean.Score;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public class MainRequest {

    public MainRequest(){}

    public void getList(OnListResponse response){
        Api.getInstance().getList(response);
    }

    public void getItemById(final long id, final OnItemResponse response){
        Observable.timer(10, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                // 订阅时的操作
            }

            @Override
            public void onNext(Long value) {
                // 定时执行的任务
                Api.getInstance().getItemById(id, response);
            }

            @Override
            public void onError(Throwable e) {
                // 发生错误时的操作
            }

            @Override
            public void onComplete() {
                // 完成时的操作
            }
        });
    }

    public interface OnListResponse{
        void list(List<Score> list);

        void onListEmpty();
    }

    public interface OnItemResponse {
        void item(Score score);

        void onItemEmpty();
    }
}
