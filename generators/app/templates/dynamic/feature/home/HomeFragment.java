package <%= app_id %>.feature.home;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import <%= app_id %>.R;
import <%= app_id %>.feature.start.StartActivity;
import <%= app_id %>.ui.BaseFragment;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends BaseFragment implements HomeContract.View {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private HomeContract.Presenter presenter;
    @BindView(R.id.image)
    ImageView imageView;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.clear();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(this)
                .load("https://image.freepik.com/free-vector/android-boot-logo_634639.jpg")
                .centerCrop()
                .placeholder(R.drawable.placeholder_loading)
                .error(R.drawable.placeholder_loading)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_fragment;
    }


    @Override
    public void logout() {
        userManager.logout();
        startActivity(StartActivity.newIntent(getActivity()));
    }

    @Override
    public void runRxJavaExample() {
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override public void onComplete() {
                        Log.d(TAG, "onComplete()");
                        Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.action_logout, Snackbar.LENGTH_LONG).show();
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                    }

                    @Override public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");
                    }
                }));
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(() -> {
            // Do some long running operation
            SystemClock.sleep(5000);
            return Observable.just("one", "two", "three", "four", "five");
        });
    }

}
