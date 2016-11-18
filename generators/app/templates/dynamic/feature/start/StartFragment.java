package <%= app_id %>.feature.start;


import android.os.Bundle;
import android.support.design.widget.Snackbar;

import <%= app_id %>.R;
import <%= app_id %>.feature.home.HomeActivity;
import <%= app_id %>.model.User;
import <%= app_id %>.ui.BaseFragment;

import butterknife.OnClick;

public class StartFragment extends BaseFragment implements StartContract.View {

    private StartContract.Presenter presenter;

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
    public void showError() {
        Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void login(String username, String password, StartContract.LoginListener listener) {
        int randomChoice = (int) (Math.random() * 2 - 0);
        switch (randomChoice) {
            case 0:
                listener.onSuccess();
                break;
            case 1:
                listener.onError();
                break;
        }
    }

    @OnClick(R.id.login)
    void login() {
        presenter.performLogin();
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
