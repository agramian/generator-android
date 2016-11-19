package <%= app_id %>.feature.start;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.EditText;

import <%= app_id %>.R;
import <%= app_id %>.feature.home.HomeActivity;
import <%= app_id %>.model.User;
import <%= app_id %>.ui.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class StartFragment extends BaseFragment implements StartContract.View {

    private StartContract.Presenter presenter;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;

    public static StartFragment newInstance() {
        Bundle args = new Bundle();
        StartFragment fragment = new StartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void navigateToHome() {
        userManager.login(new User());
        startActivity(HomeActivity.newIntent(getActivity()));
    }

    @Override
    public void showLoading() {
        onLoadingStarted();
    }

    @Override
    public void hideLoading() {
        onLoadingFinished();
    }

    @Override
    public void showError() {
        Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.invalid_credentials, Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.login)
    void login() {
        presenter.login(username.getText().toString(), password.getText().toString());
    }

    @Override
    public void setPresenter(StartContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.start_fragment;
    }

}
