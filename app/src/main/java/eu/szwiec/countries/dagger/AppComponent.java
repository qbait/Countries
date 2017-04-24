package eu.szwiec.countries.dagger;

import javax.inject.Singleton;

import dagger.Component;
import eu.szwiec.countries.data.RemoteDataStore;
import eu.szwiec.countries.main.MainActivity;

/**
 * Created by szwiec on 23/04/2017.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class, NavigationModule.class})
public interface AppComponent {
    void inject(RemoteDataStore appRemoteDataStore);

    void inject(MainActivity mainActivity);
}
