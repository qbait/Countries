package eu.szwiec.countries.data;

import java.util.List;

import eu.szwiec.countries.data.model.Country;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Created by szwiec on 23/04/2017.
 */

public class LocalDataStore implements DataStore {

    @Override
    public Observable<List<Country>> getCountries() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Country> results = realm.where(Country.class).findAll();
        List<Country> list = realm.copyFromRealm(results);
        Timber.d("read posts.size() = " + list.size());

        realm.close();
        return Observable.just(list);
    }

    public void savePostToDatabase(final List<Country> countries) {
        Timber.d("save posts.size() = " + countries.size());
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(countries);
            }
        });
        realm.close();
    }
}
