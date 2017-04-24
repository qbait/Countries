package eu.szwiec.countries.data;

import java.util.List;

import javax.inject.Inject;

import eu.szwiec.countries.App;
import eu.szwiec.countries.data.model.Country;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by szwiec on 23/04/2017.
 */

public class RemoteDataStore implements DataStore {

    @Inject
    Retrofit retrofit;

    @Inject
    LocalDataStore appLocalDataStore;

    public RemoteDataStore() {
        App.getComponent().inject(this);
    }


    @Override
    public Observable<List<Country>> getCountries() {
        return retrofit.create(CountryService.class).getCountries().doOnNext(new Consumer<List<Country>>() {
            @Override
            public void accept(List<Country> posts) throws Exception {
                appLocalDataStore.savePostToDatabase(posts);
            }
        });
    }

    private interface CountryService {
        @GET("/rest/v2/all")
        Observable<List<Country>> getCountries();
    }
}
