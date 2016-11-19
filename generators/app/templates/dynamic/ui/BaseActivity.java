package <%= app_id %>.ui;


import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import <%= app_id %>.MainApplication;
import <%= app_id %>.R;
import <%= app_id %>.di.component.AppComponent;
import <%= app_id %>.model.UserManager;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity implements LoadingListener {

    private static final String KEY_PROGRESS_COUNT = "progress_count";
    @Inject
    protected UserManager userManager;
    AtomicInteger loaderCount = new AtomicInteger(0);
    ProgressDialog progressDialog;
    private boolean isResumed = false;

    @Override
    protected void onStart() {
        super.onStart();
        setupActivityComponent();
    }

    protected MainApplication getApp() {
        return (MainApplication) getApplication();
    }

    protected AppComponent getAppComponent() {
        return getApp().getAppComponent();
    }

    protected void setupActivityComponent() {
        getAppComponent().inject(this);
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResumed = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
        if (loaderCount.get() > 0) showProgressDialog();
        else hideProgressDialog();
    }


    @Override
    public void onLoadingStarted() {
        int i = loaderCount.incrementAndGet();
        if (i > 0 && isResumed && progressDialog == null) {
            showProgressDialog();
        }
    }

    @Override
    public void onLoadingFinished() {
        if (loaderCount.decrementAndGet() <= 0) {
            loaderCount.set(0);
            hideProgressDialog();
        }
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        progressDialog = null;
    }

    protected void showProgressDialog() {
        if (progressDialog != null) {
            return;
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PROGRESS_COUNT, loaderCount.intValue());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        loaderCount = new AtomicInteger(savedInstanceState.getInt(KEY_PROGRESS_COUNT));
    }

}
