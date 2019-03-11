package p.kirke.testapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import p.kirke.testapp.app.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SelectorFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureTabLayoutIsDisplayed() {
        onView(withId(R.id.tab_layout))
                .check(matches(isDisplayed()));
    }

    @Test
    public void ensureStringListFragmentIsDisplayed() {
        onView(withId(R.id.strings_list))
                .check(matches(isDisplayed()));
    }

    @Test
    public void ensureDataIsDisplayedInStringsList() {
        onView(withId(R.id.strings_list))
                .perform(RecyclerViewActions.scrollToPosition(1))
                .check(matches(hasDescendant(withText("String one"))))
                .perform(RecyclerViewActions.scrollToPosition(2))
                .check(matches(hasDescendant(withText("String two"))))
                .perform(RecyclerViewActions.scrollToPosition(2))
                .check(matches(hasDescendant(withText("String three"))));
    }

    @Test
    public void ensureSelectorFragmentIsDisplayedAfterSwipe() {
        onView(withId(R.id.fragment_container))
                .perform(swipeLeft());
        onView(withId(R.id.service_1_button))
                .check(matches(isClickable()));
        onView(withId(R.id.service_2_button))
                .check(matches(isClickable()));
        onView(withId(R.id.service_1_and_2_button))
                .check(matches(isClickable()));
        onView(withId(R.id.service_1_button))
                .perform(click());
    }
}
