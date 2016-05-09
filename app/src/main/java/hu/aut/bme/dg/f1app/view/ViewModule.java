package hu.aut.bme.dg.f1app.view;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.aut.bme.dg.f1app.presenter.DriverAddPresenter;
import hu.aut.bme.dg.f1app.presenter.DriverEditPresenter;
import hu.aut.bme.dg.f1app.presenter.DriversPresenter;
import hu.aut.bme.dg.f1app.presenter.MainPresenter;
import hu.aut.bme.dg.f1app.presenter.TeamAddPresenter;
import hu.aut.bme.dg.f1app.presenter.TeamEditPresenter;
import hu.aut.bme.dg.f1app.presenter.TeamsPresenter;

/**
 * Created by DG on 2016.04.24..
 */
@Module
public class ViewModule {
    private Context context;

    public ViewModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter getMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public DriversPresenter getDriverPresenter() {
        return new DriversPresenter();
    }

    @Provides
    @Singleton
    public DriverAddPresenter getDriverAddPresenter() {
        return new DriverAddPresenter();
    }

    @Provides
    @Singleton
    public DriverEditPresenter getDriverEditPresenter() {
        return new DriverEditPresenter();
    }

    @Provides
    @Singleton
    public TeamsPresenter getTeamPresenter() {
        return new TeamsPresenter();
    }

    @Provides
    @Singleton
    public TeamAddPresenter getTeamAddPresenter() {
        return new TeamAddPresenter();
    }

    @Provides
    @Singleton
    public TeamEditPresenter getTeamEditPresenter() {
        return new TeamEditPresenter();
    }

}
