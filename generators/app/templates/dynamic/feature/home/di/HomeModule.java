package <%= app_id %>.feature.home.di;


import <%= app_id %>.feature.home.HomeContract;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    private final HomeContract.View view;

    public HomeModule(HomeContract.View view) {
        this.view = view;
    }

    @Provides
    public HomeContract.View provideView() {
        return view;
    }

}
