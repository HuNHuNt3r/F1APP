package hu.aut.bme.dg.f1app.presenter;

import com.google.common.eventbus.EventBus;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.interactor.DriverInteractor;
import hu.aut.bme.dg.f1app.view.DriversView;
import hu.aut.bme.dg.f1app.view.MainView;

/**
 * Created by DG on 2016.05.07..
 */
public class DriversPresenter extends Presenter<DriversView> {

    @Inject
    public DriverInteractor driverInteractor;

    public DriversPresenter() {
        F1Application.injector.inject(this);
    }

    public void refreshDrivers() {

        try {
            view.refreshDrivers(driverInteractor.getDrivers());
        } catch (Exception e) {
            view.refreshDrivers(driverInteractor.getDrivers());
            //view.showMessage(e.getMessage());
        }

    }

    public void showDriverAdd() {
        view.showDriverAdd();
    }

    public void showDriverDetails(int driverId) {
        view.showDriverDetails(driverId);
    }

}