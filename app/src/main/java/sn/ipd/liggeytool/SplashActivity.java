package sn.ipd.liggeytool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class SplashActivity extends AppCompatActivity {
    private Subscription subscription;

    Observer observer = new Observer() {

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object o) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onBackPressed() {

     //   subscription.cancel();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
       // subscription.cancel();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Observable.timer(1, TimeUnit.SECONDS).subscribe(observer);
        super.onResume();
    }
}
