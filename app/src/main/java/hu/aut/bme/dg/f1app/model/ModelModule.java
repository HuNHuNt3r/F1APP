package hu.aut.bme.dg.f1app.model;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DG on 2016.04.24..
 */
@Module
public class ModelModule {
    @Provides
    public StringModel getStringModel() {
        return new StringModel();
    }
}