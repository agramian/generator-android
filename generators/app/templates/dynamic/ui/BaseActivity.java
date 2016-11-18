package <%= app_id %>.ui;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import <%= app_id %>.MainApplication;
import <%= app_id %>.di.component.AppComponent;
import <%= app_id %>.model.UserManager;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected UserManager userManager;

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

}
