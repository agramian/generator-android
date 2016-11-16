package <%= app_id %>.model;

import <%= app_id %>.MainApplication;
import <%= app_id %>.cache.UserCache;

public class UserManager {

    private MainApplication app;
    private UserCache userCache;

    public UserManager(MainApplication app, UserCache userCache) {
        this.app = app;
        this.userCache = userCache;
    }


    public void login(User user) {
        app.createUserComponent(user);
        userCache.user(user);
    }

    public boolean isLoggedIn() {
        if (userCache.user() != null) {
            if (app.getUserComponent() == null) app.createUserComponent(userCache.user());
            return true;
        }
        return false;
    }

    public void logout() {
        userCache.user(null);
        app.releaseUserComponent();
    }

    public User getUser() {
        return userCache.user();
    }

}
