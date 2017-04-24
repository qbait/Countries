package eu.szwiec.countries.data;

import java.util.List;

import eu.szwiec.countries.data.model.Country;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * Created by szwiec on 23/04/2017.
 */

public class Repository implements DataStore {

    private LocalDataStore mAppLocalDataStore;
    private RemoteDataStore mAppRemoteDataStore;

    public Repository(LocalDataStore mAppLocalDataStore, RemoteDataStore mAppRemoteDataStore) {
        this.mAppLocalDataStore = mAppLocalDataStore;
        this.mAppRemoteDataStore = mAppRemoteDataStore;
    }

    @Override
    public Observable<List<Country>> getCountries() {
        return Observable.concat(mAppLocalDataStore.getCountries(), mAppRemoteDataStore.getCountries())
                .filter(new Predicate<List<Country>>() {
                    @Override
                    public boolean test(@NonNull List<Country> countries) throws Exception {
                        return isUpToDate(countries);
                    }
                });
    }

    private boolean isUpToDate(List<Country> countries) {
        return countries.size() != 0; //more complicated in real life scenario
    }
}
