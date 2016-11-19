package <%= app_id %>.feature.start;


import android.os.SystemClock;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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
                    if (username.equals("test") && password.equals("test")) {
                        view.navigateToHome();
                    } else {
                        view.showError();
                    }
                    view.hideLoading();
                }
            });
    }

}
