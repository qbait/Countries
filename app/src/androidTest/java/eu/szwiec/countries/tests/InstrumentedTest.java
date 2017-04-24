package eu.szwiec.countries.tests;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.squareup.picasso.PicassoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.szwiec.countries.main.MainActivity;
import eu.szwiec.countries.pageobjects.DetailsActivityPageObject;
import eu.szwiec.countries.pageobjects.MainActivityPageObject;

/**
 * Created by szwiec on 23/04/2017.
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    public static final String COUNTRY = "Spain";

    private MainActivityPageObject mainPage;
    private DetailsActivityPageObject detailsPage;

    private PicassoIdlingResource picassoIdlingResource;

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mainPage = new MainActivityPageObject();
        detailsPage = new DetailsActivityPageObject();

        picassoIdlingResource = new PicassoIdlingResource();
        Espresso.registerIdlingResources(picassoIdlingResource);

        ActivityLifecycleMonitorRegistry
                .getInstance()
                .addLifecycleCallback(picassoIdlingResource);
    }

    @After
    public void tearDown() throws Exception {
        Espresso.unregisterIdlingResources(picassoIdlingResource);
    }

    @Test
    public void shouldShowTitleAndFlag() throws Exception {
        mainPage.clickCountry(COUNTRY);
        detailsPage.verifyFlag();
        detailsPage.verifyTitle(COUNTRY);
    }
}