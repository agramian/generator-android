package <%= app_id %>.feature.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import <%= app_id %>.R;
import <%= app_id %>.feature.start.StartActivity;
import <%= app_id %>.ui.BaseUserActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends BaseUserActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private static final int MENU_RXJAVA = Menu.FIRST;
    private static final int MENU_LOGOUT = Menu.FIRST + 1;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    ImageView imageView;

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Glide.with(this)
                .load("https://image.freepik.com/free-vector/android-boot-logo_634639.jpg")
                .centerCrop()
                .placeholder(R.drawable.placeholder_loading)
                .error(R.drawable.placeholder_loading)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_RXJAVA, 0, R.string.action_rxjava_example).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, MENU_LOGOUT, 0, R.string.action_logout).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_RXJAVA:
                onRunSchedulerExampleButtonClicked();
                break;
            case MENU_LOGOUT:
                userManager.logout();
                startActivity(StartActivity.newIntent(HomeActivity.this));
                break;
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
                        Snackbar.make(findViewById(android.R.id.content), R.string.action_logout, Snackbar.LENGTH_LONG).show();
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
