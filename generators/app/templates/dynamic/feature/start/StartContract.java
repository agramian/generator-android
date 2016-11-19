package <%= app_id %>.feature.start;


import <%= app_id %>.ui.BasePresenter;
import <%= app_id %>.ui.BaseView;

public interface StartContract {

    interface View extends BaseView<Presenter>{

        void navigateToHome();

        void showError();

    }

    interface Presenter extends BasePresenter {

        void login(String username, String password);

    }

}
