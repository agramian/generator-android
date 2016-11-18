package <%= app_id %>.feature.start.di;


import <%= app_id %>.feature.start.StartContract;

import dagger.Module;
import dagger.Provides;

@Module
public class StartModule {

    private final StartContract.View view;

    public StartModule(StartContract.View view) {
        this.view = view;
    }

    @Provides
    public StartContract.View provideView() {
        return view;
    }

}
