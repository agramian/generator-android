package <%= app_id %>.di.component;


import <%= app_id %>.di.module.AppModule;
import <%= app_id %>.di.module.UserModule;
import <%= app_id %>.ui.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {AppModule.class}
)
public interface AppComponent {

    void inject(BaseActivity baseActivity);

    UserComponent plus(UserModule userModule);

}
