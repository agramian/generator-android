package <%= app_id %>.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import <%= app_id %>.MainApplication;
import <%= app_id %>.di.component.AppComponent;
import <%= app_id %>.model.UserManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements LoadingListener {

    @Inject
    protected UserManager userManager;
    boolean useBaseListeners = false;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFragmentComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public MainApplication getApp() {
        return (MainApplication) getActivity().getApplication();
    }

    public AppComponent getAppComponent() {
        return getApp().getAppComponent();
    }

    protected abstract @LayoutRes int getLayoutResId();

    protected void setupFragmentComponent() {
        getAppComponent().inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            useBaseListeners = true;
        }
    }

    private BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void onLoadingStarted() {
        if (useBaseListeners && getActivity() != null) {
            getBaseActivity().onLoadingStarted();
        }
    }

    @Override
    public void onLoadingFinished() {
        if (useBaseListeners && getActivity() != null) {
            getBaseActivity().onLoadingFinished();
        }
    }

}
