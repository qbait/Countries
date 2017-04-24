package eu.szwiec.countries.pageobjects;

import eu.szwiec.countries.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by szwiec on 23/04/2017.
 */

public class DetailsActivityPageObject {
    public void verifyTitle(String title) {
        onView(withText(title)).check(matches(withParent(withId(R.id.toolbar))));
    }

    public void verifyFlag() {
        onView(withId(R.id.flag)).check(matches(isDisplayed()));
    }
}
