package eu.szwiec.countries;

import android.content.Context;
import android.content.Intent;

import org.parceler.Parcels;

import eu.szwiec.countries.data.model.Country;
import eu.szwiec.countries.details.DetailsActivity;

/**
 * Created by szwiec on 23/04/2017.
 */

public class IntentStarter {

    public void showCountryDetails(Context context, Country country) {

        Intent i = new Intent(context, DetailsActivity.class);
        i.putExtra(DetailsActivity.KEY_COUNTRY, Parcels.wrap(country));
        context.startActivity(i);
    }

}
