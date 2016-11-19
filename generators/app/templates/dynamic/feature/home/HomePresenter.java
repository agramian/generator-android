package <%= app_id %>.feature.home;


import android.os.SystemClock;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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
        view.showLoading();
        // simulate operation
        Observable.defer(() -> {
            SystemClock.sleep(1000);
            return Observable.just("");
            })
            // Run on a background thread
            .subscribeOn(Schedulers.io())// Be notified on the main thread
            .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        view.logout();
                        view.hideLoading();
                    }
                });
    }

    @Override
    public void runRxJavaExample() {
        view.runRxJavaExample();
    }

}
