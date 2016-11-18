package <%= app_id %>.feature.start;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import <%= app_id %>.R;
import <%= app_id %>.feature.start.di.StartModule;
import <%= app_id %>.ui.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class StartActivity extends BaseActivity {

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, StartActivity.class);
    }

    @Inject
    StartPresenter presenter;
    StartFragment startFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        ButterKnife.bind(this);
        startFragment = (StartFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (startFragment == null) {
            startFragment = StartFragment.newInstance();
            addFragmentToActivity(getSupportFragmentManager(), startFragment, R.id.fragment);
        }
    }

    @Override
    protected void setupActivityComponent() {
        getAppComponent().plus(new StartModule(startFragment)).inject(this);
    }

}
