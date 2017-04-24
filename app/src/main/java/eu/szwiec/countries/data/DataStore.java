package eu.szwiec.countries.data;

import java.util.List;

import eu.szwiec.countries.data.model.Country;
import io.reactivex.Observable;

/**
 * Created by szwiec on 23/04/2017.
 */

public interface DataStore {
    Observable<List<Country>> getCountries();
}
