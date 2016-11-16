package <%= app_id %>.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import <%= app_id %>.MainApplication;
import <%= app_id %>.di.component.AppComponent;
import <%= app_id %>.model.UserManager;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected UserManager userManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

}
