package <%= app_id %>;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import <%= app_id %>.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MENU_SETTINGS = Menu.FIRST;
    Toolbar toolbar;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_SETTINGS, 0, R.string.action_settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SETTINGS:
                onRunSchedulerExampleButtonClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

    void onRunSchedulerExampleButtonClicked() {
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override public void onComplete() {
                        Log.d(TAG, "onComplete()");
                        Snackbar.make(findViewById(android.R.id.content), R.string.action_settings, Snackbar.LENGTH_LONG).show();
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                    }

                    @Override public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");
                    }
                }));
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(() -> {
            // Do some long running operation
            SystemClock.sleep(5000);
            return Observable.just("one", "two", "three", "four", "five");
        });
    }

}
