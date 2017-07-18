package com.ybyb.zzq.rxjavastudy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mTestBtn1;
    private Button mTestBtn2;
    private Button mTestBtn3;
    private Button mTestBtn4;
    private Button mTestBtn5;
    private Button mTestBtn6;
    private Button mTestBtn7;
    private Button mTestBtn8;
    private Button mTestBtn9;
    private Button mTestBtn10;
    private WebView mWebView;
    private ImageView mTestIv;
    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        addListeners();
    }

    private void initData() {
        String[] list = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" };
        this.datas = new ArrayList<>();
        for (String s : list) {
            this.datas.add(s);
        }
    }

    private void initViews() {
        this.mTestBtn1 = (Button) findViewById(R.id.test_btn1);
        this.mTestBtn2 = (Button) findViewById(R.id.test_btn2);
        this.mTestBtn3 = (Button) findViewById(R.id.test_btn3);
        this.mTestBtn4 = (Button) findViewById(R.id.test_btn4);
        this.mTestBtn5 = (Button) findViewById(R.id.test_btn5);
        this.mTestBtn6 = (Button) findViewById(R.id.test_btn6);
        this.mTestBtn7 = (Button) findViewById(R.id.test_btn7);
        this.mTestBtn8 = (Button) findViewById(R.id.test_btn8);
        this.mTestBtn9 = (Button) findViewById(R.id.test_btn9);
        this.mTestBtn10= (Button) findViewById(R.id.test_btn10);
        this.mWebView= (WebView) findViewById(R.id.webView);
        this.mTestIv = (ImageView) findViewById(R.id.test_iv);
    }

    private void addListeners() {
        this.mTestBtn1.setOnClickListener(this);
        this.mTestBtn2.setOnClickListener(this);
        this.mTestBtn3.setOnClickListener(this);
        this.mTestBtn4.setOnClickListener(this);
        this.mTestBtn5.setOnClickListener(this);
        this.mTestBtn6.setOnClickListener(this);
        this.mTestBtn7.setOnClickListener(this);
        this.mTestBtn8.setOnClickListener(this);
        this.mTestBtn9.setOnClickListener(this);
        this.mTestBtn10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_btn1:
                RxJavaTest1();
                break;
            case R.id.test_btn2:
                RxJavaTest2();
                break;
            case R.id.test_btn3:
                RxJavaTest3();
                break;
            case R.id.test_btn4:
                RxJavaTest4();
                break;
            case R.id.test_btn5:
                RxJavaTest5();
                break;
            case R.id.test_btn6:
                RxJavaTest6();
                break;
            case R.id.test_btn7:
                RxJavaTest7();
                break;
            case R.id.test_btn8:
                RxJavaTest8();
                break;
            case R.id.test_btn9:
                RxJavaTest9();
                break;
            case R.id.test_btn10:
                RxJavaTest10();
                break;
        }
    }

    private void RxJavaTest10() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String url="http://www.baidu.com";
                mWebView.loadUrl(url);
                WebSettings settings = mWebView.getSettings();
                settings.setJavaScriptEnabled(true);
                mWebView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        // TODO Auto-generated method stub
                        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                        view.loadUrl(url);
                        return true;
                    }
                });
                Log.e("tag","name-1-->"+Thread.currentThread().getName());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(new Action0() {
            @Override
            public void call() {
                Toast.makeText(MainActivity.this,"主线程中执行吐司提示！",Toast.LENGTH_SHORT).show();
                Log.e("tag","name-2-->"+Thread.currentThread().getName());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this,"onCompleted！",Toast.LENGTH_SHORT).show();
                Log.e("tag","name-3-->"+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this,"onError！"+e.toString(),Toast.LENGTH_SHORT).show();
                Log.e("tag","name-3-->"+Thread.currentThread().getName());
            }

            @Override
            public void onNext(Object o) {
                Toast.makeText(MainActivity.this,"onNext！"+o.toString(),Toast.LENGTH_SHORT).show();
                Log.e("tag","name-3-->"+Thread.currentThread().getName());
            }
        });
    }

    private void RxJavaTest9() {
        Observable.Transformer transformer=new LiftAllTransformer();
        Observable.just(3).compose(transformer).subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this,"onCompleted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this,"onError"+e.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Object o) {
                Toast.makeText(MainActivity.this,"onNext:"+o.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RxJavaTest8() {
        Student stu1=new Student();
        stu1.setName("张三");
        Course cs1=new Course("语文",88.5);
        Course cs2=new Course("数学",98.2);
        Course cs3=new Course("英语",99.8);
        Course cs4=new Course("物理",79.0);
        Course cs5=new Course("化学",89.5);
        Course cs6=new Course("生物",69.3);
        List<Course> csList1=new ArrayList<>();
        csList1.add(cs1);
        csList1.add(cs2);
        csList1.add(cs3);
        csList1.add(cs4);
        csList1.add(cs5);
        csList1.add(cs6);
        stu1.setCourses(csList1);
        Student stu2=new Student();
        stu2.setName("李四");
        Course s1=new Course("语文",68.5);
        Course s2=new Course("数学",78.2);
        Course s3=new Course("英语",59.8);
        Course s4=new Course("政治",99.0);
        Course s5=new Course("历史",88.5);
        Course s6=new Course("地理",96);
        List<Course> csList2=new ArrayList<>();
        csList2.add(s1);
        csList2.add(s2);
        csList2.add(s3);
        csList2.add(s4);
        csList2.add(s5);
        csList2.add(s6);
        stu2.setCourses(csList2);
        Student[] students={stu1,stu2};
        Subscriber<Course> subscriber=new Subscriber<Course>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(Course course) {
                Toast.makeText(MainActivity.this,"学科："+course.getName()+"，得分："+course.getScore(),Toast.LENGTH_SHORT).show();
            }
        };
        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                Toast.makeText(MainActivity.this,student.getName(),Toast.LENGTH_SHORT).show();
                return Observable.from(student.getCourses());
            }
        }).subscribe(subscriber);
    }

    private void RxJavaTest7() {
        Observable.just(R.drawable.test1)
            .map(new Func1<Integer, Bitmap>() {//Fuc*方法表示有返回值，Action*方法表示无返回值，*的个数表示参数的个数
                @Override
                public Bitmap call(Integer integer) {
                    return BitmapFactory.decodeResource(getResources(),integer);
                }
            }).subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                Toast.makeText(MainActivity.this,"map",Toast.LENGTH_SHORT).show();
                mTestIv.setVisibility(View.VISIBLE);
                mTestIv.setImageBitmap(bitmap);
            }
        });
    }

    private void RxJavaTest6() {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(R.drawable.test2);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Drawable>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "onError" + e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Drawable drawable) {
                mTestIv.setVisibility(View.VISIBLE);
                mTestIv.setImageDrawable(drawable);
            }
        });
    }

    private void RxJavaTest5() {
        Observable.just(1, 2, 3, 4).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Toast.makeText(MainActivity.this, "number=" + integer, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RxJavaTest4() {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(R.drawable.test);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onNext(Drawable drawable) {
                mTestIv.setVisibility(View.VISIBLE);
                mTestIv.setImageDrawable(drawable);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RxJavaTest3() {
        String[] testDatas = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l" };
        Observable.from(testDatas).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(MainActivity.this, "TEST--->" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RxJavaTest2() {
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(MainActivity.this, "onNext------" + s, Toast.LENGTH_SHORT).show();
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Toast.makeText(MainActivity.this, "onError------" + throwable.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        Action0 onCompleteAction = new Action0() {
            @Override
            public void call() {
                Toast.makeText(MainActivity.this, "onComplete------", Toast.LENGTH_SHORT).show();
            }
        };
        Observable<String> observable = Observable.from(this.datas);
        observable.subscribe(onNextAction, onErrorAction, onCompleteAction);
    }

    private void RxJavaTest1() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "onCompleted------", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "onError------" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this, "TEST------" + s, Toast.LENGTH_SHORT).show();
            }
        };
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                datas.remove(datas.size() - 1);
                Toast.makeText(MainActivity.this, "onStart------", Toast.LENGTH_SHORT).show();
                super.onStart();
            }

            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "onCompleted------", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "onError------" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this, "TEST------" + s, Toast.LENGTH_SHORT).show();
            }
        };
        /**
         * 方式1，循环遍历，依次调用onNext方法和最后调用依次onComplete方法
         */
        //Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        //    private String[] list = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" };
        //    @Override
        //    public void call(Subscriber<? super String> subscriber) {
        //        for (String s : list) {
        //            subscriber.onNext(s);
        //        }
        //        subscriber.onCompleted();
        //    }
        //});

        /**
         * 方式2，用Observable.from(集合)方式创建被观察者，依次调用onNext方法和最后调用依次onComplete方法
         */
        Observable observable = Observable.from(this.datas);
        /**
         * 方式3，用Observable.just(单个需要的字符对象)方式创建被观察者，依次调用onNext方法和最后调用依次onComplete方法
         * 注意：不能传入集合，不能对象过多
         */
        //Observable observable=Observable.just("A", "B", "C", "D", "E", "F");


        //observable.subscribe(observer);
        observable.subscribe(subscriber);
    }
}
