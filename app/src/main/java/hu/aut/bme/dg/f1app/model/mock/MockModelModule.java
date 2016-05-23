package hu.aut.bme.dg.f1app.model.mock;

import dagger.Module;
import dagger.Provides;
import hu.aut.bme.dg.f1app.model.prod.DriverModel;
import hu.aut.bme.dg.f1app.model.prod.TeamModel;

/**
 * Created by DG on 2016.05.23..
 */
@Module
public class MockModelModule {

    @Provides
    public DriverModel getMockDriverModel() {
        return new MockDriverModel();
    }
    @Provides
    public TeamModel getMockTeamModel() {
        return new MockTeamModel();
    }

}
