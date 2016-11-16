package <%= app_id %>.di.module;


import android.content.Context;

import <%= app_id %>.MainApplication;
import <%= app_id %>.cache.UserCache;
import <%= app_id %>.model.UserManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;

@Module
public class AppModule {

    MainApplication application;

    public AppModule(MainApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MainApplication providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    UserManager providesUserManager(MainApplication app, UserCache userCache) {
        return new UserManager(app, userCache);
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    UserCache providesUserCache(Context context) {
        return Esperandro.getPreferences(UserCache.class, context);
    }

}
