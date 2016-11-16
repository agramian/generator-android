package <%= app_id %>;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import <%= app_id %>.model.User;
import <%= app_id %>.ui.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends BaseActivity {

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, StartActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    void login() {
        userManager.login(new User());
        startActivity(HomeActivity.newIntent(this));
    }

}
