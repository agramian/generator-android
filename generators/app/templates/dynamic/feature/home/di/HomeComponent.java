package <%= app_id %>.feature.home.di;


import <%= app_id %>.di.scope.FragmentScope;
import <%= app_id %>.feature.home.HomeActivity;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
    modules = {HomeModule.class}
)
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
