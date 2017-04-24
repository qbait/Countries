package eu.szwiec.countries.pageobjects;

import android.support.test.espresso.contrib.RecyclerViewActions;

import eu.szwiec.countries.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static eu.szwiec.countries.Matchers.withCountryViewName;

/**
 * Created by szwiec on 23/04/2017.
 */

public class MainActivityPageObject {

    public void clickCountry(String country) {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItem(withCountryViewName(country), click()));
    }
}
