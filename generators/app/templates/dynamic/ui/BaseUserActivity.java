package <%= app_id %>.ui;


import <%= app_id %>.di.component.UserComponent;
import <%= app_id %>.model.User;

import javax.inject.Inject;

public abstract class BaseUserActivity extends BaseActivity {

    @Inject
    protected User user;

    protected UserComponent getUserComponent() {
        return getApp().getUserComponent();
    }

    @Override
    protected void setupActivityComponent() {
        getUserComponent().inject(this);
    }

}
