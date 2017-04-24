package eu.szwiec.countries;

import android.app.Application;

import eu.szwiec.countries.dagger.AppComponent;
import eu.szwiec.countries.dagger.AppModule;
import eu.szwiec.countries.dagger.DaggerAppComponent;
import eu.szwiec.countries.dagger.DataModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by szwiec on 23/04/2017.
 */

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule("http://restcountries.eu/"))
                .build();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("db")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static AppComponent getComponent() {
        return component;
    }
}
