package eu.szwiec.countries;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by szwiec on 23/04/2017.
 */
public class Matchers {

    public static Matcher<View> withCountryViewName(final String expected) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item != null && item.findViewById(android.R.id.text1) != null) {
                    TextView taskName = (TextView) item.findViewById(android.R.id.text1);
                    if (TextUtils.isEmpty(taskName.getText())) {
                        return false;
                    } else {
                        return taskName.getText().equals(expected);
                    }
                } else {
                    return false;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Looked for " + expected);
            }
        };
    }

}
