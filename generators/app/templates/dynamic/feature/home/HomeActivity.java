package <%= app_id %>.feature.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import <%= app_id %>.R;
import <%= app_id %>.feature.home.di.HomeModule;
import <%= app_id %>.ui.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    private static final int MENU_RXJAVA = Menu.FIRST;
    private static final int MENU_LOGOUT = Menu.FIRST + 1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, HomeActivity.class);
    }

    @Inject
    HomePresenter presenter;
    HomeFragment homeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
            addFragmentToActivity(getSupportFragmentManager(), homeFragment, R.id.fragment);
        }
    }

    @Override
    protected void setupActivityComponent() {
        getAppComponent().plus(new HomeModule(homeFragment)).inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_RXJAVA, 0, R.string.action_rxjava_example).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, MENU_LOGOUT, 0, R.string.action_logout).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_RXJAVA:
                presenter.runRxJavaExample();
                break;
            case MENU_LOGOUT:
                presenter.logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
