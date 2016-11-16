package <%= app_id %>.di.module;

import <%= app_id %>.di.scope.UserScope;
import <%= app_id %>.model.User;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    private User user;

    public UserModule(User user) {
        this.user = user;
    }

    @Provides
    @UserScope
    User provideUser() {
        return user;
    }

}
