package com.rsh_engineering.tkachenkoni.rxjava_app_study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Single;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String TAG = "OBSERVER";

    Observable<String> observable;
    Observer<String> observer;

    Observable<Integer> observableRange;
    Observer<Integer> observerRange;

    Observable<Long> observableTimeInterval;
    Observer<Long> observerTimeInterval;

    Observable<List<Integer>> observableListBuffer;
    Observer<List<Integer>> observerListBuffer;

    Observable<Integer> observableTake;
    Observer<Integer> observerTake;

    Observable<Integer> observableSkip;

    Observable<Integer> observableDistinct;

    Observer<String> observerFilter;

    Observable<Integer> observableMerge;
    Observer<Integer> observerMerge;

    Observable<String> observableFilter;


    Observable<Integer> observableMap;
    Observer<Integer> observerMap;

    Observer<String> observerZip;
    Observable<String> observableZip;

    Observable<Integer> observableTakeUtil;
    Observer<Integer> observerTakeUtil;

    Single<Boolean> observableAll;
    Observer<Boolean> observerAll;

    Observable<String> observableAction;
    Consumer<String> actionConsumer;

    Consumer<Long> actionSubscriptionStudy3;
    Observable<Long> observableSubscriptionStudy3;

    Button btn001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        observable = Observable.fromArray("one", "two", "three");

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG , " onNext: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG , "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG , "onCompleted");

            }
        };

        // create observable
        observableRange = Observable.range(10, 4);
        // create observer
        observerRange = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }
        };

        // create observable
        observableTimeInterval = Observable.interval(500, TimeUnit.MILLISECONDS);
        // create observer
        observerTimeInterval = new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Long aLong) {
                Log.d(TAG, "onNext: " + aLong);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }
        };




        // create observable
        observableListBuffer = Observable.fromArray(1,2,3,4,5,6,7,8)
                .buffer(3);

        // create observer
        observerListBuffer = new Observer<List<Integer>>() {
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(List<Integer> s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        /**MAP
         Оператор map преобразует все элементы последовательности. Для этого нам необходимо написать функцию преобразования. Например конвертация из String в Integer*/
        // create observable
        observableMap = Observable.fromArray("1", "2", "3", "4", "5", "6").map(stringToInteger);

        // create observer
        observerMap = new Observer<Integer>() {
            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        //TAKE
        // create observable
        observableTake = Observable.fromArray(5,6,7,8,9).take(3);
        // create observer
        observerTake = new Observer<Integer>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext: " + s);
            }
        };


        //SKIP
        // create observable
        observableSkip = Observable.fromArray(5,6,7,8,9).skip(2);

        //DISTINCT
        // create observable
        observableDistinct = Observable.fromArray(5,9,7,5,8,6,7,8,9).distinct();

        //FILTER
        // create observable
        //observableFilter = Observable.fromArray("15", "27", "34", "46", "52", "63").filter(filterFiveOnly);
        /*observableFilter = Observable.fromArray("15", "27", "34", "46", "52", "63").filter(new Predicate<String>() {
            @Override
            public boolean test(@NonNull String s) throws Exception {
                //return false;
                return s.contains("5");
            }
        });*/

        observableFilter = Observable.fromArray("15", "27", "34", "46", "52", "63").filter(predicateFileter);

        // create observer
        observerFilter = new Observer<String>() {
            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        //merge
        //Оператор merge объединит элементы из двух Observable в один Observable
        // create observable
        observableMerge = Observable.fromArray(1,2,3).mergeWith(Observable.fromArray(6,7,8,9));
        // create observer
        observerMerge = new Observer<Integer>() {
            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        //zip
        // create observable
        observableZip = Observable.fromArray(new Integer[]{1,2,3})
                .zipWith(Observable.fromArray("One", "Two", "Three"), zipIntWithString);
        // create observer
        observerZip = new Observer<String>() {
            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        //takeUntil
        // create observable
        //observableTakeUtil = Observable.fromArray(1,2,3,4,5,6,7,8).takeUntil(isFive);
        observableTakeUtil = Observable.fromArray(1,2,3,4,5,6,7,8).takeUntil(predicateTakeUtil);

        // create observer
        observerTakeUtil = new Observer<Integer>() {
            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        //All
        // create observable
        //Observable<Boolean> observableAll = Observable.fromArray(1,2,3,4,5,6,7,8).all(lessThanTen);
        observableAll = Observable.fromArray(1,2,3,4,5,6,7,8).all(predicateAll);

        // create observer
        observerAll = new Observer<Boolean>() {
            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Boolean s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        /**Action
         В наших примерах мы создавали Observer с тремя методами. И этот Observer умел ловить
         все три типа событий. Но бывают случаи, когда нам требуется, например, только событие
         Next и вместо Observer мы можем использовать его сокращенную версию - Action.*/
        // create observable
        observableAction = Observable.fromArray("one", "two", "three");
        // create action
        actionConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "onNext: " + s);
            }

            /*@Override
            public void call(String s) {
                Log.d(TAG, "onNext: " + s);
            }*/
        };


        //study3
        /**Subscription
         Чтобы получать события от Observable нам необходимо подписать на него
         Observer. Но что если в какой то момент мы больше не хотим ничего получать?
         Нам нужно будет отписаться. Для этого существует Subscription.*/
        // create observable
        observableSubscriptionStudy3 = Observable.interval(1, TimeUnit.SECONDS);

        // create action
        /*Action1<Long> action = new Action1<Long>() {//rxjava 1
            @Override
            public void call(Long l) {
                Log.d(TAG, "onNext: " + l);
            }
        };*/

        actionSubscriptionStudy3 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG, "onNext: " + aLong);
            }
        };



        btn001 = (Button)findViewById(R.id.btn_001);
        btn001.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_001:
                observable.subscribe(observer);
                observableRange.subscribe(observerRange);
                // observableTimeInterval.subscribe(observerTimeInterval);

                observableMap.subscribe(observerMap);

                /**fromCallable
                 Если у вас есть синхронный метод, который вам надо сделать асинхронным, то оператор fromCallable поможет вам*/
                Observable.fromCallable(new CallableLongAction("5"))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<Integer>(){
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.d(TAG, "onNext " + integer);
                            }

                            /*@Override
                            public void call(Integer integer){

                            }*/
                        });

                observableFilter.subscribe(observerFilter);

                observableListBuffer.subscribe(observerListBuffer);

                observableTake.subscribe(observerTake);

                observableMerge.subscribe(observerMerge);

                observableZip.subscribe(observerZip);

                observableTakeUtil.subscribe(observerTakeUtil);


                //observableAll.subscribe((SingleObserver<? super Boolean>) observerAll);

                observableAction.subscribe(actionConsumer);

                // subscribe
                //final Subscription subscription = (Subscription) observableSubscriptionStudy3.subscribe(actionSubscriptionStudy3);
                // unsubscribe
                /*getWindow().getDecorView().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "unsubscribe");
                        subscription.cancel();
                        //observableSubscriptionStudy3.unsubscribeOn(Schedulers.io());
                    }
                }, 4500);*/


                break;
        }
    }

    /**fromCallable
     Если у вас есть синхронный метод, который вам надо сделать асинхронным, то оператор fromCallable поможет вам*/
    //long method
    private int longAction(String text) {
        Log.d(TAG, "longAction");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(text);
    }

    //clas for wrap method
    class CallableLongAction implements Callable<Integer> {
        private final String data;
        public CallableLongAction(String data) {
            this.data = data;
        }
        @Override
        public Integer call() throws Exception {
            return longAction(data);
        }
    }


    /**map
     Оператор map преобразует все элементы последовательности. Для этого нам необходимо написать функцию преобразования. Например конвертация из String в Integer.*/
    /*Func1<String, Integer> stringToInteger = new Func1<String, Integer>() {
        @Override
        public Integer call(String s) {
            return Integer.parseInt(s);
        }
    };*///Function

    Function<String, Integer> stringToInteger = new Function<String, Integer>() {
        @Override
        public Integer apply(@NonNull String s) throws Exception {
            return Integer.parseInt(s);
        }
        /*@Override
        public Integer call(String s) {
            return Integer.parseInt(s);
        }*/
    };

    /**filter
     Оператор filter может отсеять только нужные элементы. Для этого необходимо создать функцию, в которой будет описан алгоритм фильтрации*/
    /*Func1<String, Boolean> filterFiveOnly = new Func1<String, Boolean>() {
        @Override
            public Boolean call(String s) {
                return s.contains("5");
        }
    };*/


    //doesn't work now
    Function<String, Boolean> filterFiveOnly = new Function<String, Boolean>() {
        @Override
        public Boolean apply(@NonNull String s) throws Exception {
            return s.contains("5");
        }

        /*@Override
            public Boolean call(String s) {
                return s.contains("5");
        }*/
    };

    //working for filter
    Predicate<String> predicateFileter = new Predicate<String>() {
        @Override
        public boolean test(@NonNull String s) throws Exception {
            return s.contains("5");
        }
    };

    //zip
    /*Func2<Integer, String, String> zipIntWithString = new Func2<Integer, String, String>() {
        @Override
        public String call(Integer i, String s) {
            return s + ": " + i;
        }
    };*/
    BiFunction<Integer, String, String> zipIntWithString = new BiFunction<Integer, String, String>() {
        @Override
        public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
            return s + ": " + integer;
        }

        /*@Override
        public String call(Integer i, String s) {
            return s + ": " + i;
        }*/
    };


    /**takeUntil
     Оператор takeUntil будет брать элементы пока
     не попадется элемент, удовлетворяющий определенному условию. Это условие нам необходимо оформить в виде функции.*/
    /*Func1<Integer, Boolean> isFive = new Func1<Integer, Boolean>() {
        @Override
        public Boolean call(Integer i) {
            return i == 5;
        }
    };*/
    //doesn't work now? need use predicate
    Function<Integer, Boolean> isFive = new Function<Integer, Boolean>() {
        @Override
        public Boolean apply(@NonNull Integer integer) throws Exception {
            return integer == 5;
        }
    };

    Predicate<Integer> predicateTakeUtil = new Predicate<Integer>() {
        @Override
        public boolean test(@NonNull Integer integer) throws Exception {
            return integer == 5;
        }
    };


    /**all
     Оператор all позволяет узнать все ли элементы удовлетворяют указанному условию. Условие нам необходимо оформить в виде функции.*/
    Function<Integer, Boolean> lessThanTen = new Function<Integer, Boolean>() {
        @Override
        public Boolean apply(@NonNull Integer integer) throws Exception {
            return integer < 10;
        }
    };

    Predicate<Integer> predicateAll = new Predicate<Integer>() {
        @Override
        public boolean test(@NonNull Integer integer) throws Exception {
            return integer < 10;
        }
    };

}