package <%= app_id %>.di.component;


import <%= app_id %>.di.module.AppModule;
import <%= app_id %>.di.module.UserModule;
import <%= app_id %>.feature.start.di.StartComponent;
import <%= app_id %>.feature.start.di.StartModule;
import <%= app_id %>.ui.BaseActivity;
import <%= app_id %>.ui.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {AppModule.class}
)
public interface AppComponent {

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    UserComponent plus(UserModule userModule);

    StartComponent plus(StartModule module);

}
