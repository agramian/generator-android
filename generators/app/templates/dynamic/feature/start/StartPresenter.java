package <%= app_id %>.feature.start;


import javax.inject.Inject;

final class StartPresenter implements StartContract.Presenter {

    private final StartContract.View view;

    @Inject
    StartPresenter(StartContract.View view) {
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
    public void login(String username, String password) {
        if (username.equals("test") && password.equals("test")) {
            view.navigateToHome();
        } else {
            view.showError();
        }
    }

}
