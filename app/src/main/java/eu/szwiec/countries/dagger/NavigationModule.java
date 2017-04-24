package eu.szwiec.countries.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.szwiec.countries.IntentStarter;

/**
 * Created by szwiec on 23/04/2017.
 */

@Module
public class NavigationModule {

    @Singleton
    @Provides
    public IntentStarter providesIntentStarter() {
        return new IntentStarter();
    }
}
