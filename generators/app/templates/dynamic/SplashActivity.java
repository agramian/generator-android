package <%= app_id %>;

import android.os.Bundle;
import android.support.annotation.Nullable;

import <%= app_id %>.ui.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (userManager.isLoggedIn()) {
            startActivity(HomeActivity.newIntent(this));
        } else {
            startActivity(StartActivity.newIntent(this));
        }
        finish();
    }

}
