package <%= app_id %>.feature.home;

import android.content.Intent;
import android.support.test.espresso.Espresso;
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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class HomeScreenTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityTestRule =
            new ActivityTestRule<>(HomeActivity.class, true /* Initial touch mode  */,
                    false /* Lazily launch activity */);

    private void launchActivity() {
        homeActivityTestRule.launchActivity(new Intent());
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
    public void test_01_toolbar() throws Exception {
        // check toolbar displayed
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        // check toolbar text
        onView(withText(R.string.home)).check(matches(withParent(withId(R.id.toolbar))));
    }

    @Test
    public void test_02_logout() throws Exception {
        // open overflow menu
        Espresso.openContextualActionModeOverflowMenu();
        // check logout menu item displayed
        onView(withText(R.string.action_logout)).check(matches(isDisplayed()));
        // click logouut
        onView(withText(R.string.action_logout)).perform(click());
    }

}
