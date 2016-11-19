package <%= app_id %>.feature.home;


import javax.inject.Inject;

final class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View view;

    @Inject
    HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Inject
    void setupListeners() {
        view.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void logout() {
        view.logout();
    }

    @Override
    public void runRxJavaExample() {
        view.runRxJavaExample();
    }

}
