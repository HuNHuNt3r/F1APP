package hu.aut.bme.dg.f1app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import hu.aut.bme.dg.f1app.model.prod.DriverModel;
import hu.aut.bme.dg.f1app.model.prod.ModelModule;
import hu.aut.bme.dg.f1app.model.prod.TeamModel;
import hu.aut.bme.dg.f1app.presenter.MainPresenter;
import hu.aut.bme.dg.f1app.view.ViewModule;

/**
 * Created by DG on 2016.05.23..
 */
@Module
public class TestModule {

    private final ViewModule viewModule;
    private final ModelModule modelModule;

    public TestModule(Context context) {

        this.viewModule = new ViewModule(context);
        this.modelModule = new ModelModule();
    }

    @Provides
    public Context provideContext() {
        return viewModule.provideContext();
    }

    @Provides
    public TeamModel getTeamModel() {
        return modelModule.getTeamModel();
    }

    @Provides
    public DriverModel getDriverModel() {
        return modelModule.getDriverModel();
    }

    @Provides
    public MainPresenter getMainPresenter() {
        return viewModule.getMainPresenter();
    }
}
