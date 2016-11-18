package <%= app_id %>.feature.start.di;


import <%= app_id %>.di.scope.FragmentScope;
import <%= app_id %>.feature.start.StartActivity;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
    modules = {StartModule.class}
)
public interface StartComponent {
    void inject(StartActivity startActivity);
}
