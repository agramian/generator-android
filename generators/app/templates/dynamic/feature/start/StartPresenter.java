package <%= app_id %>.feature.start;


import javax.inject.Inject;

final class StartPresenter implements StartContract.Presenter, StartContract.LoginListener {

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
    public void performLogin() {
        view.login("", "", this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onSuccess() {
        view.navigateToHome();
    }

    @Override
    public void onError() {
        view.showError();
    }
}
