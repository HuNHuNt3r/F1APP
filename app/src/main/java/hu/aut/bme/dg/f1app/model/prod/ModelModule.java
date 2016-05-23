package hu.aut.bme.dg.f1app.model.prod;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DG on 2016.05.09..
 */
@Module
public class ModelModule {
    @Provides
    public DriverModel getDriverModel() {
        return new DriverModel();
    }
    @Provides
    public TeamModel getTeamModel() {
        return new TeamModel();
    }
}
