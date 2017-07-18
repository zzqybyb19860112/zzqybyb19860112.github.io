package com.ybyb.zzq.rxjavastudy;

import rx.Observable;
import rx.Subscriber;

/**
 * 作者：周正权 on 2017/5/17
 * 邮箱：zhouzhengquan@shixinyun.com
 */

public class LiftAllTransformer implements Observable.Transformer<Integer, String> {
    @Override
    public Observable<String> call(Observable<Integer> integerObservable) {
        return integerObservable.lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        subscriber.onNext(integer + "");
                    }
                };
            }
        });
    }
}
