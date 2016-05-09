package hu.aut.bme.dg.f1app;

import javax.inject.Singleton;

import dagger.Component;
import hu.aut.bme.dg.f1app.interactor.DriverInteractor;
import hu.aut.bme.dg.f1app.interactor.InteractorModule;
import hu.aut.bme.dg.f1app.interactor.TeamInteractor;
import hu.aut.bme.dg.f1app.model.ModelModule;
import hu.aut.bme.dg.f1app.presenter.DriverAddPresenter;
import hu.aut.bme.dg.f1app.presenter.DriverEditPresenter;
import hu.aut.bme.dg.f1app.presenter.DriversPresenter;
import hu.aut.bme.dg.f1app.presenter.MainPresenter;
import hu.aut.bme.dg.f1app.presenter.TeamAddPresenter;
import hu.aut.bme.dg.f1app.presenter.TeamEditPresenter;
import hu.aut.bme.dg.f1app.presenter.TeamsPresenter;
import hu.aut.bme.dg.f1app.view.DriverAddActivity;
import hu.aut.bme.dg.f1app.view.DriverEditActivity;
import hu.aut.bme.dg.f1app.view.DriversActivity;
import hu.aut.bme.dg.f1app.view.MainActivity;
import hu.aut.bme.dg.f1app.view.TeamAddActivity;
import hu.aut.bme.dg.f1app.view.TeamEditActivity;
import hu.aut.bme.dg.f1app.view.TeamsActivity;
import hu.aut.bme.dg.f1app.view.ViewModule;

/**
 * Created by DG on 2016.04.24..
 */
@Singleton
@Component(modules = {ViewModule.class, InteractorModule.class, ModelModule.class})
public interface F1ApplicationComponent {

    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);

    void inject(DriversActivity driverActivity);
    void inject(DriversPresenter driversPresenter);

    void inject(DriverAddActivity driverAddActivity);
    void inject(DriverAddPresenter driverAddPresenter);

    void inject(DriverEditActivity driverEditActivity);
    void inject(DriverEditPresenter driverEditPresenter);

    void inject(TeamsActivity teamsActivity);
    void inject(TeamsPresenter teamsPresenter);

    void inject(TeamAddActivity teamAddActivity);
    void inject(TeamAddPresenter teamAddPresenter);

    void inject(TeamEditActivity teamEditActivity);
    void inject(TeamEditPresenter teamEditPresenter);

    void inject(DriverInteractor driverInteractor);
    void inject(TeamInteractor teamInteractor);

}
