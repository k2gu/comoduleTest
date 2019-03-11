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
public class MainActivityTest {

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
    public void ensureSelectorFragmentIsDisplayedAfterSwipe() throws InterruptedException {
        onView(withId(R.id.fragment_container))
                .perform(swipeLeft());
        onView(withId(R.id.service_1_button))
                .check(matches(isClickable()));
        onView(withId(R.id.service_2_button))
                .check(matches(isClickable()));
        onView(withId(R.id.service_1_and_2_button))
                .check(matches(isClickable()));
        Thread.sleep(100);
        onView(withId(R.id.service_1_button))
                .perform(click());
        onView(withId(R.id.listView))
                .check(matches(isDisplayed()));
    }

    @Test
    public void ensureDataDisplayedOnClickService1Button() throws InterruptedException {
        onView(withId(R.id.fragment_container))
                .perform(swipeLeft());
        Thread.sleep(200);
        onView(withId(R.id.service_1_button))
                .perform(click());
        Thread.sleep(200);
        onView(withId(R.id.listView))
                .perform(RecyclerViewActions.scrollToPosition(1))
                .check(matches(hasDescendant(withText("one"))))
                .perform(RecyclerViewActions.scrollToPosition(2))
                .check(matches(hasDescendant(withText("aaa"))))
                .perform(RecyclerViewActions.scrollToPosition(3))
                .check(matches(hasDescendant(withText("two"))))
                .perform(RecyclerViewActions.scrollToPosition(4))
                .check(matches(hasDescendant(withText("bbb"))))
                .perform(RecyclerViewActions.scrollToPosition(5))
                .check(matches(hasDescendant(withText("tri"))))
                .perform(RecyclerViewActions.scrollToPosition(6))
                .check(matches(hasDescendant(withText("ccc"))))
                .perform(RecyclerViewActions.scrollToPosition(7))
                .check(matches(hasDescendant(withText("four"))))
                .perform(RecyclerViewActions.scrollToPosition(8))
                .check(matches(hasDescendant(withText("ddd"))))
                .perform(RecyclerViewActions.scrollToPosition(9))
                .check(matches(hasDescendant(withText("five"))));
    }

    @Test
    public void ensureDataDisplayedOnClickService2Button() throws InterruptedException {
        onView(withId(R.id.fragment_container))
                .perform(swipeLeft());
        Thread.sleep(200);
        onView(withId(R.id.service_2_button))
                .perform(click());
        Thread.sleep(200);
        onView(withId(R.id.listView))
                .perform(RecyclerViewActions.scrollToPosition(1))
                .check(matches(hasDescendant(withText("aye"))))
                .perform(RecyclerViewActions.scrollToPosition(2))
                .check(matches(hasDescendant(withText("beta"))))
                .perform(RecyclerViewActions.scrollToPosition(3))
                .check(matches(hasDescendant(withText("charlie"))))
                .perform(RecyclerViewActions.scrollToPosition(4))
                .check(matches(hasDescendant(withText("delta"))))
                .perform(RecyclerViewActions.scrollToPosition(5))
                .check(matches(hasDescendant(withText("echo"))));
    }

    @Test
    public void ensureDataDisplayedOnClickService1And2Button() throws InterruptedException {
        onView(withId(R.id.fragment_container))
                .perform(swipeLeft());
        Thread.sleep(200);
        onView(withId(R.id.service_1_and_2_button))
                .perform(click());
        Thread.sleep(200);
        onView(withId(R.id.listView))
                .perform(RecyclerViewActions.scrollToPosition(1))
                .check(matches(hasDescendant(withText("aye one"))))
                .perform(RecyclerViewActions.scrollToPosition(2))
                .check(matches(hasDescendant(withText("beta two"))))
                .perform(RecyclerViewActions.scrollToPosition(3))
                .check(matches(hasDescendant(withText("charlie tri"))))
                .perform(RecyclerViewActions.scrollToPosition(4))
                .check(matches(hasDescendant(withText("delta four"))))
                .perform(RecyclerViewActions.scrollToPosition(5))
                .check(matches(hasDescendant(withText("echo five"))));
    }
}
