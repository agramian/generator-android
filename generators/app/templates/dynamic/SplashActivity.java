package <%= app_id %>;


import <%= app_id %>.feature.home.HomeActivity;
import <%= app_id %>.feature.start.StartActivity;
import <%= app_id %>.ui.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onStart() {
        super.onStart();
        if (userManager.isLoggedIn()) {
            startActivity(HomeActivity.newIntent(this));
        } else {
            startActivity(StartActivity.newIntent(this));
        }
        finish();
    }

}
