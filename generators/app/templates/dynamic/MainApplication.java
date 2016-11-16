package <%= app_id %>;

import android.app.Application;

import <%= app_id %>.di.component.AppComponent;
import <%= app_id %>.di.component.DaggerAppComponent;
import <%= app_id %>.di.component.UserComponent;
import <%= app_id %>.di.module.AppModule;
import <%= app_id %>.di.module.UserModule;
import <%= app_id %>.model.User;
import com.squareup.leakcanary.LeakCanary;

public class MainApplication extends Application {

    private AppComponent appComponent;
    private UserComponent userComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public UserComponent createUserComponent(User user) {
        userComponent = appComponent.plus(new UserModule(user));
        return userComponent;
    }

    public void releaseUserComponent() {
        userComponent = null;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

}
