package <%= app_id %>.feature.start;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import <%= app_id %>.R;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class StartScreenTest {

    @Rule
    public ActivityTestRule<StartActivity> startActivityTestRule =
            new ActivityTestRule<>(StartActivity.class, true /* Initial touch mode  */,
                    false /* Lazily launch activity */);

    private void launchActivity() {
        startActivityTestRule.launchActivity(new Intent());
    }

    @BeforeClass
    public static void setUpSuite() {
    }

    @Before
    public void setUp() {
        launchActivity();
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownSuite() {
    }

    @Test
    public void test_01_check_inputs() throws Exception {
        // check username input
        onView(withId(R.id.username)).check(matches(isDisplayed()));
        // check hint
        onView(withId(R.id.username)).check(matches(withHint(R.string.username)));
        // check password input displayed
        onView(withId(R.id.password)).check(matches(isDisplayed()));
        // check hint
        onView(withId(R.id.password)).check(matches(withHint(R.string.password)));
        // check button displayed
        onView(withId(R.id.login)).check(matches(isDisplayed()));
        // check button text
        onView(withId(R.id.login)).check(matches(withText(R.string.login)));
    }

    @Test
    public void test_02_login_failure() throws Exception {
        // click login with no entry
        onView(withId(R.id.login)).perform(click());
        // check snackbar displayed
        onView(allOf(withId(android.support.design.R.id.snackbar_text))).check(matches(isDisplayed()));
        // check snackbar text
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.invalid_credentials))).check(matches(isDisplayed()));
    }

}
