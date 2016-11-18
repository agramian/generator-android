package <%= app_id %>.feature.start;


import <%= app_id %>.ui.BasePresenter;
import <%= app_id %>.ui.BaseView;

public interface StartContract {

    interface View extends BaseView<Presenter>{

        void navigateToHome();

        void showError();

        void login(String username, String password, LoginListener listener);

    }

    interface Presenter extends BasePresenter {

        void performLogin();

    }

    interface LoginListener {

        void onSuccess();

        void onError();

    }

}
