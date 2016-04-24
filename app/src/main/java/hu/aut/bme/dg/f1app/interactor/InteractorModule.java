package hu.aut.bme.dg.f1app.interactor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DG on 2016.04.24..
 */
@Module
public class InteractorModule {
    @Provides
    public StringInteractor getStringInteractor() {
        return new StringInteractor();
    }
}
