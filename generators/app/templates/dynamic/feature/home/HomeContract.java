package <%= app_id %>.feature.home;


import <%= app_id %>.ui.BasePresenter;
import <%= app_id %>.ui.BaseView;

public interface HomeContract {

    interface View extends BaseView<Presenter>{

        void logout();

        void runRxJavaExample();

    }

    interface Presenter extends BasePresenter {

        void logout();

        void runRxJavaExample();

    }

}
