package <%= app_id %>.ui;


import <%= app_id %>.di.component.UserComponent;
import <%= app_id %>.model.User;

import javax.inject.Inject;

public abstract class BaseUserFragment extends BaseFragment {

    @Inject
    protected User user;

    protected UserComponent getUserComponent() {
        return getApp().getUserComponent();
    }

    @Override
    protected void setupFragmentComponent() {
        getUserComponent().inject(this);
    }

}
