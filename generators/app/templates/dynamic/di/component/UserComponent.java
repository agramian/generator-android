package <%= app_id %>.di.component;

import <%= app_id %>.di.module.UserModule;
import <%= app_id %>.di.scope.UserScope;
import <%= app_id %>.ui.BaseUserActivity;

import dagger.Subcomponent;

@UserScope
@Subcomponent(
    modules = {
        UserModule.class
    }
)
public interface UserComponent {

    void inject(BaseUserActivity baseUserActivity);

}
